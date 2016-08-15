package edu.berkeley.datascience.contextualhealer.fragment;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.model.PredictionSample;
import edu.berkeley.datascience.contextualhealer.service.ContextRecognitionService;

public class fragmentCurrentActivity extends Fragment  {

    private static final String TAG = fragmentCurrentActivity.class.getSimpleName();
    private Boolean mBound = false;
    private Button mBtnServiceStatus;
    private ContextRecognitionService mContextRecognitionService;
    private Context mContext;
    private TextView mTxtCurrentActivity;


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.v(TAG, "On Service Connected");
            mBound = true;
            ContextRecognitionService.LocalBinder localBinder = (ContextRecognitionService.LocalBinder) binder;
            mContextRecognitionService = localBinder.getService();
            if(mContextRecognitionService.isTracking()){
                mBtnServiceStatus.setText("Stop Service");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_current_activity, container, false);
        mContext = rootView.getContext();


        mBtnServiceStatus = (Button) rootView.findViewById(R.id.mBtnServiceStatus);
        mTxtCurrentActivity = (TextView) rootView.findViewById(R.id.txtCurrentActivity);

        mBtnServiceStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mBound){

                    if(mContextRecognitionService.isTracking()){
                        mContextRecognitionService.pauseTracking();
                        mBtnServiceStatus.setText("Start Service");
                    }
                    else{
                        Intent intent = new Intent(rootView.getContext(),  ContextRecognitionService.class);
                        getActivity().startService(intent);
                        mContextRecognitionService.startTracking();
                        mBtnServiceStatus.setText("Stop Service");
                    }
                }
            }
        });

        return rootView;
    }


    @Override
    public void onStart() {
        Log.v(TAG, "On Start of fragment");
        Intent intent = new Intent(getContext(), ContextRecognitionService.class);
        getActivity().bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.v(TAG, "On Stop of fragment");
        super.onStop();
        if(mBound) {
            getActivity().unbindService(mServiceConnection);
            mBound = false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(TAG, "Fragment in background");
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mActivityReciever);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, "Fragment in foreground");
        IntentFilter customFilter = new IntentFilter(ContextRecognitionService.NOTIFY_ACTIVITY_CHANGE);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mActivityReciever, customFilter);
    }

    private BroadcastReceiver mActivityReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String currentActivity = intent.getStringExtra(ContextRecognitionService.NOTIFY_CURRENT_ACTIVITY);
            mTxtCurrentActivity.setText(currentActivity);
        }
    };
}
