package com.dariuszdeoniziak.charades.activities;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, assetDir = "assets", application = App.class)
public class BaseActivityTest {

    CharadeListActivity activity; // Any non abstract Activity to test BaseActivity methods.

    /**
     * Instantiate any non abstract Activity.
     */
    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(CharadeListActivity.class).create().get();
        assertNotNull(activity);
    }

    @After
    public void tearDown() {

    }

    /**
     * Simple test on SharedPreferences to see if dependency injection did work.
     */
    @Test
    public void testSharedPreferences() {
        String key = "test_string_key";

        assertNotNull(activity.mSharedPreferences);
        activity.mSharedPreferences.edit().putString(key, "Hello Prefs").commit();
        String testString = activity.mSharedPreferences.getString(key, null);
        assertNotNull(testString);

        boolean didCommit = activity.mSharedPreferences.edit().remove(key).commit();
        assertTrue(didCommit);
    }
}