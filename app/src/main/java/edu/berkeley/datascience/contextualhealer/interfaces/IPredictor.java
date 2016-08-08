package edu.berkeley.datascience.contextualhealer.interfaces;


import edu.berkeley.datascience.contextualhealer.activity.ActivityDetector;
import edu.berkeley.datascience.contextualhealer.activity.ActivityType;

public interface IPredictor {
    public ActivityType GetActivity(ActivityDetector detector, double[] sample);

}
