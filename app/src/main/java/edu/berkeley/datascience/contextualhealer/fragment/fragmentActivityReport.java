package edu.berkeley.datascience.contextualhealer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.berkeley.datascience.contextualhealer.R;

public class fragmentActivityReport extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_activity_report, container, false);
    }
}
