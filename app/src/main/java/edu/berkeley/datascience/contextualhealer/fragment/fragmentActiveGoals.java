package edu.berkeley.datascience.contextualhealer.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.adapter.GoalAdapter;
import edu.berkeley.datascience.contextualhealer.app.CreateGoalActivity;
import edu.berkeley.datascience.contextualhealer.database.GoalDataSource;

public class fragmentActiveGoals extends Fragment  {

    private static final String TAG = fragmentActiveGoals.class.getSimpleName();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView mRecyclerView;
    private GoalAdapter mRecyclerAdapter;
    private GoalDataSource mGoalDataSource;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_active_goals, container, false);
        // 1. get a reference to recyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // 2. set layoutManger
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(getActivity()); // (Context context, int spanCount)
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManagerVertical);


        // 3. create an adapter
        mGoalDataSource = new GoalDataSource(getActivity());
        mRecyclerAdapter = new GoalAdapter(rootView.getContext(), mGoalDataSource.readActiveGoals());


        // 4. set adapter
        mRecyclerView.setAdapter(mRecyclerAdapter);
        // 5. set item animator to DefaultAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        coordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.coordinatorlayout);

        //Floating button to create a new goal
        FloatingActionButton btnCreateGoal = (FloatingActionButton) rootView.findViewById(R.id.btnCreateGoal);
        btnCreateGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //On click of create alarm button : open the new acitivity
                Intent intent = new Intent(getActivity(), CreateGoalActivity.class);
                startActivity(intent);

            }
        });

        // On item click
//        mRecyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                        // TODO Handle item click
//                        Goal goal =  mRecyclerAdapter.getList().get(position);
//                        Intent intent = new Intent(getActivity(), CreateGoalActivity.class);
//                        intent.putExtra("EXTRA_GOAL", goal);
//                        startActivity(intent);
//
//                    }
//                })
//        );


        return rootView;



    }


    @Override
    public void onResume() {
        super.onResume();
        //Toast.makeText(this.getActivity(),"Fetching active goals",Toast.LENGTH_SHORT).show();
        refreshActiveGoals();

    }

    private void refreshActiveGoals() {
        // Get Latest Active Goals
        Log.v(TAG,"Refreshed active goals list");
        mRecyclerAdapter = new GoalAdapter(this.getActivity(), mGoalDataSource.readActiveGoals());
        mRecyclerView.setAdapter(mRecyclerAdapter);

    }


}
