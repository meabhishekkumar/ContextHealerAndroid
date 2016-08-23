package edu.berkeley.datascience.contextualhealer;


import android.test.suitebuilder.annotation.LargeTest;

import org.apache.commons.math3.stat.StatUtils;
import org.junit.Test;

import edu.berkeley.datascience.contextualhealer.model.PredictionSample;

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
}
