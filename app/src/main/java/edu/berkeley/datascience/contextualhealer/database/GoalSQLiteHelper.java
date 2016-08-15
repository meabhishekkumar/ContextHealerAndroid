package edu.berkeley.datascience.contextualhealer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.activity.ActivityType;
import edu.berkeley.datascience.contextualhealer.model.ActivitySample;
import edu.berkeley.datascience.contextualhealer.model.Goal;
import edu.berkeley.datascience.contextualhealer.model.GoalCompletion;

public class GoalSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "goaltick.db"; //Database Name
    private static final int DB_VERSION = 1; // Database version
    private static Random rand = new Random();

    //Table: Goals
//    GoalID : int : AutoIncrement
//    GoalTitle : Text : 200
//    GoalType : Text : 100 : Running, Jogging, Walking ( fixed options)
//    GoalDurationInMinutes : Int : Number of minutes the activity to be performed
//    GoalDescription : Text : 400 : Summary of Goal : Jogging for 10 mins
//    GoalDays : What are days we need to track : Format : MON,TUE,WED,THR
//    GoalStartTime : Text : 24 hour format : 08:00
//    GoalEndTime: Text : 24 Hour Format : 15:00
//    IsGoalCurrentlyTracked : Int : 0 or 1 : 1 means true, 0 means false
//    IsGoalDeleted : Int 0 or 1
//    CompletedDurationInMinutes : int : Number of minutes already performed for the given block
//    CompletedPercentage : int : PercentageOfCompletion


    //Table Name
    public static final String GOALS_TABLE = "GOALS";
    //Columns
    public static final String COLUMN_GOAL_TITLE = "GOAL_TITLE";
    public static final String COLUMN_GOAL_TYPE = "GOAL_TYPE";
    public static final String COLUMN_GOAL_DURATION_IN_MINUTES = "GOAL_DURATION_IN_MINUTES";
    public static final String COLUMN_GOAL_DAYS = "GOAL_DAYS";
    public static final String COLUMN_GOAL_START_TIME = "GOAL_START_TIME";
    public static final String COLUMN_GOAL_END_TIME = "GOAL_END_TIME";
    public static final String COLUMN_IS_GOAL_CURRENTLY_TRACKED = "IS_GOAL_CURRENTLY_TRACKED";
    public static final String COLUMN_IS_GOAL_DELETED = "IS_GOAL_DELETED";


    private static String CREATE_GOALS =
            "CREATE TABLE "+ GOALS_TABLE + "(" +  BaseColumns._ID +" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    COLUMN_GOAL_TITLE + " TEXT," +
                    COLUMN_GOAL_TYPE + " TEXT," +
                    COLUMN_GOAL_DURATION_IN_MINUTES + " INTEGER," +
                    COLUMN_GOAL_DAYS + " TEXT," +
                    COLUMN_GOAL_START_TIME + " TEXT," +
                    COLUMN_GOAL_END_TIME + " TEXT," +
                    COLUMN_IS_GOAL_CURRENTLY_TRACKED + " INTEGER," +
                    COLUMN_IS_GOAL_DELETED + " INTEGER)";


    //Table Name
    public static final String GOALS_COMPLETION_TABLE = "GOALS_COMPLETION";
    //Columns
    public static final String COLUMN_COMPLETION_TABLE_GOAL_ID = "GOALS_ID";
    public static final String COLUMN_COMPLETION_TABLE_GOAL_DATE = "GOAL_DATE";
    public static final String COLUMN_COMPLETION_TABLE_GOAL_COMPLETION_PERCENTAGE = "GOAL_COMPLETION_PERCENTAGE";
    public static final String COLUMN_COMPLETION_TABLE_GOAL_TYPE = "GOAL_TYPE";

    public static final String COLUMN_FOREIGN_KEY_GOALS = "GOALS_ID";

    private static String CREATE_GOALS_COMPLETION =
            "CREATE TABLE "+ GOALS_COMPLETION_TABLE + "( "  +  COLUMN_COMPLETION_TABLE_GOAL_ID + " INTEGER," +
                    COLUMN_COMPLETION_TABLE_GOAL_DATE + " TEXT," +
                    COLUMN_COMPLETION_TABLE_GOAL_COMPLETION_PERCENTAGE + " REAL," +
                    COLUMN_COMPLETION_TABLE_GOAL_TYPE + " TEXT," +
                    " PRIMARY KEY (" + COLUMN_COMPLETION_TABLE_GOAL_ID + " , " + COLUMN_COMPLETION_TABLE_GOAL_DATE + " )," +
                    " FOREIGN KEY (" + COLUMN_FOREIGN_KEY_GOALS + ") REFERENCES GOALS(_ID))";



    //Table Name : ActivitySample
    public static final String ACTIVITY_SAMPLES_TABLE = "ACTIVITY_SAMPLES";
    //Columns
    public static final String COLUMN_ACTIVITY_SAMPLES_START_TIME_STAMP= "START_TIME_STAMP";
    public static final String COLUMN_ACTIVITY_SAMPLES_END_TIME_STAMP = "END_TIME_STAMP";
    public static final String COLUMN_ACTIVITY_SAMPLES_DURATION_IN_MILLI_SECS = "DURATION_IN_MILLI_SECS";
    public static final String COLUMN_ACTIVITY_SAMPLES_ACTIVITY_TYPE = "ACTIVITY_TYPE";

    //Computed Columns
    public static final String COLUMN_ACTIVITY_SAMPLES_TOTAL_DURATION_IN_MINS = "TOTAL_DURATION_IN_MINS";

    private static String CREATE_ACTIVITY_SAMPLES =
            "CREATE TABLE "+ ACTIVITY_SAMPLES_TABLE + "( " +
                    COLUMN_ACTIVITY_SAMPLES_START_TIME_STAMP + " TEXT," +
                    COLUMN_ACTIVITY_SAMPLES_END_TIME_STAMP + " TEXT," +
                    COLUMN_ACTIVITY_SAMPLES_DURATION_IN_MILLI_SECS + " INT," +
                    COLUMN_ACTIVITY_SAMPLES_ACTIVITY_TYPE + " TEXT" +
                    ")";


    //Meme Table Annotations functionality
