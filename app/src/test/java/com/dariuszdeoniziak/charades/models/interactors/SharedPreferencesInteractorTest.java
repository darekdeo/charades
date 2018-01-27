package com.dariuszdeoniziak.charades.models.interactors;

import android.content.Context;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, application = App.class)
public class SharedPreferencesInteractorTest {

    PreferencesInteractor interactor;

    @Before
    public void setUp() throws Exception {
        Context context = RuntimeEnvironment.application;
        interactor = new SharedPreferencesInteractor(context);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFirstRun() throws Exception {
        assertTrue(interactor.isFirstRun());
        interactor.saveFirstRun();
        Thread.sleep(1000);
        assertFalse(interactor.isFirstRun());
        interactor.deleteFirstRun();
        Thread.sleep(1000);
        assertTrue(interactor.isFirstRun());
    }

}