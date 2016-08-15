package edu.berkeley.datascience.contextualhealer.model;

public class ActivitySample {

    private String mStartTimeStamp;
    private String mEndTimeStamp;
    private int mDurationInMilliSecs;
    private String mActivityType;

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



}
