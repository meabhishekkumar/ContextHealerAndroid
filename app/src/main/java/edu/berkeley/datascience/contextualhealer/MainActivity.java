package edu.berkeley.datascience.contextualhealer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private float mLastX, mLastY, mLastZ;
    private double meanX, meanY, meanZ = 0.0;
    private float deltaX, deltaY, deltaZ;

    private boolean mInitialized;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private final float NOISE = (float) 0.0;
    private JSONArray SensorDataArray = new JSONArray();
    private final String TAG = MainActivity.class.getSimpleName();
    private boolean isReset = false;
    private TextView tvX, tvY,tvZ;
    private ActivityType currentActivity = ActivityType.unknown;


    private ActivityDetector mActivityDetector = new ActivityDetector();
    private PredictionSample mPredictionSample = new PredictionSample();
    private IPredictor mPredictor = new OnDevicePredictor();
    private TextView mResultText;
    private ImageView mActivityImageView;

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvX= (TextView)findViewById(R.id.x_axis);
        tvY= (TextView)findViewById(R.id.y_axis);
        tvZ= (TextView)findViewById(R.id.z_axis);
        mResultText= (TextView)findViewById(R.id.text_result);
        mActivityImageView = (ImageView)findViewById(R.id.activity_image_view);
        mActivityImageView.setImageResource(R.drawable.unknown);
        //Check if Activity Detector is working or not
        boolean ret = mActivityDetector.setup(this);
        if( !ret ) {
            Log.i(TAG, "Detector setup failed");
            return;
        }

        //Sensor manager setup
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        //int[] inputs = new int[]{10};
        //double inputs[] = {-1.566340,	-7.777218,	-9.112013 }; // Actual jogging
//        double inputs[] = {-2.533385,	6.088296,	3.023717 };  // Actual jogging
//
//        currentActivity = getActivity(mActivityDetector, inputs);
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mResultText.setText("Output: " + currentActivity);
//            }
//        });
        //PredictActivity(mPredictionSample);
        //TestAPICall();
        //SetupTimerForAPICall(); //working on local emulator.. have to configure the API IP for actual machine

        // With All 51 inputs
        double inputs[] = {1.4003247 , -0.3805544 , -0.17791853,  0.13834895, -0.67707807,
                0.1862355 ,  1.45689209, -0.45677004, -0.487276  ,  0.14323398,
                -0.67898883, -0.37901384,  0.80240762,  0.50062263,  0.08050633,
                1.41200524,  0.04279476,  0.8866245 , -0.16974649,  0.25476395,
                1.32464603,  0.19608871,  1.25351932,  1.9168293 ,  0.90290446,
                0.21110376,  0.13234236,  1.02353982,  0.09213821, -0.03568492,
                1.24188142, -0.09534324, -0.15783303,  1.3654427 , -0.27739236,
                -0.31567319,  1.45689209, -0.45677004, -0.487276  ,  1.43321083,
                -0.67439332, -0.65683225,  1.31815458, -0.72055132, -0.68426837,
                1.07560947, -0.72491893, -0.59336155,  1.09926409, -0.69184339,
                -0.47611452};
        currentActivity = mPredictor.GetActivity(mActivityDetector, inputs);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mResultText.setText("Output: " + currentActivity.toString());

                switch (currentActivity.toString().toLowerCase()) {
                    case "downstairs":  mActivityImageView.setImageResource(R.drawable.downstairs);
                        break;
                    case "jogging":   mActivityImageView.setImageResource(R.drawable.jogging);
                        break;
                    case "sitting":   mActivityImageView.setImageResource(R.drawable.sitting);
                        break;
                    case "standing":   mActivityImageView.setImageResource(R.drawable.standing);
                        break;
                    case "upstairs":  mActivityImageView.setImageResource(R.drawable.upstairs);
                        break;
                    case "walking":   mActivityImageView.setImageResource(R.drawable.walking);
                        break;
                    default:  mActivityImageView.setImageResource(R.drawable.unknown);
                        break;
                }

            }
        });
    }




    private void SetupTimerForAPICall(){
        /***
         * Set the timer for 10 Seconds. So after each 10 seconds it take an average of the sensorDataArray and use it to make predictions
         *
         */
        //
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //TestAPICall();
                String data = SensorDataArray.toString();
                JSONArray predictionInputArray = SensorDataArray;
                double tempMeanX = meanX;
                double tempMeanY = meanY;
                double tempMeanZ = meanZ;
                int count = SensorDataArray.length();
                PredictionSample temp = SerializationUtils.clone(mPredictionSample);
                //PredictionSample temp = mPredictionSample;
                isReset = true;
                try {
                    PredictActivity(temp);
                    //PredictActivity(tempMeanX, tempMeanY, tempMeanZ, count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Log.v(TAG," Sensor Data : " + data);
                //SaveToKinesisFireHose(data);
                //SaveToDb(data);
            }
        }, 20000, 20000);


    }

    private void PredictActivity(PredictionSample sample){

        if(sample != null && sample.Count() > 0 ){
            currentActivity = mPredictor.GetActivity(mActivityDetector, sample.GetSample1());
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mResultText.setText("Output: " + currentActivity.toString());

                switch (currentActivity.toString().toLowerCase()) {
                    case "downstairs":  mActivityImageView.setImageResource(R.drawable.downstairs);
                        break;
                    case "jogging":   mActivityImageView.setImageResource(R.drawable.jogging);
                        break;
                    case "sitting":   mActivityImageView.setImageResource(R.drawable.sitting);
                        break;
                    case "standing":   mActivityImageView.setImageResource(R.drawable.standing);
                        break;
                    case "upstairs":  mActivityImageView.setImageResource(R.drawable.upstairs);
                        break;
                    case "walking":   mActivityImageView.setImageResource(R.drawable.walking);
                        break;
                    default:  mActivityImageView.setImageResource(R.drawable.unknown);
                        break;
                }

            }
        });

    }


