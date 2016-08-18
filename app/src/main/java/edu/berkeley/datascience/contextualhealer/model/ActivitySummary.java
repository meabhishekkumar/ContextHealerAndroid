package edu.berkeley.datascience.contextualhealer.model;

import android.graphics.drawable.ColorDrawable;

import edu.berkeley.datascience.contextualhealer.R;


public class ActivitySummary {


    private String mActivityType;
    private float mTotalDurationInMins;

    public ActivitySummary(String activityType, float totalDurationInMins){
        mActivityType = activityType;
        mTotalDurationInMins = totalDurationInMins;
    }

    public String getActivityType() {
        return mActivityType;
    }

    public void setActivityType(String activityType) {
        mActivityType = activityType;
    }

    public float getTotalDurationInMins() {
        return mTotalDurationInMins;
    }

    public void setTotalDurationInMins(float totalDurationInMins) {
        mTotalDurationInMins = totalDurationInMins;
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
                    case "walking":   color = R.color.color_activity_walking;
                        break;
                    default:  color = R.color.color_activity_unknown;
                        break;

        }

        return color;
    }


}
