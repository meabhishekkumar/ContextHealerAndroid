package edu.berkeley.datascience.contextualhealer;

import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.TestCase;

import org.apache.commons.math3.stat.StatUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

@LargeTest
public class ExampleUnitTest  {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void statistics_library_Working() throws  Exception{
        double[] values = {4.0, 2.0, 4.0, 2.0};
        double mean = StatUtils.mean(values);
        assertEquals(mean, 3.0, 0.0001);
    }




}