package edu.berkeley.datascience.contextualhealer;


public interface IPredictor {
    public ActivityType GetActivity(ActivityDetector detector, double[] sample);

}
