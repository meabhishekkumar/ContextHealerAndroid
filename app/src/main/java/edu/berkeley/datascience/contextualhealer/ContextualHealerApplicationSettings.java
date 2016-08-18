package edu.berkeley.datascience.contextualhealer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ContextualHealerApplicationSettings {

    private static final String SETTINGS_ENABLE_TRACKING = "SETTINGS_ENABLE_TRACKING";
    SharedPreferences mSharedPreferences;


    public ContextualHealerApplicationSettings(Context context){
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getEnableTrackingPreference(){

        return mSharedPreferences.getInt(SETTINGS_ENABLE_TRACKING, 0); //Default is 0 : Means Not tracking. 1 : Means Tracking
    }

    public void setEnableTrackingPreference(int enableTracking){
        mSharedPreferences.edit()
                .putInt(SETTINGS_ENABLE_TRACKING, enableTracking)
                .apply();
    }
}
