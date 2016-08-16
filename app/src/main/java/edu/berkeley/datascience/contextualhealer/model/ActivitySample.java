package edu.berkeley.datascience.contextualhealer.model;

import edu.berkeley.datascience.contextualhealer.R;

public class ActivitySample {

    private String mStartTimeStamp;
    private String mEndTimeStamp;
    private int mDurationInMilliSecs;
    private String mActivityType;

    public ActivitySample(){


    }
    public ActivitySample(String startTimeStamp, String endTimeStamp, int durationInMilliSecs, String activityType){
        mStartTimeStamp = startTimeStamp;
        mEndTimeStamp = endTimeStamp;
        mDurationInMilliSecs = durationInMilliSecs;
        mActivityType = activityType;
    }

    public String getStartTimeStamp() {
        return mStartTimeStamp;
    }

    public void setStartTimeStamp(String startTimeStamp) {
        mStartTimeStamp = startTimeStamp;
    }

    public String getActivityType() {
        return mActivityType;
    }

    public void setActivityType(String activityType) {
        mActivityType = activityType;
    }

    public int getDurationInMilliSecs() {
        return mDurationInMilliSecs;
    }

    public void setDurationInMilliSecs(int durationInMilliSecs) {
        mDurationInMilliSecs = durationInMilliSecs;
    }

    public String getEndTimeStamp() {
        return mEndTimeStamp;
    }

    public void setEndTimeStamp(String endTimeStamp) {
        mEndTimeStamp = endTimeStamp;
    }

    public int getActivityIcon(String activityType){
        int icon = R.drawable.unknown;
        switch (activityType){
            case "downstairs":
                icon = R.drawable.downstairs;
                break;
            case "jogging":  icon = R.drawable.jogging;
                break;
            case "sitting":  icon = R.drawable.sitting;
                break;
            case "standing":   icon = R.drawable.standing;
                break;
            case "upstairs":  icon = R.drawable.upstairs;
                break;
            case "walking":   icon = R.drawable.walking;
                break;
            case "staircase":   icon = R.drawable.staircase;
                break;
            default:  icon =R.drawable.unknown;
                break;

        }

        return icon;
    }

}
