package edu.berkeley.datascience.contextualhealer.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import edu.berkeley.datascience.contextualhealer.R;

public class ContextualHealerSettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
