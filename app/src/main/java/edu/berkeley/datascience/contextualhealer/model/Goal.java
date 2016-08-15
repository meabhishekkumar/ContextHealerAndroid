package edu.berkeley.datascience.contextualhealer.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import edu.berkeley.datascience.contextualhealer.R;

public class Goal  implements Parcelable {
//    ----------------------------------------------------------------------------
//    Table: Goal
//    ----------------------------------------------------------------------------
//    mGoalID : int : AutoIncrement
//    mGoalTitle : Text : 200
//    mGoalType : Text : 100 : Running, Jogging, Walking ( fixed options)
//    mGoalDurationInMinutes : Int : Number of minutes the activity to be performed
//    mGoalDescription : Text : 400 : Summary of Goal : Jogging for 10 mins
//    mGoalDays : What are days we need to track : Format : MON,TUE,WED,THR
//    mGoalStartTime : Text : 24 hour format : 08:00
//    mGoalEndTime: Text : 24 Hour Format : 15:00
//    mIsGoalCurrentlyTracked : Int : 0 or 1 : 1 means true, 0 means false
//    mIsGoalDeleted : Int 0 or 1
//    mCompletedDurationInMinutes : int : Number of minutes already performed for the given block
//    CompletedPercentage : int : PercentageOfCompletion

    private int mGoalID;
    private String mGoalTitle;
    private String mGoalType;
    private int mGoalDurationInMinutes;
    private String mGoalDescription;
    private String mGoalDays;
    private String mGoalStartTime;
    private String mGoalEndTime;
    private String mGoalStartEndCombinedString;
    private int mIsGoalCurrentlyTracked;
    private int mIsGoalDeleted;
    private int mCompletedDurationInMinutes;
    private int CompletedPercentage;
    private int mGoalImageID;


    //Constructor
    public Goal(int goalID, String goalTitle, String goalType, int goalDurationInMinutes,
                String goalDays, String goalStartTime,
                String goalEndTime, int isGoalCurrentlyTracked, int isGoalDeleted){

        mGoalID = goalID;
        mGoalTitle = goalTitle;
        mGoalType = goalType;
        mGoalDurationInMinutes = goalDurationInMinutes;
        mGoalDays = goalDays;
        mGoalStartTime = goalStartTime;
        mGoalEndTime = goalEndTime;
        mIsGoalCurrentlyTracked = isGoalCurrentlyTracked;
        mIsGoalDeleted = isGoalDeleted;

    }

    //Constructor
    public Goal(String goalTitle, String goalType, int goalDurationInMinutes,
                String goalDays, String goalStartTime,
                String goalEndTime, int isGoalCurrentlyTracked, int isGoalDeleted){

        mGoalTitle = goalTitle;
        mGoalType = goalType;
        mGoalDurationInMinutes = goalDurationInMinutes;
        mGoalDays = goalDays;
        mGoalStartTime = goalStartTime;
        mGoalEndTime = goalEndTime;
        mIsGoalCurrentlyTracked = isGoalCurrentlyTracked;
        mIsGoalDeleted = isGoalDeleted;

    }
    // Setters and Getters

    public int getGoalID() {
        return mGoalID;
    }

    public void setGoalID(int goalID) {
        mGoalID = goalID;
    }

    public String getGoalTitle() {
        return mGoalTitle;
    }

    public void setGoalTitle(String goalTitle) {
        mGoalTitle = goalTitle;
    }

    public String getGoalType() {
        return mGoalType;
    }

    public void setGoalType(String goalType) {
        mGoalType = goalType;
    }

    public int getGoalDurationInMinutes() {
        return mGoalDurationInMinutes;
    }

    public void setGoalDurationInMinutes(int goalDurationInMinutes) {
        mGoalDurationInMinutes = goalDurationInMinutes;
    }

    public String getGoalDescription() {
        return String.format("%s for %d mins", mGoalType, mGoalDurationInMinutes);
    }

    public void setGoalDescription(String goalType, int goalDurationInMinutes) {
        mGoalDescription = String.format("%s for %d mins", mGoalType, mGoalDurationInMinutes);;
    }



    public String getGoalDays() {
        return mGoalDays;
    }

    public void setGoalDays(String goalDays) {
        mGoalDays = goalDays;
    }

    public String getGoalStartTime() {
        return mGoalStartTime;
    }

    public void setGoalStartTime(String goalStartTime) {
        mGoalStartTime = goalStartTime;
    }

