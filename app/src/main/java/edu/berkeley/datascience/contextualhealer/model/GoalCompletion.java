package edu.berkeley.datascience.contextualhealer.model;

public class GoalCompletion  {
    private int mGoalID;
    private String mGoalDate;
    private float mGoalCompletionPercentage;
    private String mGoalType;

    public GoalCompletion(float goalCompletionPercentage, String goalType){
        mGoalCompletionPercentage = goalCompletionPercentage;
        mGoalType = goalType;
    }


    public GoalCompletion(int goalID, String goalDate, float goalCompletionPercentage, String goalType){
        mGoalID = goalID;
        mGoalDate = goalDate;
        mGoalCompletionPercentage = goalCompletionPercentage;
        mGoalType = goalType;
    }

    public int getGoalID() {
        return mGoalID;
    }

    public void setGoalID(int goalID) {
        mGoalID = goalID;
    }

    public String getGoalDate() {
        return mGoalDate;
    }

    public void setGoalDate(String goalDate) {
        mGoalDate = goalDate;
    }

    public float getGoalCompletionPercentage() {
        return mGoalCompletionPercentage;
    }

    public void setGoalCompletionPercentage(float goalCompletionPercentage) {
        mGoalCompletionPercentage = goalCompletionPercentage;
    }

    public String getGoalType() {
        return mGoalType;
    }

    public void setGoalType(String goalType) {
        mGoalType = goalType;
    }



}
