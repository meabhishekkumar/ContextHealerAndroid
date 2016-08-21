package edu.berkeley.datascience.contextualhealer;

import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import org.apache.commons.math3.stat.StatUtils;
import org.json.JSONArray;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import edu.berkeley.datascience.contextualhealer.model.PredictionSample;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

@LargeTest
public class APITest {

    @Test
    public void AWS_API_CALL() throws  Exception{

        PredictionSample sample1 = new PredictionSample();
        sample1.AddAccelerometerX(2.01);
        sample1.AddAccelerometerX(2.02);
        sample1.AddAccelerometerX(2.03);


        sample1.AddAccelerometerY(1.01);
        sample1.AddAccelerometerY(1.02);
        sample1.AddAccelerometerY(1.03);

        sample1.AddAccelerometerZ(0.01);
        sample1.AddAccelerometerZ(0.02);
        sample1.AddAccelerometerZ(0.03);


        PredictionSample sample2 = new PredictionSample();
        sample2.AddAccelerometerX(3.01);
        sample2.AddAccelerometerX(3.02);
        sample2.AddAccelerometerX(3.03);


        sample2.AddAccelerometerY(4.01);
        sample2.AddAccelerometerY(4.02);
        sample2.AddAccelerometerY(4.03);

        sample2.AddAccelerometerZ(5.01);
        sample2.AddAccelerometerZ(5.02);
        sample2.AddAccelerometerZ(5.03);

        ArrayList<PredictionSample> samples= new ArrayList<PredictionSample>();
        samples.add(sample1);
        samples.add(sample2);

        JSONArray jsonArray = new JSONArray();
        for (int i=0; i < samples.size(); i++) {
            jsonArray.put(samples.get(i).getJSONObjectForAPICall());
        }

        //Call to API
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonArray.toString());

        OkHttpClient client = new OkHttpClient();
        String response = "";

        //Use 10.0.2.2 for localhost or 127.0.0.1 : for genymotion it 10.0.3.2
        String url = "http://ec2-54-174-44-241.compute-1.amazonaws.com:5000/predict";

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("API_TEST", "onFailure() Request was:");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.e("API_TEST", "onResponse(): " + response.body().string() );
            }


        });






        double[] values = {4.0, 2.0, 4.0, 2.0};
        double mean = StatUtils.mean(values);
        assertEquals(mean, 3.0, 0.0001);
    }

}