//    private void PredictActivity(double x, double y, double z, int count) throws JSONException {
//
//        if(count > 0){
//            double input_x = x / (double) count;
//            double input_y = y / (double) count;
//            double input_z = z / (double) count;
//
//
//            double input[] = {input_x, input_y, input_z};
//            Log.v(TAG, "Count : " + count + " " +  input_x + " " + input_y + " " + input_z);
//
//            currentActivity = mPredictor.GetActivity(mActivityDetector, sample.GetSample1());;
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    mResultText.setText("Output: " + currentActivity);
//                }
//            });
//        }
//    }




//    public String getActivity(ActivityDetector detector, double[] inputs) {
//        //Call the activity detection
//        int result = detector.getActivityClass(inputs);
//        String finalactivity;
//
//        String[] activities = {"downstairs","jogging","sitting","standing","upstairs","walking"};
//        if (result == -1){
//            finalactivity = "Not able to Analyze";
//        }
//        else{
//            finalactivity = activities[result];
//        }
//        return finalactivity;
//    }


   protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //ImageView iv = (ImageView)findViewById(R.id.image);
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        if (!mInitialized) {
            mLastX = x;
            mLastY = y;
            mLastZ = z;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvX.setText("0.0");
                    tvY.setText("0.0");
                    tvZ.setText("0.0");
                }
            });


            mInitialized = true;
        } else {
            deltaX = Math.abs(mLastX - x);
            deltaY = Math.abs(mLastY - y);
            deltaZ = Math.abs(mLastZ - z);
            if (deltaX < NOISE) deltaX = (float)0.0;
            if (deltaY < NOISE) deltaY = (float)0.0;
            if (deltaZ < NOISE) deltaZ = (float)0.0;

            JSONObject currentSensorData =  new JSONObject();
            try {
                if(Math.abs(mLastX - x) > NOISE && Math.abs(mLastY - y) > NOISE && Math.abs(mLastZ -z) > NOISE){

                    meanX = meanX + deltaX;
                    meanY = meanY + deltaY;
                    meanZ = meanZ + deltaZ;

                    currentSensorData.put("FieldX", deltaX);
                    currentSensorData.put("FieldY", deltaY);
                    currentSensorData.put("FieldZ", deltaZ);

                    mPredictionSample.AddAccelerometerX(deltaX);
                    mPredictionSample.AddAccelerometerY(deltaY);
                    mPredictionSample.AddAccelerometerZ(deltaZ);

                    if(isReset == true){
                        SensorDataArray = new JSONArray();
                        meanX = 0.0;
                        meanY = 0.0;
                        meanZ = 0.0;
                        mPredictionSample = new PredictionSample();
                        isReset = false;


                    }
                    SensorDataArray.put(currentSensorData.toString());
                }


            } catch (JSONException e) {
                Log.e(TAG, "Error adding to sensor array:");
                e.printStackTrace();
            }


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvX.setText(Float.toString(deltaX));
                    tvY.setText(Float.toString(deltaY));
                    tvZ.setText(Float.toString(deltaZ));
                }
            });

            mLastX = x;
            mLastY = y;
            mLastZ = z;




        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
