package edu.berkeley.datascience.contextualhealer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.activity.ActivityType;
import edu.berkeley.datascience.contextualhealer.model.ActivitySummary;
import edu.berkeley.datascience.contextualhealer.model.Goal;
import edu.berkeley.datascience.contextualhealer.model.GoalCompletion;

public class GoalDataSource {

    private Context mContext;
    private GoalSQLiteHelper mGoalSQLiteHelper;

    public GoalDataSource(Context context){
        mContext = context;
        mGoalSQLiteHelper = new GoalSQLiteHelper(context);
        //For initial seeding
        //SQLiteDatabase database = mGoalSQLiteHelper.getReadableDatabase();
        //database.close();
    }

    private SQLiteDatabase  open(){
        return mGoalSQLiteHelper.getWritableDatabase();
    }

    private  void close(SQLiteDatabase database){
        database.close();
    }


    //Read Operation
    public ArrayList<Goal> readActiveGoals(){
        //return  getMockedActiveGoals();


        SQLiteDatabase database = open();
        Cursor cursor = database.query(
                GoalSQLiteHelper.GOALS_TABLE,
                new String[] {BaseColumns._ID,
                        GoalSQLiteHelper.COLUMN_GOAL_TITLE,
                        GoalSQLiteHelper.COLUMN_GOAL_TYPE,
                        GoalSQLiteHelper.COLUMN_GOAL_DURATION_IN_MINUTES,
                        GoalSQLiteHelper.COLUMN_GOAL_DAYS,
                        GoalSQLiteHelper.COLUMN_GOAL_START_TIME,
                        GoalSQLiteHelper.COLUMN_GOAL_END_TIME,
                        GoalSQLiteHelper.COLUMN_IS_GOAL_CURRENTLY_TRACKED,
                        GoalSQLiteHelper.COLUMN_IS_GOAL_DELETED
                },
                null, // selection
                null, // selection args
                null, // group by
                null, // having
                null); //order
        ArrayList<Goal> activeGoals = new ArrayList<Goal>();
        if(cursor.moveToFirst()){
            do{
                int GoalID = getIntFromColumnName(cursor, BaseColumns._ID);
                Goal goal = new Goal(getIntFromColumnName(cursor, BaseColumns._ID),
                        getStringFromColumnName(cursor, GoalSQLiteHelper.COLUMN_GOAL_TITLE),
                        getStringFromColumnName(cursor, GoalSQLiteHelper.COLUMN_GOAL_TYPE),
                        getIntFromColumnName(cursor, GoalSQLiteHelper.COLUMN_GOAL_DURATION_IN_MINUTES),
                        getStringFromColumnName(cursor, GoalSQLiteHelper.COLUMN_GOAL_DAYS),
                        getStringFromColumnName(cursor, GoalSQLiteHelper.COLUMN_GOAL_START_TIME),
                        getStringFromColumnName(cursor, GoalSQLiteHelper.COLUMN_GOAL_END_TIME),
                        getIntFromColumnName(cursor, GoalSQLiteHelper.COLUMN_IS_GOAL_CURRENTLY_TRACKED),
                        getIntFromColumnName(cursor, GoalSQLiteHelper.COLUMN_IS_GOAL_DELETED));

                //TODO: WORK ON COMPLETION PERCENTAGE
                goal.setCompletedPercentage(60);
                activeGoals.add(goal);

            }while(cursor.moveToNext());
        }
        cursor.close();
        close(database);
        return activeGoals;
    }


