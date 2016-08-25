package edu.berkeley.datascience.contextualhealer;


import android.test.suitebuilder.annotation.LargeTest;

import org.apache.commons.math3.stat.StatUtils;
import org.junit.Test;

import edu.berkeley.datascience.contextualhealer.activity.ActivityType;
import edu.berkeley.datascience.contextualhealer.model.Goal;
import edu.berkeley.datascience.contextualhealer.model.PredictionSample;
import edu.berkeley.datascience.contextualhealer.utils.CommonUtil;

import static org.junit.Assert.assertEquals;




@LargeTest
public class BasicTest {

//    Context context;
//
//    public void setUp() throws Exception {
//        super.setUp();
//        context = new MockContext();
//    }

    @Test
    public void statistics_library_Working() throws  Exception{
        double[] values = {4.0, 2.0, 4.0, 2.0};
        double mean = StatUtils.mean(values);
        assertEquals(mean, 3.0, 0.0001);
    }

    @Test
    public void collection_test() throws Exception{
        double[] values1 =  {-1.566340,	-7.777218,	-9.112013 };
        double[] values2 =  {-1.566450,	-7.777220,	-9.112002 };

        PredictionSample sample = new PredictionSample();
        sample.AddAccelerometerX(values1[0]);
        sample.AddAccelerometerY(values1[1]);
        sample.AddAccelerometerZ(values1[2]);

        sample.AddAccelerometerX(values2[0]);
        sample.AddAccelerometerY(values2[1]);
        sample.AddAccelerometerZ(values2[2]);

        double[] inputs = sample.GetSample1();

        //ActivityDetector mActivityDetector = new ActivityDetector();
        //mActivityDetector.setup(context);
        assertEquals(4, 4+0);
        //Util.PredictionUtil predictUtil = new Util.PredictionUtil();
        //String result = predictUtil.getActivity(mActivityDetector, inputs);


    }

    @Test
    public void ParseResponseString(){
      //String response =  ["Sitting", "Sitting", "Sitting", "Sitting", "Sitting", "Sitting", "Sitting", "Sitting", "Sitting", "Sitting", "Sitting", "Sitting"];
    }


    @Test
    public void CheckIsToBeUpdated(){
        String day1_str = CommonUtil.GetCurrentDateString();


        //Activity - 1
        Goal goal_1 = new Goal(1, "Morning Jogging", ActivityType.jogging.toString(),20, "DAILY","", "07:00", "20:00",day1_str, 1,0);
        goal_1.setCompletedPercentage(70);
        Goal goal_2 = new Goal(2, "Walking to Office",ActivityType.walking.toString(),10, "DAILY","","09:00", "10:00",day1_str, 1,0);
        goal_2.setCompletedPercentage(50);
        Goal goal_3 = new Goal(3, "Use Stairs",ActivityType.staircase.toString(),10, "DAILY", "","05:50", "17:50",day1_str, 1,0);
        goal_3.setCompletedPercentage(20);

        //Boolean result1 = goal_1.IsGoalToBeUpdated();

        //Boolean result2 = goal_2.IsGoalToBeUpdated();
        Boolean result3 = goal_3.IsGoalToBeUpdated();

    }
}
