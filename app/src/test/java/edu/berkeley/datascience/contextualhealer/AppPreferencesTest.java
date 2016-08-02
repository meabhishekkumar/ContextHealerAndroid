package edu.berkeley.datascience.contextualhealer;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.TestCase.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class AppPreferencesTest {

    //AppPreferences preferences;
    Context mContext;

    @Before
    public void setUp() throws Exception {
        mContext = RuntimeEnvironment.application.getApplicationContext();
        //preferences = new AppPreferences();
    }

    @Test
    public void testIsNotificationsEnabled_Default() throws Exception {
        ActivityDetector mActivityDetector = new ActivityDetector();
        mActivityDetector.setup(mContext);
    }
}
