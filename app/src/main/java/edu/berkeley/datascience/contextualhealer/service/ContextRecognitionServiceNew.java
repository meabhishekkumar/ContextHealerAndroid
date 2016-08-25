package edu.berkeley.datascience.contextualhealer.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClient;
import com.amazonaws.services.kinesisfirehose.model.PutRecordBatchRequest;
import com.amazonaws.services.kinesisfirehose.model.Record;

import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import edu.berkeley.datascience.contextualhealer.ContextualHealerApplicationSettings;
import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.activity.ActivityDetector;
import edu.berkeley.datascience.contextualhealer.activity.ActivityType;
import edu.berkeley.datascience.contextualhealer.activity.OnDevicePredictor;
import edu.berkeley.datascience.contextualhealer.activity.PredictionMode;
import edu.berkeley.datascience.contextualhealer.app.MainActivity;
import edu.berkeley.datascience.contextualhealer.database.GoalDataSource;
import edu.berkeley.datascience.contextualhealer.interfaces.IPredictor;
import edu.berkeley.datascience.contextualhealer.model.ActivitySample;
import edu.berkeley.datascience.contextualhealer.model.Goal;
import edu.berkeley.datascience.contextualhealer.model.GoalCompletion;
import edu.berkeley.datascience.contextualhealer.model.PredictionSample;
import edu.berkeley.datascience.contextualhealer.utils.CommonUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.support.v4.app.ActivityCompat.requestPermissions;

public class ContextRecognitionServiceNew extends Service implements SensorEventListener {

    // For Service
    private static final String TAG = ContextRecognitionServiceNew.class.getSimpleName();
    public static final String NOTIFY_ACTIVITY_CHANGE = "NOTIFY_ACTIVITY_CHANGE";
    public static final String NOTIFY_CURRENT_ACTIVITY = "NOTIFY_CURRENT_ACTIVITY";
    private static final int REQUEST_OPEN = 99; // To open the activity from notification bar
    private static final int NOTIFY_SERVICE_STATE = 11;
    private IBinder mBinder = new LocalBinder();
    private Boolean mBackgroundServiceRunning = false;  // Boolean to check if the background service is running
    private Boolean mTrackSensorChange = true; // whether the sensor is tracked or not
    private int mSamplesBatchSize = 12; // How many samples to be predicted in one batch
    private int SensorBlockInSeconds = 5; // Each sample will contain how many seconds sample
    private int FrequencyOfSensorDataCollectionInMilliSeconds = 50; // sensor values will be collected at 50 ms interval
    private PredictionSample mPredictionSample = null; // One Prediction Sample
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
    private Handler sampleCollectionHandler = new Handler();


    //For Kinesis Stream
    private CognitoCachingCredentialsProvider credentialsProvider;
    private AmazonKinesisFirehoseClient firehoseClient;
    private final String deliveryStreamName = "abhi-kinesis-stream-1";
    private String deviceID;

    private Context mContext;






