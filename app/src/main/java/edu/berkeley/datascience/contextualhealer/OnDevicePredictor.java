package edu.berkeley.datascience.contextualhealer;

public class OnDevicePredictor implements IPredictor {

    @Override
    public ActivityType GetActivity(ActivityDetector detector, double[] sample) {
        int result = detector.getActivityClass(sample.length, sample);
        ActivityType activity;
        
        switch (result) {
            case 1:  activity = ActivityType.downstairs;
                break;
            case 2:  activity = ActivityType.jogging;
                break;
            case 3:  activity = ActivityType.sitting;
                break;
            case 4:  activity = ActivityType.standing;
                break;
            case 5:  activity = ActivityType.upstairs;
                break;
            case 6:  activity = ActivityType.walking;
                break;
            default: activity = ActivityType.unknown;
                break;
        }

        return  activity;
    }


}