//    public static final String ANNOTATIONS_TABLE = "ANNOTATIONS";
//    public static final String COLUMN_ANNOTATION_COLOR = "COLOR";
//    public static final String COLUMN_ANNOTATION_X = "X";
//    public static final String COLUMN_ANNOTATION_Y = "Y";
//    public static final String COLUMN_ANNOTATION_TITLE = "TITLE";
//    public static final String COLUMN_FOREIGN_KEY_MEME = "MEME_ID";
//
//    private static final String CREATE_ANNOTATIONS = "CREATE TABLE " + ANNOTATIONS_TABLE + " (" +
//            BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//            COLUMN_ANNOTATION_X + " INTEGER, " +
//            COLUMN_ANNOTATION_Y + " INTEGER, " +
//            COLUMN_ANNOTATION_TITLE + " TEXT, " +
//            COLUMN_ANNOTATION_COLOR + " TEXT, " +
//            COLUMN_FOREIGN_KEY_MEME + " INTEGER, " +
//            "FOREIGN KEY(" + COLUMN_FOREIGN_KEY_MEME + ") REFERENCES MEMES(_ID))";


    //Meme Table Annotations functionality

    public GoalSQLiteHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Executed first time
        db.execSQL(CREATE_GOALS);
        db.execSQL(CREATE_ACTIVITY_SAMPLES);
        db.execSQL(CREATE_GOALS_COMPLETION);

        SeedDatabase(db);
        //db.execSQL(CREATE_ANNOTATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // at the time of upgrade

    }


    private void SeedDatabase(SQLiteDatabase db){
        // Seed the Goals Table


        db.beginTransaction();

        // For each goal in the mocked goals list
        for(Goal goal : getMockedActiveGoals()){
            ContentValues goalValues = new ContentValues();
            goalValues.put(COLUMN_GOAL_TITLE, goal.getGoalTitle());
            goalValues.put(COLUMN_GOAL_TYPE, goal.getGoalType());
            goalValues.put(COLUMN_GOAL_DURATION_IN_MINUTES, goal.getGoalDurationInMinutes());
            goalValues.put(COLUMN_GOAL_DAYS, goal.getGoalDays());
            goalValues.put(COLUMN_GOAL_START_TIME, goal.getGoalStartTime());
            goalValues.put(COLUMN_GOAL_END_TIME, goal.getGoalEndTime());
            goalValues.put(COLUMN_IS_GOAL_CURRENTLY_TRACKED, goal.getIsGoalCurrentlyTracked());
            goalValues.put(COLUMN_IS_GOAL_DELETED, goal.getIsGoalDeleted());


            long goalID = db.insert(GOALS_TABLE,null,goalValues);


        }


        // For each activity sample in mocked activity sample list

        for(ActivitySample sample : getMockedActivitySamples()){

            ContentValues sampleValues = new ContentValues();
            sampleValues.put(COLUMN_ACTIVITY_SAMPLES_START_TIME_STAMP, sample.getStartTimeStamp());
            sampleValues.put(COLUMN_ACTIVITY_SAMPLES_END_TIME_STAMP, sample.getEndTimeStamp());
            sampleValues.put(COLUMN_ACTIVITY_SAMPLES_DURATION_IN_MILLI_SECS, sample.getDurationInMilliSecs());
            sampleValues.put(COLUMN_ACTIVITY_SAMPLES_ACTIVITY_TYPE,sample.getActivityType());

            db.insert(ACTIVITY_SAMPLES_TABLE,null,sampleValues);

        }


        // Add Mocked GoalCompletions



        for(GoalCompletion goalCompletion: getMockedGoalCompletions()){
            ContentValues sampleValues = new ContentValues();
            sampleValues.put(COLUMN_COMPLETION_TABLE_GOAL_ID, goalCompletion.getGoalID());
            sampleValues.put(COLUMN_COMPLETION_TABLE_GOAL_DATE, goalCompletion.getGoalDate());
            sampleValues.put(COLUMN_COMPLETION_TABLE_GOAL_COMPLETION_PERCENTAGE, goalCompletion.getGoalCompletionPercentage());
            sampleValues.put(COLUMN_COMPLETION_TABLE_GOAL_TYPE,goalCompletion.getGoalType());

            db.insert(GOALS_COMPLETION_TABLE,null,sampleValues);
        }


        db.setTransactionSuccessful();
        db.endTransaction();
        //db.close();
    }

    public static ArrayList<Goal> getMockedActiveGoals() {

        ArrayList<Goal> dataList = new ArrayList<>();

        //Activity - 1
        Goal goal_1 = new Goal(1, "Morning Jogging",ActivityType.jogging.toString(),20, "DAILY","07:00", "08:00", 1,0);
        goal_1.setCompletedPercentage(70);
        Goal goal_2 = new Goal(2, "Walking to Office",ActivityType.walking.toString(),10, "DAILY","09:00", "10:00", 1,0);
        goal_2.setCompletedPercentage(50);
        Goal goal_3 = new Goal(3, "Use Stairs",ActivityType.staircase.toString(),10, "NEVER","10:00", "22:00", 1,0);
        goal_3.setCompletedPercentage(20);
        dataList.add(goal_1);
        dataList.add(goal_2);
        dataList.add(goal_3);

        return dataList;
    }

    public static ArrayList<GoalCompletion> getMockedGoalCompletions() {

        ArrayList<GoalCompletion> dataList = new ArrayList<>();
        //Today Date

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  //ISO 8601 format
        Calendar c = Calendar.getInstance();
        Date day1 = new Date(System.currentTimeMillis());
        c.setTime(day1);
        String day1_str = formatter.format(c.getTime());

        c.add(Calendar.DATE, 1); // Adding 1 day
        String day2_str = formatter.format(c.getTime());

        c.add(Calendar.DATE, 1); // Adding 1 day
        String day3_str = formatter.format(c.getTime());



        dataList.add(new GoalCompletion(1,day1_str,76.0f,"jogging"));
        dataList.add(new GoalCompletion(1,day2_str,70.0f,"jogging"));
        dataList.add(new GoalCompletion(1,day3_str,10.0f,"jogging"));

        dataList.add(new GoalCompletion(2,day1_str,30.0f,"walking"));
        dataList.add(new GoalCompletion(2,day2_str,40.0f,"walking"));
        dataList.add(new GoalCompletion(2,day3_str,66.0f,"walking"));

        dataList.add(new GoalCompletion(3,day1_str,10.0f,"staircase"));
        dataList.add(new GoalCompletion(3,day2_str,20.0f,"staircase"));
        dataList.add(new GoalCompletion(3,day3_str,42.0f,"staircase"));


        return dataList;
    }



    public static ArrayList<ActivitySample> getMockedActivitySamples(){
        ArrayList<ActivitySample> data = new ArrayList<>();
        long currentTime = System.currentTimeMillis();
        rand.setSeed(100);

        String[] activities = {"downstairs","jogging","sitting","upstairs","walking","unknown","standing"};
        for (int i=0; i<1000; i++){
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");  //ISO 8601 format
            int durationInMilliSecs = 5 *1000;

            Date startTimestamp =new Date(currentTime);
            String start = formatter.format(startTimestamp);

            Date endTimestamp =new Date(currentTime + durationInMilliSecs);
            String end = formatter.format(endTimestamp);

            String currentActivity = activities[randInt(0,activities.length -1)];

            currentTime = currentTime + durationInMilliSecs;
            data.add(new ActivitySample(start, end, durationInMilliSecs, currentActivity));
        }
        //data.add(new ActivitySample())

        return data;
    }

    public static int randInt(int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }


}