    public String getGoalEndTime() {
        return mGoalEndTime;
    }

    public void setGoalEndTime(String goalEndTime) {
        mGoalEndTime = goalEndTime;
    }

    public String getGoalStartEndCombinedString() {
        return String.format("%s - %s", mGoalStartTime, mGoalEndTime);
    }

    public void setGoalStartEndCombinedString(String goalStartEndCombinedString) {
        mGoalStartEndCombinedString = goalStartEndCombinedString;
    }

    public int getIsGoalCurrentlyTracked() {
        return mIsGoalCurrentlyTracked;
    }

    public void setIsGoalCurrentlyTracked(int isGoalCurrentlyTracked) {
        mIsGoalCurrentlyTracked = isGoalCurrentlyTracked;
    }

    public int getIsGoalDeleted() {
        return mIsGoalDeleted;
    }

    public void setIsGoalDeleted(int isGoalDeleted) {
        mIsGoalDeleted = isGoalDeleted;
    }

    public int getCompletedDurationInMinutes() {
        return mCompletedDurationInMinutes;
    }

    public void setCompletedDurationInMinutes(int completedDurationInMinutes) {
        mCompletedDurationInMinutes = completedDurationInMinutes;
    }

    public int getCompletedPercentage() {
        return CompletedPercentage;
    }

    public void setCompletedPercentage(int completedPercentage) {
        CompletedPercentage = completedPercentage;
    }

    public int getGoalImageID() {

        int tempGoalImageID = R.drawable.unknown;
        switch (mGoalType.toLowerCase()) {
            case "downstairs":  tempGoalImageID = R.drawable.downstairs;
                break;
            case "jogging":  tempGoalImageID = R.drawable.jogging;
                break;
            case "sitting":  tempGoalImageID = R.drawable.sitting;
                break;
            case "standing":  tempGoalImageID = R.drawable.standing;
                break;
            case "staircase":  tempGoalImageID = R.drawable.upstairs;
                break;
            case "upstairs":  tempGoalImageID = R.drawable.upstairs;
                break;
            case "walking":  tempGoalImageID = R.drawable.walking;
                break;
            case "unknown":  tempGoalImageID = R.drawable.unknown;
                break;
            case "stairs":  tempGoalImageID = R.drawable.upstairs;
                break;
            default: tempGoalImageID = R.drawable.unknown;
                break;
        }


        return tempGoalImageID;
    }

    public void setGoalImageID(String goalType) {
        int tempGoalImageID = R.drawable.unknown;
        switch (mGoalType.toLowerCase()) {
            case "downstairs":  tempGoalImageID = R.drawable.downstairs;
                break;
            case "jogging":  tempGoalImageID = R.drawable.jogging;
                break;
            case "sitting":  tempGoalImageID = R.drawable.sitting;
                break;
            case "standing":  tempGoalImageID = R.drawable.standing;
                break;
            case "upstairs":  tempGoalImageID = R.drawable.upstairs;
                break;
            case "walking":  tempGoalImageID = R.drawable.walking;
                break;
            case "unknown":  tempGoalImageID = R.drawable.unknown;
                break;
            case "stairs":  tempGoalImageID = R.drawable.upstairs;
                break;
            default: tempGoalImageID = R.drawable.unknown;
                break;
        }

        mGoalImageID = tempGoalImageID;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(mGoalID);
        dest.writeString(mGoalTitle);
        dest.writeString(mGoalType);
        dest.writeInt(mGoalDurationInMinutes);
        dest.writeString(mGoalDays);
        dest.writeString(mGoalStartTime);
        dest.writeString(mGoalEndTime);
        dest.writeInt(mIsGoalCurrentlyTracked);
        dest.writeInt(mIsGoalDeleted);
    }

    private Goal(Parcel in) {
        mGoalID = in.readInt();
        mGoalTitle = in.readString();
        mGoalType = in.readString();
        mGoalDurationInMinutes = in.readInt();
        mGoalDays = in.readString();
        mGoalStartTime = in.readString();
        mGoalEndTime = in.readString();
        mIsGoalCurrentlyTracked = in.readInt();
        mIsGoalDeleted = in.readInt();
    }

    public static final Creator<Goal> CREATOR = new Creator<Goal>() {
        @Override
        public Goal createFromParcel(Parcel source) {
            return new Goal(source);
        }

        @Override
        public Goal[] newArray(int size) {
            return new Goal[size];
        }
    };
}
