package com.dariuszdeoniziak.charades.presenters;

import android.content.Context;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;
import com.orm.SugarContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, assetDir = "assets", application = App.class)
public class CategoriesPresenterTest {

    @Before
    public void setUp() throws Exception {
        Context context = RuntimeEnvironment.application;
        SugarContext.init(context);
    }

    @After
    public void tearDown() throws Exception {
        SugarContext.terminate();
    }

    @Test
    public void getCharades() {

    }
}