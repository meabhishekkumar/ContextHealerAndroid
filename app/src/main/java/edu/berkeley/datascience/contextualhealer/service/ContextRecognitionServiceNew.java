package edu.berkeley.datascience.contextualhealer.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.apache.commons.lang3.SerializationUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.activity.ActivityDetector;
import edu.berkeley.datascience.contextualhealer.activity.ActivityType;
import edu.berkeley.datascience.contextualhealer.activity.OnDevicePredictor;
import edu.berkeley.datascience.contextualhealer.app.MainActivity;
import edu.berkeley.datascience.contextualhealer.database.GoalDataSource;
import edu.berkeley.datascience.contextualhealer.interfaces.IPredictor;
import edu.berkeley.datascience.contextualhealer.model.ActivitySample;
import edu.berkeley.datascience.contextualhealer.model.PredictionSample;

public class ContextRecognitionServiceNew extends Service implements SensorEventListener {

    // For Service
    private static final String TAG = ContextRecognitionServiceNew.class.getSimpleName();
    public static final String NOTIFY_ACTIVITY_CHANGE = "NOTIFY_ACTIVITY_CHANGE";
    public static final String NOTIFY_CURRENT_ACTIVITY = "NOTIFY_CURRENT_ACTIVITY";
    private static final int REQUEST_OPEN = 99; // To open the activity from notification bar
    private IBinder mBinder = new LocalBinder();
    private Boolean mBackgroundServiceRunning = false;  // Boolean to check if the background service is running
    private Boolean mTrackSensorChange = true; // whether the sensor is tracked or not
    private int mSamplesBatchSize = 1; // How many samples to be predicted in one batch
    private int SensorBlockInSeconds = 5; // Each sample will contain how many seconds sample
    private int TimerIntervalInMilliSeconds = 50;  // Retrieve data at each 50 milisecond
    private PredictionSample mPredictionSample; // One Prediction Sample
    private ArrayList<PredictionSample> mPredictionSamples; // List of samples
    private ActivityDetector mActivityDetector = new ActivityDetector(); // Activity Detector
    private IPredictor mPredictor = new OnDevicePredictor(); // Instance of Predictor Class
    private ActivityType currentActivity = ActivityType.unknown; // Current Activity


    // For sensors
    private float mLastX, mLastY, mLastZ; // Last position of X, Y, Z
    private float deltaX, deltaY, deltaZ; // Change from last X, Y, Z
    private final float NOISE = (float) 0.00001; //What change in accelerometer will be treated as Noise
    private boolean mSensorInitialized; // Check if the Sensor is initialized or not
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float AccelerometerX;
    private float AccelerometerY;
    private float AccelerometerZ;
    private long StartTime;
    private long EndTime;


    // Setters and Getters
    public float getAccelerometerX() {
        return AccelerometerX;
    }

    public void setAccelerometerX(float accelerometerX) {
        AccelerometerX = accelerometerX;
    }

    public float getAccelerometerY() {
        return AccelerometerY;
    }

    public void setAccelerometerY(float accelerometerY) {
        AccelerometerY = accelerometerY;
    }

    public float getAccelerometerZ() {
        return AccelerometerZ;
    }

    public void setAccelerometerZ(float accelerometerZ) {
        AccelerometerZ = accelerometerZ;
    }