    @Override
    public void onCreate() {

        Log.v(TAG, "On Create");
        //For sample
        mPredictionSamples = new ArrayList<PredictionSample>();
        mContext = getApplicationContext();

        //Kinesis Stream
        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-east-1:1cf60624-e37d-4a26-944c-b40782a7d558", // Identity Pool ID
                Regions.US_EAST_1 // Region
        );
        //Unique Device ID
        deviceID = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);

        // AWS Firehose Setup
        firehoseClient = new AmazonKinesisFirehoseClient(credentialsProvider);


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


        //Set up timers
        //Handler for data collection
        mPredictionSample = null;
        sampleCollectionHandler.postDelayed(sampleCollectionRunnable, FrequencyOfSensorDataCollectionInMilliSeconds);


        //Timer for activity predictions
        //Timer for Kinesis Stream Data push
        //Timer for model update



    }

    private Runnable sampleCollectionRunnable = new Runnable() {
        @Override
        public void run() {

            if(mBackgroundServiceRunning){

                long currentTimeStamp = System.currentTimeMillis();

                //Get Sensor Data at 50ms
                double accel_x = getAccelerometerX();
                double accel_y = getAccelerometerY();
                double accel_z = getAccelerometerZ();

                if(mPredictionSample == null){
                    //If ran for the first time
                    mPredictionSample = new PredictionSample();
                    mPredictionSample.setM_SampleStartTime(currentTimeStamp);
                }

                // Add to the collection
                if(mPredictionSample.Count() < (int)(SensorBlockInSeconds * 1000/ FrequencyOfSensorDataCollectionInMilliSeconds) ){
                    //if hundred sensor points are not received in the sample then continue to add it to the sample

                    mPredictionSample.AddAccelerometerX(accel_x);
                    mPredictionSample.AddAccelerometerY(accel_y);
                    mPredictionSample.AddAccelerometerZ(accel_z);

                }
                else{
                    //if it has reached the 100 limit
                    //save the existing prediction samples. create a new prediction sample
                    //Set the endtime
                    mPredictionSample.setM_SampleEndTime(currentTimeStamp);
                    //Add sample to the prediction pool
                    mPredictionSamples.add(mPredictionSample);
                    //Log.v(TAG, "Start : " + mPredictionSample.getM_SampleStartTime() +  " End: " + mPredictionSample.getM_SampleEndTime()
                    //         + "  Count : " +  mPredictionSample.Count());
                    //Reset the sample
                    mPredictionSample = new PredictionSample();
                    mPredictionSample.setM_SampleStartTime(currentTimeStamp);
                }

                //If the prediction pool has reached its limits, send it for predictions on a separate thread
                if(mPredictionSamples.size() >= mSamplesBatchSize){
                    //Log.v(TAG, "Sent for batch prediction on separate thread with total samples : " + mPredictionSamples.size());
                    final ArrayList<PredictionSample> samplesToBeProcessed = SerializationUtils.clone(mPredictionSamples);
                    mPredictionSamples.clear();
                    Runnable runnable = new Runnable() {
                        public void run() {
                            PredictBatchActivity(samplesToBeProcessed);
                        }
                    };
                    Thread predictionThread = new Thread(runnable);
                    predictionThread.start();
                }

            }

            // Launch the handler again
            sampleCollectionHandler.postDelayed(this, FrequencyOfSensorDataCollectionInMilliSeconds);
        }
    };



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //TODO : Work on the Notification Builder
//        Intent mainIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_OPEN, mainIntent, 0);
//
//        Notification.Builder notificationBuilder = new Notification.Builder(this)
//                            .setSmallIcon(R.mipmap.ic_launcher)
//                            .setContentTitle("GoalTick")
//                            .setContentText("Click to stop tracking your goals.")
//                            .setContentIntent(pendingIntent);
//
//        //notificationBuilder.setAutoCancel(true);
//        Notification notification = notificationBuilder.build();
//        startForeground(11, notification);

        //Returning the Sticky so, that the service can be resumed even it is closed somehow
        return Service.START_STICKY;
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

    public static boolean isAvailable(Context ctx, Intent intent) {
        final PackageManager mgr = ctx.getPackageManager();
        List<ResolveInfo> list =
                mgr.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }


    //Client (other Activities)
    public void startTracking(){
        Intent mainIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_OPEN, mainIntent, 0);

        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.notification_target_small)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.target_grey))
                .setAutoCancel(false)
                .setContentTitle("GoalTick")
                .setContentText("Click to stop tracking your goals.")
                .setSubText("Tap to view goalTick app")
                .setContentIntent(pendingIntent);

        //notificationBuilder.setAutoCancel(true);
        Notification notification = notificationBuilder.build();
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFY_SERVICE_STATE, notification);
        startForeground(11, notification);
        Log.v(TAG, "Goal Tracking Started.");
        mBackgroundServiceRunning = true;
    }

    public void pauseTracking(){
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(ns);
        mNotificationManager.cancel(NOTIFY_SERVICE_STATE);
        stopForeground(true);
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

            //extract sensor value
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            //set the values
            setAccelerometerX(x);
            setAccelerometerY(y);
            setAccelerometerZ(z);


        }
    }

    private void PredictBatchActivity(List<PredictionSample> samples){
        Log.v(TAG, " Prediction batch size : " + samples.size());

        //Get SharedPreference from preferenceManager
        ContextualHealerApplicationSettings settings = new ContextualHealerApplicationSettings(mContext);

        Log.v(TAG, "Tracking Mode : " + settings.getTrackingModePreference());
        switch(settings.getTrackingModePreference()){
            case "TRACKING_LOCAL":
                Log.v(TAG, "Local Tracking");
                PredictUsingLocalMode(samples);
                break;
            case "TRACKING_SERVER":
                if(isNetworkAvailable()){
                    //If server mode is enabled and internet is available then use server mode
                    PredictUsingServerMode(samples);
                }
                else{
                    PredictUsingLocalMode(samples);
                }
                break;
            default:
                Log.v(TAG, "Local Tracking");
                PredictUsingLocalMode(samples);
                break;

        }

    }



    private void PredictUsingLocalMode(List<PredictionSample> samples){
        ArrayList<PredictionSample> streamSamples = new ArrayList<>();

        //Local prediction
        for (PredictionSample sample : samples){
            // For each sample do the prediction
            ActivityType currentActivity =  PredictActivity(sample);
            sample.setActivityType(currentActivity);
            streamSamples.add(sample);
        }

        SendToKinesisStream(streamSamples, PredictionMode.Local);
    }



    private void PredictUsingServerMode(final List<PredictionSample> samples){


      Log.v("API_TEST", "Inside prediction API call with total sample : " + samples.size());

      JSONArray jsonArray = new JSONArray();
      for (int i=0; i < samples.size(); i++) {
          jsonArray.put(CommonUtil.getJSONObjectForAPICall(samples.get(i)));
      }

      //Log.v("API_TEST", "JSON : " + jsonArray);
      //Log.v("API_TEST", "Array : " + jsonArray.toString());

      //writeToFile(jsonArray.toString(), mContext);
      //Call to API
      MediaType JSON = MediaType.parse("application/json; charset=utf-8");

      RequestBody body = RequestBody.create(JSON, jsonArray.toString());

        //writeToFile(jsonArray.toString(), mContext);

      OkHttpClient client = new OkHttpClient();
      String response = "";

      //Use 10.0.2.2 for localhost or 127.0.0.1 : for genymotion it 10.0.3.2
      String url = "http://ec2-54-174-44-241.compute-1.amazonaws.com:5000/predict?inbound_data_type=STRING";

      Request request = new Request.Builder()
              .url(url)
              .post(body)
              .build();

      Call call = client.newCall(request);

      call.enqueue(new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {
              Log.e("API_TEST", "onFailure() Request was:");
              //If failure on API call, resort to local mode
              Log.v("API_TEST", e.getStackTrace().toString());
              PredictUsingLocalMode(samples);

          }

          @Override
          public void onResponse(Call call, Response response) throws IOException {
              if(response.isSuccessful()){
                  String result = response.body().string();
                  //Log.e("API_TEST", "onResponse(): " + result );
                  //If response is successful the send it to the databse
                  ProcessPredictionAPIResponse(samples, result);
              }
              else{
                  Log.v("API_TEST", "Wrong response code, default to Local Mode");
                  PredictUsingLocalMode(samples);
              }


          }

      });

  }

    private void ProcessPredictionAPIResponse(final List<PredictionSample> samples, String response) {
        ArrayList<PredictionSample> streamSamples = new ArrayList<>();

        String[] activities = response.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        for(int i = 0; i < samples.size(); i++){

            PredictionSample sample = samples.get(i);
            ActivityType activity = CommonUtil.getActivityTypeFromString(activities[i]);
            sample.setActivityType(activity);
            streamSamples.add(sample);
            //Log.v("API_TEST", " Activity String " + activities[i] + " Type: " + activity);
            // Save to Activity Sample Database
            SaveActivitySampleToDb(sample,activity);
            UpdateGoalCompletion(sample, activity);
            Log.v("API_TEST", "Mode: Server : Start: " + sample.getM_SampleStartTime() + "  End : " + sample.getM_SampleEndTime() + " Count : " +  sample.Count() + " Activity :" + activity.toString());

        }

        SendToKinesisStream(streamSamples, PredictionMode.Server);


    }

    private void writeToFile(String data,Context context) {


        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(path, "sample_data.txt");
        try {
            FileOutputStream stream = new FileOutputStream(file, true);
            stream.write(data.getBytes());
            stream.close();
            Log.i("API_TEST", "Data Saved");
        } catch (IOException e) {
            Log.e("API_TEST DATA", "Could not write file " + e.getMessage());
        }

    }

    private boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected())
        {
            isAvailable = true;

        }
        return isAvailable;
    }


    private ActivityType PredictActivity(PredictionSample sample){

        if(sample != null && sample.Count() > 0 ){
            ActivityType activity = mPredictor.GetActivity(mActivityDetector, sample.GetSample2());
            sample.setActivityType(activity);

            // Save to Activity Sample Database
            SaveActivitySampleToDb(sample,activity);
            UpdateGoalCompletion(sample, activity);
            Log.v(TAG, "Start: " + sample.getM_SampleStartTime() + "  End : " + sample.getM_SampleEndTime() + " Count : " +  sample.Count() + " Activity :" + activity.toString());

        }

        return sample.getActivityType();
    }


    private void SendToKinesisStream(ArrayList<PredictionSample> samples, PredictionMode mode){

        //Get SharedPreference from preferenceManager
        ContextualHealerApplicationSettings settings = new ContextualHealerApplicationSettings(mContext);
        boolean isKinesisStreamEnabled = settings.getEnableKinesisStreamPreference();
        Log.v(TAG, "Enable Kinesis Stream : " + isKinesisStreamEnabled);

        if(isKinesisStreamEnabled && isNetworkAvailable()){
            try{
                PutRecordBatchRequest putRecordBatchRequest = new PutRecordBatchRequest();
                putRecordBatchRequest.setDeliveryStreamName(deliveryStreamName);
                List<Record> recordList = new ArrayList<Record>();

                for(PredictionSample sample: samples){
                    ArrayList<Double> arr_accel_x = sample.getM_AccelerometerX();
                    ArrayList<Double> arr_accel_y = sample.getM_AccelerometerY();
                    ArrayList<Double> arr_accel_z = sample.getM_AccelerometerZ();
                    int recordCount = 0;
                    for (int i = 0; i < sample.Count(); i++) {
                        recordCount = recordCount + 1;
                        String data = deviceID + "," + sample.getM_SampleStartTime() + "," + sample.getM_SampleEndTime() + ","
                                + arr_accel_x.get(i) + "," + arr_accel_y.get(i) + "," + arr_accel_z.get(i) + "," + sample.getActivityType().toString() + "," + mode.toString() + "\r\n";
                        Record record = new Record();
                        ByteBuffer buff = ByteBuffer.wrap(data.getBytes());
                        record.setData(buff);
                        recordList.add(record);
                        //Log.e("KINESIS_STREAM", "KinesisStream:" + data);
                    }
                    // Put Record Batch records. Max No.Of Records we can put in a
                    // single put record batch request is 500
                    putRecordBatchRequest.setRecords(recordList);
                    firehoseClient.putRecordBatch(putRecordBatchRequest);
                    recordList.clear();
                }


            }catch (Exception e){
                Log.v(TAG, " Error in Kinesis stream. Exception log: " + e.getStackTrace().toString());

            }



        }


    }




    private void UpdateGoalCompletion(PredictionSample sample, ActivityType activity) {
        //Based on the goals set: update the database

        //Get all the goals
        GoalDataSource datasource = new GoalDataSource(getApplicationContext());
        ArrayList<Goal> goals = datasource.readActiveGoals();

        //Iterate with all goals
        for(Goal goal: goals){

            //Check if the goal will be updated or not
            // if the activity type and goal type is not same then dont need to do anything
            // if they are the same then check if the goal timing is suited to update or not
            if((goal.getIsGoalCurrentlyTracked() == 1) &&  CommonUtil.IsActivityTypeSameAsGoalType(goal.getGoalType(), activity.toString()) && goal.IsGoalToBeUpdated()){
                int goalID = goal.getGoalID();
                Log.v("UPDATE_DB", "current goal " + goal.getGoalTitle());
                Log.v("UPDATE_DB","IsActivitySame: " +  CommonUtil.IsActivityTypeSameAsGoalType(goal.getGoalType(), activity.toString()) + " ");
                Log.v("UPDATE_DB", "IsGoalToBeUpdated" + goal.IsGoalToBeUpdated() + " " );

                String currentDate = CommonUtil.GetCurrentDateString();
                float completionPercentage = 0.0f;
                if(goal.getGoalDurationInMinutes() > 0){
                    completionPercentage = 100.0f * (SensorBlockInSeconds /(float) (goal.getGoalDurationInMinutes() * 60));
                }

                String goalType = goal.getGoalType();
                GoalCompletion existingGoalCompletionRow = datasource.readGoalCompletionIDDateWise(goalID, currentDate);

                if(existingGoalCompletionRow != null){
                    //Update the completion percentage if completion percentage for (GOAL_ID, GOAL_DATE) pair is already available in the databse
                    //Add the existing the completion percentage with new
                    float existingCompletionPercentage = existingGoalCompletionRow.getGoalCompletionPercentage();
                    Log.v("UPDATE_DB", "Existing Completion Percentage : " + existingCompletionPercentage + " for goal ID " + goalID);
                    completionPercentage = existingCompletionPercentage + completionPercentage;
                    Log.v("UPDATE_DB", "Updated Completion Percentage : " + completionPercentage + " for goal ID " + goalID);
                    if(completionPercentage < 100.0f){

                        Log.v("UPDATE_DB", "Updated...");

                        // if completion exceeds 100 then do nothing, else update the value
                        GoalCompletion goalCompletion = new GoalCompletion(goalID, currentDate, completionPercentage, goalType);
                        datasource.updateGoalCompletionPercentage(goalCompletion);

                    }

                }
                else{
                    //Insert the new completion percentage
                    Log.v("UPDATE_DB", "inserted ");
                    GoalCompletion goalCompletion = new GoalCompletion(goalID, currentDate, completionPercentage, goalType);
                    datasource.insertGoalCompletion(goalCompletion);
                }


            }
        }



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


}
