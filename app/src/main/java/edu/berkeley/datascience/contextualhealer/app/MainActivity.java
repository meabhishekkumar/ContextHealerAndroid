package edu.berkeley.datascience.contextualhealer.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.datascience.contextualhealer.ContextualHealerApplicationSettings;
import edu.berkeley.datascience.contextualhealer.adapter.IconTabsAdapter;
import edu.berkeley.datascience.contextualhealer.fragment.NavigationDrawerFragment;
import edu.berkeley.datascience.contextualhealer.fragment.fragmentActiveGoals;
import edu.berkeley.datascience.contextualhealer.fragment.fragmentActivityReport;
import edu.berkeley.datascience.contextualhealer.fragment.fragmentActivityTrend;
import edu.berkeley.datascience.contextualhealer.fragment.fragmentCurrentActivityTimeLine;
import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.service.ContextRecognitionService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =  MainActivity.class.getSimpleName();
    public static final String TAB_TAG = "TAB_TAG";
    Toolbar toolbar;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ViewPager viewPager;
    private IconTabsAdapter adapter;
    private TabLayout tabLayout;

    private Button mBtnServiceStatus;
    private Boolean mBound = false;
    private ContextRecognitionService mContextRecognitionService;
    private SwitchCompat mchkContextService;
    private Context mContext;


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.v(TAG, "On Service Connected");
            mBound = true;
            ContextRecognitionService.LocalBinder localBinder = (ContextRecognitionService.LocalBinder) binder;
            mContextRecognitionService = localBinder.getService();
            if(mContextRecognitionService.isTracking()){
                mchkContextService.setChecked(true);
                //mBtnServiceStatus.setText("Stop Service");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };



    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mchkContextService = (SwitchCompat) findViewById(R.id.chkContextService);

        //get the settings from sharedPreference
        ContextualHealerApplicationSettings settings = new ContextualHealerApplicationSettings(MainActivity.this);
        if(settings.getEnableTrackingPreference() == 1){
            mchkContextService.setChecked(true);
        }
        else{
            mchkContextService.setChecked(false);
        }




        initialise();

        prepareDataResource();

        adapter = new IconTabsAdapter(getSupportFragmentManager(), mFragmentList);

        // Bind the Adapter to the ViewPager
        viewPager.setAdapter(adapter);

        // Link ViewPager and TabLayout
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        setTabIcons(); // Set the icons to the Tabs

        //TODO : Work on navigation drawer
        setUpNavigationDrawer();  //Set up navigation drawer

        mchkContextService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mBound){

                    if(mContextRecognitionService.isTracking()){
                        //Save the settings to sharedPreference
                        ContextualHealerApplicationSettings settings = new ContextualHealerApplicationSettings(MainActivity.this);
                        settings.setEnableTrackingPreference(0);

                        mContextRecognitionService.pauseTracking();
                        mchkContextService.setChecked(false);
                        //Save the settings to SharedPreference


                    }
                    else{
                        //Save the settings to sharedPreference
                        ContextualHealerApplicationSettings settings = new ContextualHealerApplicationSettings(MainActivity.this);
                        settings.setEnableTrackingPreference(1);

                        Intent intent = new Intent(MainActivity.this,  ContextRecognitionService.class);
                        startService(intent);
                        mContextRecognitionService.startTracking();
                        mchkContextService.setChecked(true);
                        //mBtnServiceStatus.setText("Stop Service");
                    }
                }
            }
        });

        //Set Pager
        int defaultValue = 0;
        int page = getIntent().getIntExtra(TAB_TAG, defaultValue);
        viewPager.setCurrentItem(page);
        //tabLayout.setS




    }

    @Override
    protected void onStart() {
        Log.v(TAG, "On Start of fragment");
        Intent intent = new Intent(this, ContextRecognitionService.class);
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
        super.onStart();
    }

    //Start the Context Recognition Service
//    private void startContextRecognitionService() {
//
//        if(mBound){
//
//            if(mContextRecognitionService.isTracking()){
//                mContextRecognitionService.pauseTracking();
//                Log.v(TAG, "Started Context Recognition Service");
//            }
//            else{
//                Intent intent = new Intent(this,  ContextRecognitionService.class);
//                startService(intent);
//                mContextRecognitionService.startTracking();
//                Log.v(TAG, "Stopping Context Recognition Service");
//            }
//        }
//    }


    //setup the ContextRecognitionService
//    private void setUpContextRecognitionService() {
//        Intent intent = new Intent(this, ContextRecognitionService.class);
//        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
//    }

    //Navigation Drawer
    private void setUpNavigationDrawer(){
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drwr_fragment);


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment.setUpDrawer(R.id.nav_drwr_fragment, drawerLayout, toolbar);
    }

    // Initialise Activity Data
    private void initialise() {
        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("GoalTick");
        //toolbar.inflateMenu(R.menu.menu_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }


    private void prepareDataResource() {
        mFragmentList.add(new fragmentActiveGoals());
        mFragmentList.add(new fragmentActivityTrend());
        mFragmentList.add(new fragmentActivityReport());
        //mFragmentList.add(new fragmentCurrentActivity());
        mFragmentList.add(new fragmentCurrentActivityTimeLine());

    }

    // Set the Icons of the Tabs
    private void setTabIcons() {

        tabLayout.getTabAt(0).setIcon(R.drawable.selector_icon_target);
        tabLayout.getTabAt(1).setIcon(R.drawable.selector_icon_activity_trend);
        tabLayout.getTabAt(2).setIcon(R.drawable.selector_icon_graph);
        tabLayout.getTabAt(3).setIcon(R.drawable.selector_icon_current_activity);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onStop() {
        Log.v(TAG, "On Stop of fragment");
        super.onStop();
        if(mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

}
