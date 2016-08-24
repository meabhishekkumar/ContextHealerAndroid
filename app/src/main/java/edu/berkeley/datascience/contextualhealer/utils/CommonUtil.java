package edu.berkeley.datascience.contextualhealer.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

import edu.berkeley.datascience.contextualhealer.activity.ActivityType;
import edu.berkeley.datascience.contextualhealer.model.ActivitySample;
import edu.berkeley.datascience.contextualhealer.model.PredictionSample;

public class CommonUtil {

    private static final String TAG = CommonUtil.class.getSimpleName();

    public static Date ParseTimeString(String timeString){
        //parse the timestring in HH:mm format to date
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = sdf.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    public static Boolean IsSameDay(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        return sameDay;
    }


    public static Date ParseDateString(String dateString){
        //parse the date string format to date : yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    public static Date ParseTimeStampString(String timeStampString){
        //parse the date string format to date : yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date date = null;
        try {
            date = sdf.parse(timeStampString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    public static Boolean IsBetweenLastOneHour(Date timeStamp){
        Calendar currentTimeStamp = Calendar.getInstance();
        currentTimeStamp.setTime(new Date(System.currentTimeMillis()));
        Date currentTime = currentTimeStamp.getTime();

        //Subtract one hour from current time
        currentTimeStamp.add(Calendar.HOUR, -1);
        Date oneHourBack = currentTimeStamp.getTime();

        return oneHourBack.compareTo(timeStamp) * timeStamp.compareTo(currentTime) >= 0;
    }


    public static Boolean IsCurrentDateBetweenStartAndEnd(Date start, Date end){
        //Check if current time is between start and end
        Date current = new Date(System.currentTimeMillis());
        return start.compareTo(current) * current.compareTo(end) >= 0;
    }

    public static String GetCurrentDateString(){
        //Get the current date in ISO 8601 format
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  //ISO 8601 format
        Calendar c = Calendar.getInstance();
        Date day = new Date(System.currentTimeMillis());
        c.setTime(day);
        String day_str = formatter.format(c.getTime());
        return day_str;
    }

    public static Date GetCurrentDate(){
        Date day =  new Date(System.currentTimeMillis());
        return  day;
    }

    public static int GetCurrentDayOfWeek(){
        Date date =  new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return  dayOfWeek;
    }

    public static int GetCurrentDayOfMonth(){
        Date date =  new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.DAY_OF_MONTH);
        return  month;
    }

    public static int GetCurrentMonth(){
        Date date =  new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        return  month;
    }

    public static int GetDayOfWeek(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return  dayOfWeek;
    }

    public static int GetDayOfMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.DAY_OF_MONTH);
        return  month;
    }

    public static int GetMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        return  month;
    }


    public static boolean IsActivityTypeSameAsGoalType(String goalType, String activityType){
        //Currently the activity supported are jogging, walking and staircase

        Log.v("UPDATE_DB", "Goal Type : " + goalType + " Activity type " + activityType);
      boolean result = false;
        if(goalType.equals("jogging") && activityType.equals("jogging")){
            result = true;
            return  result;
        }

        if(goalType.equals("walking") && activityType.equals("walking")){
            result = true;
            return  result;
        }

        if(goalType.equals("staircase") && activityType.equals("upstairs")){
            result = true;
            return  result;
        }

        if(goalType.equals("staircase") && activityType.equals("downstairs")){
            result = true;
            return  result;
        }

       return  result;
    }


    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }


    public static ArrayList<ActivitySample> GetActivitySampleForTimeLine(ArrayList<ActivitySample> samples){

        // sort the collection in reverse order
        Collections.sort(samples, new Comparator<ActivitySample>() {
            public int compare(ActivitySample o1, ActivitySample o2) {
                return o2.getEndTimeStampInDate().compareTo(o1.getEndTimeStampInDate());
            }
        });

        ArrayList<ActivitySample> finalSamples = new ArrayList<ActivitySample>();
        // Iterate through it. if the activity is changed then only add it to final list

        String currentActivity = null;
        for(ActivitySample sample: samples){

            if(currentActivity == null || !sample.getActivityType().equals(currentActivity)){
                finalSamples.add(sample);
                currentActivity = sample.getActivityType();
            }
        }

        return  finalSamples;
    }

    public static String GetTimeStampInLocalTimeZone(Date timestamp){
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        cal.setTime(timestamp);
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        formatter.setCalendar(cal);
        formatter.setTimeZone(tz);
        return formatter.format(cal.getTime());
    }

    public static String getDoubleArray(ArrayList<Double> items){
        float[] result  = new float[items.size()];
        int count = 0;
        for(Double item: items){
            result[count] = item.floatValue();
            count = count + 1;
            //Log.v("API_TEST", "Value : " + item.floatValue());
        }
        //Log.v("API_TEST", "result : " + Arrays.toString(result));
        return  Arrays.toString(result);
    }

    public static JSONObject getJSONObjectForAPICall(PredictionSample sample){
        JSONObject obj = new JSONObject();
        try {

            //Log.v("API_TEST","xACC" +  CommonUtil.getDoubleArray(sample.getM_AccelerometerX()));
            obj.put("xAcc", CommonUtil.getDoubleArray(sample.getM_AccelerometerX()));
            obj.put("yAcc", CommonUtil.getDoubleArray(sample.getM_AccelerometerY()));
            obj.put("zAcc", CommonUtil.getDoubleArray(sample.getM_AccelerometerZ()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  obj;
    }

    public static ActivityType getActivityTypeFromString(String activityStr){
        activityStr = activityStr.replaceAll("^\"|\"$", "").toLowerCase();
        ActivityType activity  = null;
        switch (activityStr.toLowerCase()) {
            case "downstairs":  activity = ActivityType.downstairs;
                break;
            case "jogging":  activity = ActivityType.jogging;
                break;
            case "sitting":  activity = ActivityType.sitting;
                break;
            case "standing":  activity = ActivityType.standing;
                break;
            case "upstairs":  activity = ActivityType.upstairs;
                break;
            case "walking":  activity = ActivityType.walking;
                break;
            default: activity = ActivityType.unknown;
                break;
        }
        return  activity;
    }


    public static double[] getScaledData(double[] data, double[] means, double[] stds) {
        double[] result = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = (data[i] - means[i])/ stds[i];
        }
        return result;
    }
}
