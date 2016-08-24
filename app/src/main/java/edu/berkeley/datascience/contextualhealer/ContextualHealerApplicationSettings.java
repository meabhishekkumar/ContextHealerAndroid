package edu.berkeley.datascience.contextualhealer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ContextualHealerApplicationSettings {

    private static final String SETTINGS_ENABLE_TRACKING = "SETTINGS_ENABLE_TRACKING";
    private static final String FIRST_RUN = "FIRST_RUN";

    SharedPreferences mSharedPreferences;
    private Context mContext;

    public ContextualHealerApplicationSettings(Context context){
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getEnableTrackingPreference(){

        return mSharedPreferences.getInt(SETTINGS_ENABLE_TRACKING, 0); //Default is 0 : Means Not tracking. 1 : Means Tracking
    }

    public Boolean getFirstRun(){

        return mSharedPreferences.getBoolean(FIRST_RUN, true); //Default is true : Means Not tracking. 1 : Means Tracking
    }

    public void setFirstRun(){
        mSharedPreferences.edit()
                .putBoolean(FIRST_RUN, false)
                .apply();
    }


    public void setEnableTrackingPreference(int enableTracking){
        mSharedPreferences.edit()
                .putInt(SETTINGS_ENABLE_TRACKING, enableTracking)
                .apply();
    }

    public String getTrackingModePreference(){

        return mSharedPreferences.getString(mContext.getResources().getString(R.string.key_tracking_mode), "TRACKING_LOCAL"); //Default TRACKING_LOCAL
    }


    public Boolean getEnableKinesisStreamPreference(){

        return mSharedPreferences.getBoolean(mContext.getResources().getString(R.string.enable_data_stream),false); //Default false
    }

}
