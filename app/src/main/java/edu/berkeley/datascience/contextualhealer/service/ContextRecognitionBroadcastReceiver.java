package edu.berkeley.datascience.contextualhealer.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ContextRecognitionBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = ContextRecognitionBroadcastReceiver.class.getSimpleName() ;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG,"Intent received to start the service");
        // Run the service on the system boot
        Intent contextRecognitionServiceIntent = new Intent(context, ContextRecognitionService.class);
        context.startActivity(contextRecognitionServiceIntent);

    }
}
