package edu.berkeley.datascience.contextualhealer.fragment;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.adapter.RecyclerAdapter;
import edu.berkeley.datascience.contextualhealer.app.MainActivity;
import edu.berkeley.datascience.contextualhealer.model.ActiveGoal;
import edu.berkeley.datascience.contextualhealer.model.Landscape;

public class fragmentActiveGoals extends Fragment {

    private CoordinatorLayout coordinatorLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        final View rootView = inflater.inflate(R.layout.fragment_active_goals, container, false);
        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // 2. set layoutManger
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(getActivity()); // (Context context, int spanCount)
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);


        // 3. create an adapter
        RecyclerAdapter adapter = new RecyclerAdapter(rootView.getContext(), ActiveGoal.getData());


        // 4. set adapter
        recyclerView.setAdapter(adapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        coordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.coordinatorlayout);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(rootView.getContext(), "FAB Clicked", Toast.LENGTH_SHORT).show();
            }
        });



        return rootView;



    }


}
