package edu.berkeley.datascience.contextualhealer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.database.GoalDataSource;
import edu.berkeley.datascience.contextualhealer.model.ActivitySummary;

public class fragmentActivityReport extends Fragment {

    private static final String TAG = fragmentActivityReport.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_activity_report, container, false);

        return rootView;


    }
}
