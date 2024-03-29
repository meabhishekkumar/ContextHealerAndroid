package edu.berkeley.datascience.contextualhealer.activity;

import edu.berkeley.datascience.contextualhealer.interfaces.IPredictor;

public class OnDevicePredictor implements IPredictor {

    @Override
    public ActivityType GetActivity(ActivityDetector detector, double[] sample) {
        int result = detector.getActivityClass(sample.length, sample);
        ActivityType activity;

        switch (result) {
            case 0:  activity = ActivityType.downstairs;
                break;
            case 1:  activity = ActivityType.jogging;
                break;
            case 2:  activity = ActivityType.sitting;
                break;
            case 3:  activity = ActivityType.standing;
                break;
            case 4:  activity = ActivityType.upstairs;
                break;
            case 5:  activity = ActivityType.walking;
                break;
            default: activity = ActivityType.unknown;
                break;
        }


        return  activity;
    }


}
