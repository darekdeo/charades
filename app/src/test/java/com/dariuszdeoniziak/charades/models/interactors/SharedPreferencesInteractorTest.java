package com.dariuszdeoniziak.charades.models.interactors;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class SharedPreferencesInteractorTest {

    private PreferencesInteractor interactor;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        interactor = new SharedPreferencesInteractor(context);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFirstRun() throws Exception {
        // when then
        assertTrue(interactor.isFirstRun());

        // also when
        interactor.saveFirstRun();
        Thread.sleep(1000);

        // then
        assertFalse(interactor.isFirstRun());

        // also when
        interactor.deleteFirstRun();
        Thread.sleep(1000);

        // then
        assertTrue(interactor.isFirstRun());
    }

}