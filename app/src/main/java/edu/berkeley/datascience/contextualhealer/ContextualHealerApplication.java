package edu.berkeley.datascience.contextualhealer;

import android.preference.PreferenceManager;

public class ContextualHealerApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }
}
