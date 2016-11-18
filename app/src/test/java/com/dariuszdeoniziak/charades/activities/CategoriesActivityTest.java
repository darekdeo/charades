package com.dariuszdeoniziak.charades.activities;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;

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
public class CategoriesActivityTest {

    private CategoriesActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(CategoriesActivity.class).create().get();
        assertNotNull(activity);
    }

    @Test
    public void testSharedPreferences() {
        String key = "test_string_key";

        assertNotNull(activity.sharedPreferences);
        activity.sharedPreferences.edit().putString(key, "Hello Prefs").commit();
        String testString = activity.sharedPreferences.getString(key, null);
        assertNotNull(testString);

        boolean didCommit = activity.sharedPreferences.edit().remove(key).commit();
        assertTrue(didCommit);
    }
}