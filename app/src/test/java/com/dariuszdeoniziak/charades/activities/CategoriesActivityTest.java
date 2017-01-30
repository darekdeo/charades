package com.dariuszdeoniziak.charades.activities;

import android.os.Bundle;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;
import com.dariuszdeoniziak.charades.presenters.CategoriesPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, assetDir = "assets", application = App.class)
public class CategoriesActivityTest {

    private CategoriesActivity activity;
    @Mock CategoriesPresenter presenter;
    @Mock Bundle bundle;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.buildActivity(CategoriesActivity.class).create().get();
        assertNotNull(activity);
        activity.setPresenter(presenter);
    }

    @After
    public void tearDown() throws Exception {
        reset(presenter);
    }

    @Test
    public void testLeaveView() throws Exception {
        activity.onDetachedFromWindow();
        verify(presenter).onDropView();
    }

    @Test
    public void testReturnToView() throws Exception {
        activity.onAttachedToWindow();
        verify(presenter).onTakeView();
    }

    @Test
    public void testSaveView() throws Exception {
        activity.onSaveInstanceState(bundle);
        verify(presenter).onSave();
    }
}