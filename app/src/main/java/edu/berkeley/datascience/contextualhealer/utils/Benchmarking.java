package edu.berkeley.datascience.contextualhealer.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

//import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.berkeley.datascience.contextualhealer.activity.ActivityDetector;
import edu.berkeley.datascience.contextualhealer.activity.ActivityType;
import edu.berkeley.datascience.contextualhealer.activity.OnDevicePredictor;
import edu.berkeley.datascience.contextualhealer.interfaces.IPredictor;

public class Benchmarking {

//    private static final String TAG = Benchmarking.class.getSimpleName() ;
//    private  Context mContext;
//    public Benchmarking(Context context){
//        mContext = context;
//    }
//
//    public void RunBenchMarking(){
//        List<double[]> inputs = readCsv(mContext, "test_input.csv");
//        List<double[]> outputs = readCsv(mContext, "test_output.csv");
//
//        Log.v(TAG, " inputs size : " + inputs.size() + " Output size: "+ outputs.size());
//
//        //Setup the activity detector
//        // Activity Detector Setup
//        ActivityDetector mActivityDetector = new ActivityDetector(); // Activity Detector
//        IPredictor mPredictor = new OnDevicePredictor(); // Instance of Predictor Class
//        boolean ret = mActivityDetector.setup(mContext);
//        if( !ret ) {
//            Log.v(TAG, "Detector setup failed");
//            return;
//        }
//        else{
//            Log.v(TAG, "Activity Tracking Model Setup");
//        }
//
//        double[] test = outputs.get(0);
//        Log.v(TAG, "actual results : " + Arrays.toString(test) +" Index :" +  GetIndex(test));
//
//
//        int count = 0;
//        for (int i = 0; i < inputs.size(); i++) {
//            double[] sample = inputs.get(i);
//            int result = mActivityDetector.getActivityClass(sample.length, sample);
//            ActivityType activity;
//
//            switch (result) {
//                case 0:  activity = ActivityType.downstairs;
//                    break;
//                case 1:  activity = ActivityType.jogging;
//                    break;
//                case 2:  activity = ActivityType.sitting;
//                    break;
//                case 3:  activity = ActivityType.standing;
//                    break;
//                case 4:  activity = ActivityType.upstairs;
//                    break;
//                case 5:  activity = ActivityType.walking;
//                    break;
//                default: activity = ActivityType.unknown;
//                    break;
//            }
//
//
//
//            double[] actualResult = outputs.get(i);
//            //Log.v(TAG, "actual results : " + Arrays.toString(actualResult));
//            int Index = GetIndex(actualResult);
//            String[] activities = {"downstairs", "jogging","sitting","standing","upstairs","walking"};
//            String actualActivity = activities[Index];
//            if(activity.toString().equals(actualActivity)){
//                count = count + 1;
//            }
//            //Log.v(TAG, "Sample : " + i + " Predicted : "+ activity + " Actual : "+ actualActivity);
//
//        }
//
//        Log.v(TAG, "Total : " + inputs.size() + " Matched : " + count + " Accuracy :" + count*100/(float)inputs.size());
//
//
//    }
//
//
//    private int GetIndex(double[] values){
//        int result = -1;
//        for (int i = 0; i < values.length; i++) {
//            if(values[i] > 0.0){
//                result = i;
//            }
//        }
//        return  result;
//    }
//
//    public final List<double[]> readCsv(Context context, String CSV_PATH) {
//        Log.v(TAG, "Reading the file :" + CSV_PATH);
//
//        List<double[]> result = new ArrayList<double[]>();
//        AssetManager assetManager = context.getAssets();
//
//        try {
//            InputStream csvStream = assetManager.open(CSV_PATH);
//            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
//            CSVReader csvReader = new CSVReader(csvStreamReader);
//            String[] line;
//
//            // throw away the header
//            //csvReader.readNext(); As we do not have any header
//            int count = 1;
//            while ((line = csvReader.readNext()) != null) {
//
//                String lineString = Arrays.toString(line);
//                String[] numberStrs = lineString.replaceAll("[\\[\\](){}]","").split(",");
//
//                double[] numbers = new double[numberStrs.length];
//                for(int i = 0;i < numberStrs.length;i++)
//                {
//                    // Note that this is assuming valid input
//                    // If you want to check then add a try/catch
//                    // and another index for the numbers if to continue adding the others
//                    numbers[i] = Double.parseDouble(numberStrs[i]);
//                }
//
//                //Log.v(TAG, "Line Number : " + count + " Current Line : " + numbers[0]);
//                result.add(numbers);
//                count = count + 1;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
}