    //Create a new goal
    public void create(Goal goal){

        SQLiteDatabase database = open();

        database.beginTransaction();

        ContentValues goalValues = new ContentValues();
        goalValues.put(GoalSQLiteHelper.COLUMN_GOAL_TITLE, goal.getGoalTitle());
        goalValues.put(GoalSQLiteHelper.COLUMN_GOAL_TYPE, goal.getGoalType());
        goalValues.put(GoalSQLiteHelper.COLUMN_GOAL_DURATION_IN_MINUTES, goal.getGoalDurationInMinutes());
        goalValues.put(GoalSQLiteHelper.COLUMN_GOAL_DAYS, goal.getGoalDays());
        goalValues.put(GoalSQLiteHelper.COLUMN_GOAL_START_TIME, goal.getGoalStartTime());
        goalValues.put(GoalSQLiteHelper.COLUMN_GOAL_END_TIME, goal.getGoalEndTime());
        goalValues.put(GoalSQLiteHelper.COLUMN_IS_GOAL_CURRENTLY_TRACKED, goal.getIsGoalCurrentlyTracked());
        goalValues.put(GoalSQLiteHelper.COLUMN_IS_GOAL_DELETED, goal.getIsGoalDeleted());

        long goalID = database.insert(GoalSQLiteHelper.GOALS_TABLE,null,goalValues);

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);

    }

    //Update Operation
    public void update(Goal goal){
        SQLiteDatabase database = open();
        database.beginTransaction();

        ContentValues updateGoalValues = new ContentValues();

        updateGoalValues.put(GoalSQLiteHelper.COLUMN_GOAL_TITLE, goal.getGoalTitle());
        updateGoalValues.put(GoalSQLiteHelper.COLUMN_GOAL_TYPE, goal.getGoalType());
        updateGoalValues.put(GoalSQLiteHelper.COLUMN_GOAL_DURATION_IN_MINUTES, goal.getGoalDurationInMinutes());
        updateGoalValues.put(GoalSQLiteHelper.COLUMN_GOAL_DAYS, goal.getGoalDays());
        updateGoalValues.put(GoalSQLiteHelper.COLUMN_GOAL_START_TIME, goal.getGoalStartTime());
        updateGoalValues.put(GoalSQLiteHelper.COLUMN_GOAL_END_TIME, goal.getGoalEndTime());
        updateGoalValues.put(GoalSQLiteHelper.COLUMN_IS_GOAL_CURRENTLY_TRACKED, goal.getIsGoalCurrentlyTracked());
        updateGoalValues.put(GoalSQLiteHelper.COLUMN_IS_GOAL_DELETED, goal.getIsGoalDeleted());
        database.update(GoalSQLiteHelper.GOALS_TABLE,
                updateGoalValues,
                String.format("%s=%d", BaseColumns._ID, goal.getGoalID()),null);

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }


    public void delete(int goalId){
        SQLiteDatabase database = open();
        database.beginTransaction();

        // delete goal
        database.delete(GoalSQLiteHelper.GOALS_TABLE,
                String.format("%s=%s", BaseColumns._ID, String.valueOf(goalId)), null);


        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }


    public ArrayList<ActivitySummary> readActivitySummary(){

       String query =  "SELECT " +  GoalSQLiteHelper.COLUMN_ACTIVITY_SAMPLES_ACTIVITY_TYPE + ", " +
                "SUM(" +   GoalSQLiteHelper.COLUMN_ACTIVITY_SAMPLES_DURATION_IN_MILLI_SECS +
                 ")  / (60*1000) " + GoalSQLiteHelper.COLUMN_ACTIVITY_SAMPLES_TOTAL_DURATION_IN_MINS  +" from "+ GoalSQLiteHelper.ACTIVITY_SAMPLES_TABLE + " group by " +
               GoalSQLiteHelper.COLUMN_ACTIVITY_SAMPLES_ACTIVITY_TYPE ;

        SQLiteDatabase database = open();
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<ActivitySummary> activitySummaries = new ArrayList<ActivitySummary>();

        if(cursor.moveToFirst()){
            do{

                ActivitySummary summary = new ActivitySummary(
                        getStringFromColumnName(cursor, GoalSQLiteHelper.COLUMN_ACTIVITY_SAMPLES_ACTIVITY_TYPE),
                        getIntFromColumnName(cursor, GoalSQLiteHelper.COLUMN_ACTIVITY_SAMPLES_TOTAL_DURATION_IN_MINS));
                activitySummaries.add(summary);
            }while(cursor.moveToNext());
        }
        cursor.close();
        close(database);
        return activitySummaries;
    }


    public int readGoalsSetCount(){

        int setGoalsCount = 0;
        String query =  "SELECT count(*) Total_Count from "+ GoalSQLiteHelper.GOALS_COMPLETION_TABLE ;
        SQLiteDatabase database = open();
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<ActivitySummary> activitySummaries = new ArrayList<ActivitySummary>();

        if(cursor.moveToFirst()){
            do{
                setGoalsCount = getIntFromColumnName(cursor, "Total_Count");
            }while(cursor.moveToNext());
        }
        cursor.close();
        close(database);
        return setGoalsCount;
    }


    public float readGoalsAverageCompletionPercentage(){

        float avgCompletion = 0.0f;
        String query =  "SELECT avg(" +  GoalSQLiteHelper.COLUMN_COMPLETION_TABLE_GOAL_COMPLETION_PERCENTAGE  + ") Avg_Completion from "+ GoalSQLiteHelper.GOALS_COMPLETION_TABLE ;
        SQLiteDatabase database = open();
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<ActivitySummary> activitySummaries = new ArrayList<ActivitySummary>();

        if(cursor.moveToFirst()){
            do{
                avgCompletion = getFloatFromColumnName(cursor, "Avg_Completion");
            }while(cursor.moveToNext());
        }
        cursor.close();
        close(database);
        return avgCompletion;
    }

    public ArrayList<GoalCompletion> readGoalCompletionByType(){

        String query =  "select GOAL_TYPE, avg(GOAL_COMPLETION_PERCENTAGE) Avg_Completion from GOALS_COMPLETION Group by GOAL_TYPE" ;

        SQLiteDatabase database = open();
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<GoalCompletion> result = new ArrayList<GoalCompletion>();

        if(cursor.moveToFirst()){
            do{

                GoalCompletion summary = new GoalCompletion(
                        getFloatFromColumnName(cursor,"Avg_Completion"),
                        getStringFromColumnName(cursor, GoalSQLiteHelper.COLUMN_COMPLETION_TABLE_GOAL_TYPE)
                        );
                result.add(summary);
            }while(cursor.moveToNext());
        }
        cursor.close();
        close(database);
        return result;
    }






    //Helpers
    private int getIntFromColumnName(Cursor cursor, String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getInt(columnIndex);
    }

    private String getStringFromColumnName(Cursor cursor, String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getString(columnIndex);
    }

    private float getFloatFromColumnName(Cursor cursor, String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getFloat(columnIndex);
    }





    // Mocked Data
    public static ArrayList<Goal> getMockedActiveGoals() {

        ArrayList<Goal> dataList = new ArrayList<>();

        //Activity - 1
        Goal goal_1 = new Goal(1, "Morning Jogging",ActivityType.jogging.toString(),20, "MON,TUE,WED","07:00", "08:00", 1,0);
        goal_1.setCompletedPercentage(70);

        Goal goal_2 = new Goal(2, "Walking to Office",ActivityType.walking.toString(),10, "WED,FRI","09:00", "10:00", 1,0);
        goal_2.setCompletedPercentage(50);


        Goal goal_3 = new Goal(3, "Use Stairs",ActivityType.staircase.toString(),10, "SAT,SUN","10:00", "22:00", 1,0);
        goal_3.setCompletedPercentage(20);


        dataList.add(goal_1);
        dataList.add(goal_2);
        dataList.add(goal_3);



        return dataList;
    }

}