    @Override
    public void onCreate() {

        Log.v(TAG, "On Create");
        //For sample
        mPredictionSample = new PredictionSample();
        mPredictionSamples = new ArrayList<PredictionSample>();

        // Activity Detector Setup
        boolean ret = mActivityDetector.setup(getApplicationContext());
        if( !ret ) {
            Log.v(TAG, "Detector setup failed");
            return;
        }
        else{
            Log.v(TAG, "Activity Tracking Model Setup");
        }

        //Sensor manager setup
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        StartTime = System.currentTimeMillis();
        EndTime = System.currentTimeMillis() + 1000 * SensorBlockInSeconds;

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
                //At interval get the current
                float current_X = AccelerometerX;
                float current_Y = AccelerometerY;
                float current_Z = AccelerometerZ;
            }
        }, TimerIntervalInMilliSeconds, TimerIntervalInMilliSeconds);

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //TODO : Work on the Notification Builder

        // TODO: Check on other options for STICK attribute
        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "On Bind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG, "On Unbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "On Destroy");
    }


    public class LocalBinder extends Binder {
        public ContextRecognitionServiceNew getService(){
            return ContextRecognitionServiceNew.this;
        }
    }
    //Client (other Activities)
    public void startTracking(){


        Intent mainIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_OPEN, mainIntent, 0);

        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("GoalTick")
                .setContentText("Click to stop tracking your goals.")
                .setContentIntent(pendingIntent);

        //notificationBuilder.setAutoCancel(true);
        Notification notification = notificationBuilder.build();
        startForeground(11, notification);

        Log.v(TAG, "Goal Tracking Started.");
        mBackgroundServiceRunning = true;
    }

    public void pauseTracking(){
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
        nMgr.cancelAll();
        Log.v(TAG, "Goal Tracking Paused.");
        mBackgroundServiceRunning = false;
    }

    public Boolean isTracking(){
        return mBackgroundServiceRunning;
    }

    // Sensor Work
    @Override
    public void onSensorChanged(SensorEvent event) {

        if(mBackgroundServiceRunning && mTrackSensorChange){

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            if (!mSensorInitialized) {
                mLastX = x;
                mLastY = y;
                mLastZ = z;
                mSensorInitialized = true;
            } else {
                deltaX = Math.abs(mLastX - x);
                deltaY = Math.abs(mLastY - y);
                deltaZ = Math.abs(mLastZ - z);
                if (deltaX < NOISE) deltaX = (float)0.0;
                if (deltaY < NOISE) deltaY = (float)0.0;
                if (deltaZ < NOISE) deltaZ = (float)0.0;

                try {
                    //Log.v(TAG, "Sensor Changed");
                    StartTime = System.currentTimeMillis();
                    if(Math.abs(mLastX - x) > NOISE && Math.abs(mLastY - y) > NOISE && Math.abs(mLastZ -z) > NOISE){
                        //Log.v(TAG, "Field X: " + deltaX + " Field Y:" + deltaY + "  Field Z:" + deltaZ);
                        if(EndTime > StartTime){
                            //set the values
                            setAccelerometerX(x);
                            setAccelerometerY(y);
                            setAccelerometerZ(z);
                            // Add to the collection
                            mPredictionSample.AddTimeStamp(StartTime);
                            mPredictionSample.AddAccelerometerX(x);
                            mPredictionSample.AddAccelerometerY(y);
                            mPredictionSample.AddAccelerometerZ(z);

                        }
                        else{

                            // When sample is gathered. Send it to activity prediction pool
                            PredictionSample temp = SerializationUtils.clone(mPredictionSample);

                            // It number of samples in the bucket is less than equal to number of samples to predict
                            if(mPredictionSamples.size() <= mSamplesBatchSize){
                                mPredictionSamples.add(temp);
                            }
                            else{
                                //Reset the pool
                                mPredictionSamples.clear();
                                mPredictionSamples.add(temp);
                                // Send the Pool for predictions
                                try {
                                    PredictBatchActivity(mPredictionSamples);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            // Create a New Sample
                            mPredictionSample = new PredictionSample();
                            StartTime = System.currentTimeMillis();;
                            EndTime = StartTime + 1000 * SensorBlockInSeconds;
                            mPredictionSample.setM_SampleStartTime(StartTime);
                            mPredictionSample.setM_SampleEndTime(EndTime);
                        }
                    }


                } catch (Exception e) {
                    Log.e(TAG, "Error in service OnSensorChanged");
                    e.printStackTrace();
                }
                mLastX = x;
                mLastY = y;
                mLastZ = z;
            }
        }
    }

    private void PredictBatchActivity(List<PredictionSample> samples){
        String currentActivity = "Unknown";
        for (PredictionSample sample : samples){
        // For each sample do the prediction
          currentActivity =  PredictActivity(sample);
        }

        Log.v(TAG, "Latest Activity " + currentActivity.toString());
        SendActivityBroadcast(currentActivity.toString());


    }

    private void SendActivityBroadcast(String activity){
        //Broadcast
        Intent localIntent = new Intent(NOTIFY_ACTIVITY_CHANGE);
        localIntent.putExtra(NOTIFY_CURRENT_ACTIVITY, activity);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(localIntent);
    }

    private String PredictActivity(PredictionSample sample){

        if(sample != null && sample.Count() > 0 ){
            ActivityType activity = mPredictor.GetActivity(mActivityDetector, sample.GetSample2());

            // Save to Activity Sample Database
            SaveActivitySampleToDb(sample,activity);
            Log.v(TAG, "Start: " + sample.getM_SampleStartTime() + "  End : " + sample.getM_SampleEndTime() + " Count : " +  sample.Count() + " Activity :" + activity.toString());

        }

        return currentActivity.toString();
    }

    private  void SaveActivitySampleToDb(PredictionSample predictionSample, ActivityType activityType){

        //Prepare Data to insert
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");  //ISO 8601 format
        int durationInMilliSecs = SensorBlockInSeconds *1000;

        Date startTimestamp = new Date(predictionSample.getM_SampleStartTime());
        String start = formatter.format(startTimestamp);

        Date endTimestamp = new Date(predictionSample.getM_SampleEndTime());
        String end = formatter.format(endTimestamp);
        String currentActivity = activityType.toString();

        ActivitySample activitySample = new ActivitySample();
        activitySample.setStartTimeStamp(start);
        activitySample.setEndTimeStamp(end);
        activitySample.setActivityType(currentActivity);
        activitySample.setDurationInMilliSecs(durationInMilliSecs);


        //Save Activity Sample to the Database
        GoalDataSource dataSource = new GoalDataSource(getApplicationContext());
        dataSource.insertActivitySample(activitySample);

    }




    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




}
