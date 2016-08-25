package edu.berkeley.datascience.contextualhealer.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.utils.CommonUtil;

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
    private String mGoalRepeatType;
    private String mGoalRepeatPattern;
    private String mGoalStartTime;
    private String mGoalEndTime;
    private String mGoalSetDate;
    private String mGoalStartEndCombinedString;
    private int mIsGoalCurrentlyTracked;
    private int mIsGoalDeleted;
    private int mCompletedDurationInMinutes;
    private float CompletedPercentage;
    private int mGoalImageID;



    //Constructor
    public Goal(int goalID, String goalTitle, String goalType, int goalDurationInMinutes,
                String goalRepeatType, String goalRepeatPattern, String goalStartTime,
                String goalEndTime, String goalSetDate, int isGoalCurrentlyTracked, int isGoalDeleted){

        mGoalID = goalID;
        mGoalTitle = goalTitle;
        mGoalType = goalType;
        mGoalDurationInMinutes = goalDurationInMinutes;
        mGoalRepeatType = goalRepeatType;
        mGoalRepeatPattern = goalRepeatPattern;
        mGoalStartTime = goalStartTime;
        mGoalEndTime = goalEndTime;
        mGoalSetDate = goalSetDate;
        mIsGoalCurrentlyTracked = isGoalCurrentlyTracked;
        mIsGoalDeleted = isGoalDeleted;

    }

    //Constructor
    public Goal(String goalTitle, String goalType, int goalDurationInMinutes,
                String goalRepeatType, String goalRepeatPattern, String goalStartTime,
                String goalEndTime, String goalSetDate, int isGoalCurrentlyTracked, int isGoalDeleted){

        mGoalTitle = goalTitle;
        mGoalType = goalType;
        mGoalDurationInMinutes = goalDurationInMinutes;
        mGoalRepeatType = goalRepeatType;
        mGoalRepeatPattern = goalRepeatPattern;
        mGoalStartTime = goalStartTime;
        mGoalEndTime = goalEndTime;
        mGoalSetDate = goalSetDate;
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

    public String getGoalRepeatPattern() { return mGoalRepeatPattern; }

    public void setGoalRepeatPattern(String goalRepeatPattern) { mGoalRepeatPattern = goalRepeatPattern; }

    public String getGoalRepeatType() { return mGoalRepeatType; }

    public void setGoalRepeatType(String goalRepeatType) { mGoalRepeatType = goalRepeatType; }


    public void setGoalDurationInMinutes(int goalDurationInMinutes) {
        mGoalDurationInMinutes = goalDurationInMinutes;
    }

    public String getGoalDescription() {
        return String.format("%s for %d mins", mGoalType, mGoalDurationInMinutes);
    }

    public void setGoalDescription(String goalType, int goalDurationInMinutes) {
        mGoalDescription = String.format("%s for %d mins", mGoalType, mGoalDurationInMinutes);;
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

    public String getGoalSetDate() {
        return mGoalSetDate;
    }

    public void setGoalSetDate(String goalSetDate) {
        mGoalSetDate = goalSetDate;
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

    public float getCompletedPercentage() {
        return CompletedPercentage;
    }

    public void setCompletedPercentage(float completedPercentage) {
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
        dest.writeString(mGoalRepeatType);
        dest.writeString(mGoalRepeatPattern);
        dest.writeString(mGoalStartTime);
        dest.writeString(mGoalEndTime);
        dest.writeString(mGoalSetDate);
        dest.writeInt(mIsGoalCurrentlyTracked);
        dest.writeInt(mIsGoalDeleted);
    }

    private Goal(Parcel in) {
        mGoalID = in.readInt();
        mGoalTitle = in.readString();
        mGoalType = in.readString();
        mGoalDurationInMinutes = in.readInt();
        mGoalRepeatType = in.readString();
        mGoalRepeatPattern = in.readString();
        mGoalStartTime = in.readString();
        mGoalEndTime = in.readString();
        mGoalSetDate = in.readString();
        mIsGoalCurrentlyTracked = in.readInt();
        mIsGoalDeleted = in.readInt();
    }


    public Boolean IsGoalToBeUpdated() {
        Boolean result = true;
        if(mIsGoalCurrentlyTracked == 0){
            //If the goal is not getting tracked : user has turned it off manually : then don't update
            return false;
        }

        Date goalSetDate = CommonUtil.ParseDateString(mGoalSetDate);
        Date startTimeStamp = CommonUtil.ParseTimeString(mGoalStartTime);
        Date endTimeStamp = CommonUtil.ParseTimeString(mGoalEndTime);

        //if the goal is set to be tracked. Then look at the repeat type
        if(mGoalRepeatType.equals("NEVER") ){
            // if the date of goal creation is same as today : i.e. goal has been created today only : track it else leave it
            if(CommonUtil.IsSameDay(goalSetDate, CommonUtil.GetCurrentDate())){
                // if it is the same day then check if the current time is between goal start time and end time

                result = CommonUtil.IsCurrentDateBetweenStartAndEnd(startTimeStamp, endTimeStamp);
                return result;
            }
            else{
                result = false;
                return  result;
            }
        }

        if(mGoalRepeatType.equals("DAILY")){
            // if the repeat type is DAILY then simply check if the current time is between goal start and end time
            result = CommonUtil.IsCurrentDateBetweenStartAndEnd(startTimeStamp, endTimeStamp);
            return result;
        }

        if(mGoalRepeatType.equals("WEEKLY")){
            // if the repeat type is Weekly, then check if the current day is same as the day of goal creation
            if(CommonUtil.GetCurrentDayOfWeek() == CommonUtil.GetDayOfWeek(goalSetDate)){
                //If yes, then simply check if the current time is between goal start and end time
                result = CommonUtil.IsCurrentDateBetweenStartAndEnd(startTimeStamp, endTimeStamp);
                return result;
            }
            else{
                result = false;
                return  result;
            }
        }

        if(mGoalRepeatType.equals("MONTHLY")){
            // if the repeat type is Monthly, then check if the current month is same as the day of goal creation
            if(CommonUtil.GetCurrentDayOfMonth() == CommonUtil.GetDayOfMonth(goalSetDate)){
                //If yes, then simply check if the current time is between goal start and end time
                result = CommonUtil.IsCurrentDateBetweenStartAndEnd(startTimeStamp, endTimeStamp);
                return result;
            }
            else{
                result = false;
                return  result;
            }

        }

        if(mGoalRepeatType.equals("YEARLY")){
            //Both Day of Month and Month should be same with that of goal creation
            if(CommonUtil.GetCurrentDayOfMonth() == CommonUtil.GetDayOfMonth(goalSetDate)
                    && CommonUtil.GetCurrentMonth() == CommonUtil.GetMonth(goalSetDate)){
                //If yes, then simply check if the current time is between goal start and end time
                result = CommonUtil.IsCurrentDateBetweenStartAndEnd(startTimeStamp, endTimeStamp);
                return result;
            }
            else{
                result = false;
                return  result;
            }

        }

        //TODO: WORK ON CUSTOM ONE

        return  result;
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
