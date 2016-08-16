package edu.berkeley.datascience.contextualhealer.model;

import edu.berkeley.datascience.contextualhealer.R;

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

    public int getActivityColor(String activityType){
        int color = R.color.logoSecondaryColor;
        switch (activityType){
            case "downstairs":
                color = R.color.color_activity_downstairs;
                break;
            case "jogging":  color = R.color.color_activity_jogging;
                break;
            case "sitting":  color = R.color.color_activity_sitting;
                break;
            case "standing":   color = R.color.color_activity_standing;
                break;
            case "upstairs":  color = R.color.color_activity_upstairs;
                break;
            case "staircase":  color = R.color.color_activity_upstairs;
                break;
            case "walking":   color = R.color.color_activity_walking;
                break;
            default:  color = R.color.color_activity_unknown;
                break;

        }

        return color;
    }

}
