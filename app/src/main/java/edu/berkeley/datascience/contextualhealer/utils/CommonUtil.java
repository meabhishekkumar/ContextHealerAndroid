package edu.berkeley.datascience.contextualhealer.utils;

import android.util.Log;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

import edu.berkeley.datascience.contextualhealer.model.ActivitySample;

public class CommonUtil {

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



}
