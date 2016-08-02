/*
Copyright 2016 Narrative Nights Inc. All Rights Reserved.
Copyright 2015 Google Inc. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

#include "tensorflow_jni.h"

#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>
#include <android/bitmap.h>

#include <jni.h>
#include <pthread.h>
#include <unistd.h>
#include <queue>
#include <sstream>
#include <string>

#include "tensorflow/core/framework/step_stats.pb.h"
#include "tensorflow/core/framework/tensor.h"
#include "tensorflow/core/framework/types.pb.h"
#include "tensorflow/core/lib/strings/stringprintf.h"
#include "tensorflow/core/platform/env.h"
#include "tensorflow/core/platform/logging.h"
#include "tensorflow/core/platform/mutex.h"
#include "tensorflow/core/platform/types.h"
#include "tensorflow/core/public/session.h"
#include "tensorflow/core/util/stat_summarizer.h"
#include "jni_utils.h"

static const int PIXEL_SIZE = 784;
static std::unique_ptr<tensorflow::Session> session;

static bool g_compute_graph_initialized = false;
using namespace tensorflow;

JNIEXPORT jint JNICALL
TENSORFLOW_METHOD(init)(JNIEnv* env,
						jobject thiz,
						jobject java_asset_manager,
						jstring model) {
	
	if (g_compute_graph_initialized) {
		LOG(INFO) << "Compute graph already loaded. skipping.";
		return 0;
	}
	
	const char* const model_cstr = env->GetStringUTFChars(model, NULL);
	
	LOG(INFO) << "Loading Tensorflow.";
	LOG(INFO) << "Making new SessionOptions.";
	
	tensorflow::SessionOptions options;
	tensorflow::ConfigProto& config = options.config;
	LOG(INFO) << "Got config, " << config.device_count_size() << " devices";
	session.reset(tensorflow::NewSession(options));
	LOG(INFO) << "Session created.";
	
	tensorflow::GraphDef graph_def;
	
	LOG(INFO) << "Graph created.";
	
	AAssetManager* const asset_manager =
		AAssetManager_fromJava(env, java_asset_manager);
	
	LOG(INFO) << "Acquired AssetManager.";

	LOG(INFO) << "Reading file to proto: " << model_cstr;
	
	ReadFileToProto(asset_manager, model_cstr, &graph_def);

	LOG(INFO) << "Creating session.";

	tensorflow::Status s = session->Create(graph_def);
	
	if (!s.ok()) {
		LOG(ERROR) << "Could not create Tensorflow Graph: " << s;
		return -1;
	}

	// Clear the proto to save memory space.
	graph_def.Clear();
	
	LOG(INFO) << "Tensorflow graph loaded from: " << model_cstr;

	g_compute_graph_initialized = true;

	return 0;
}

static double makePredictions(const double* inputs) {
	// Create input tensor
	Tensor input_tensor( tensorflow::DT_FLOAT,
						 tensorflow::TensorShape( {1,1} ) );

	auto input_tensor_mapped = input_tensor.tensor<float, 2>();
    input_tensor_mapped(0,0) = 1.0;
    input_tensor_mapped(0,1) = inputs[0];

	LOG(INFO) << "Start computing.";

	std::vector<std::pair<std::string, tensorflow::Tensor> > input_tensors(
		{{"input", input_tensor}});

	// Actually run the image through the model.
	std::vector<Tensor> output_tensors;
	std::vector<std::string> output_names({"output"});

	Status run_status = session->Run( input_tensors, output_names,
									  {},
									  &output_tensors );

	LOG(INFO) << "End computing.";

	if (!run_status.ok()) {
		LOG(ERROR) << "Error during inference: " << run_status;
		return -1;
	}

	// Find output
	Tensor& output_tensor = output_tensors[0];
	tensorflow::TTypes<float>::Flat output_flat = output_tensor.flat<float>();
    LOG(INFO) << " prediction : " << output_flat(1);
    return 10.0;

	//return  output_flat(0);;
}


static int makePredictionForAcivityClass(int featurescount, const double* inputs) {
    LOG(INFO) << "ARM Architecture";
    LOG(INFO) << "features count : "<< featurescount;
	// Create input tensor
	Tensor input_tensor( tensorflow::DT_FLOAT,
						 tensorflow::TensorShape( {1,featurescount} ) );

	auto input_tensor_mapped = input_tensor.tensor<float, 2>();

	for(int i=0; i<featurescount; i++) {
    		input_tensor_mapped(0,i) = inputs[i];
    	}


    //input_tensor_mapped(0,1) = inputs[1];
    //input_tensor_mapped(0,2) = inputs[2];

	LOG(INFO) << "Start computing.";

	std::vector<std::pair<std::string, tensorflow::Tensor> > input_tensors(
		{{"input", input_tensor}});

	// Actually run the image through the model.
	std::vector<Tensor> output_tensors;
	std::vector<std::string> output_names({"output"});

	Status run_status = session->Run( input_tensors, output_names,
									  {},
									  &output_tensors );

	LOG(INFO) << "End computing.";

	if (!run_status.ok()) {
		LOG(ERROR) << "Error during inference: " << run_status;
		return -1;
	}

	// Find output
	Tensor& output_tensor = output_tensors[0];
	tensorflow::TTypes<float>::Flat output_flat = output_tensor.flat<float>();

    float max_score = std::numeric_limits<float>::min();
    	int maxIndex = -1;

    	for(int i=0; i<6; ++i) {
    		const float score = output_flat(i);
    		if( score > max_score ) {
    			maxIndex = i;
    			max_score = score;
    		}

    		VLOG(0) <<  " (" << i << "): " << score;
    	}

    return maxIndex;

	//return  output_flat(0);;
}


JNIEXPORT jint JNICALL
TENSORFLOW_METHOD(getActivityClass)(JNIEnv* env, jobject thiz,jint featurescount, jdoubleArray inputs) {


    jboolean iCopied = JNI_FALSE;
    jdouble* processed = env->GetDoubleArrayElements(inputs, &iCopied);
    int result = makePredictionForAcivityClass(featurescount,  reinterpret_cast<double*>(processed) );


    return (jint)result;


}


JNIEXPORT jdouble JNICALL
TENSORFLOW_METHOD(getTestPrediction)(JNIEnv* env, jobject thiz, jdoubleArray inputs) {
    jboolean iCopied = JNI_FALSE;
    jdouble* processed = env->GetDoubleArrayElements(inputs, &iCopied);
    double result = makePredictions( reinterpret_cast<double*>(processed) );
    //env->ReleaseDoubleArrayElements(processed, inputs, JNI_ABORT);
    return (jdouble)result;

}