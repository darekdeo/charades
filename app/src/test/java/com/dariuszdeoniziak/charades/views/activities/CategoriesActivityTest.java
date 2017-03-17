package com.dariuszdeoniziak.charades.views.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;
import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.views.CategoriesView;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.presenters.CategoriesActivityPresenter;
import com.dariuszdeoniziak.charades.utils.AndroidStaticsWrapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, application = App.class)
public class CategoriesActivityTest {

    private CategoriesActivity activity;
    private ActivityController<CategoriesActivity> controller;
    @Mock CategoriesActivityPresenter presenter;
    @Mock Bundle bundle;
    @Mock BaseFragment fragment;
    @Mock AndroidStaticsWrapper androidWrapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.buildActivity(CategoriesActivity.class).create().get();
        assertNotNull(activity);
        activity.replace(presenter, androidWrapper);

        controller = Robolectric.buildActivity(CategoriesActivity.class);
    }

    @After
    public void tearDown() throws Exception {
        reset(presenter);
    }

    @Test
    public void testLeaveView() throws Exception {
        activity.onDestroy();
        verify(presenter).onDropView();
    }

    @Test
    public void testReturnToView() throws Exception {
        activity.onResume();
        verify(presenter).onTakeView(activity);
    }

    @Test
    public void testSaveView() throws Exception {
        activity.onSaveInstanceState(bundle);
        verify(presenter).onSave();
    }

    @Test
    public void testCreate() throws Exception {
        CategoriesActivity activity = controller.get();
        Fragment fragment = activity.getCurrentFragment(R.id.fragment_container);
        assertNull(fragment);

        controller.create();
        fragment = activity.getCurrentFragment(R.id.fragment_container);
        assertNotNull(fragment);
    }

    @Test
    public void testDisplayTextInfo() throws Exception {
        activity.displayTextInfo("test");
        verify(androidWrapper).showToast(activity, "test", Toast.LENGTH_SHORT);
    }
}