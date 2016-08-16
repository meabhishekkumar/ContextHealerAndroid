package edu.berkeley.datascience.contextualhealer.fragment;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.adapter.ActivityTimeLineAdapter;
import edu.berkeley.datascience.contextualhealer.database.GoalDataSource;
import edu.berkeley.datascience.contextualhealer.model.ActivitySample;
import edu.berkeley.datascience.contextualhealer.model.GoalCompletion;
import edu.berkeley.datascience.contextualhealer.service.ContextRecognitionService;

public class fragmentCurrentActivityTimeLine extends Fragment  {

    private static final String TAG = fragmentCurrentActivityTimeLine.class.getSimpleName();
    private Context mContext;

    private RecyclerView mRecyclerView;

    private ActivityTimeLineAdapter mActivityTimeLineAdapter;
    private ArrayList<ActivitySample> mDataList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_current_activity_timeline, container, false);
        mContext = rootView.getContext();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.activityTimelineRecyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);
        initView();
        return rootView;
    }

    private LinearLayoutManager getLinearLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        return linearLayoutManager;
    }

    private void initView() {

        GoalDataSource dataSource = new GoalDataSource(mContext);
        ArrayList<ActivitySample> samples = dataSource.readActivitySamples(5);
        mActivityTimeLineAdapter = new ActivityTimeLineAdapter(samples);
        mRecyclerView.setAdapter(mActivityTimeLineAdapter);


//        mDataList.clear();
//        ActivitySample model1 = new ActivitySample();
//        model1.setEndTimeStamp("00:40");
//        model1.setActivityType("jogging");
//        mDataList.add(model1);
//
//        ActivitySample model2 = new ActivitySample();
//        model2.setEndTimeStamp("00:45");
//        model2.setActivityType("walking");
//        mDataList.add(model2);
//
//
//        ActivitySample model3 = new ActivitySample();
//        model3.setEndTimeStamp("00:50");
//        model3.setActivityType("jogging");
//        mDataList.add(model3);
//
//        ActivitySample model4 = new ActivitySample();
//        model4.setEndTimeStamp("00:55");
//        model4.setActivityType("walking");
//        mDataList.add(model4);
//
//        ActivitySample model5 = new ActivitySample();
//        model5.setEndTimeStamp("00:60");
//        model5.setActivityType("jogging");
//        mDataList.add(model5);
//
//        ActivitySample model6 = new ActivitySample();
//        model6.setEndTimeStamp("00:65");
//        model6.setActivityType("walking");
//        mDataList.add(model6);
//
//        ActivitySample model7 = new ActivitySample();
//        model7.setEndTimeStamp("00:70");
//        model7.setActivityType("jogging");
//        mDataList.add(model7);
//
//        ActivitySample model8 = new ActivitySample();
//        model8.setEndTimeStamp("00:8");
//        model8.setActivityType("walking");
//        mDataList.add(model8);
//
//        ActivitySample model9 = new ActivitySample();
//        model9.setEndTimeStamp("00:40");
//        model9.setActivityType("jogging");
//        mDataList.add(model9);
//
//        ActivitySample model10 = new ActivitySample();
//        model10.setEndTimeStamp("00:45");
//        model10.setActivityType("walking");
//        mDataList.add(model10);



    }





}
