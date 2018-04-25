package com.dariuszdeoniziak.charades.views.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;
import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.presenters.CategoriesActivityPresenter;
import com.dariuszdeoniziak.charades.utils.AndroidStaticsWrapper;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesFormFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

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
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.buildActivity(CategoriesActivity.class).create().get();
        assertNotNull(activity);
        activity.replace(presenter, androidWrapper);

        controller = Robolectric.buildActivity(CategoriesActivity.class);
    }

    @After
    public void tearDown() {
        reset(presenter, bundle, fragment, androidWrapper);
    }

    @Test
    public void testLeaveView() {
        // when
        activity.onStop();

        // then
        verify(presenter).onDropView();
    }

    @Test
    public void testReturnToView() {
        // when
        activity.onStart();

        // then
        verify(presenter).onTakeView(activity);
    }

    @Test
    public void testSaveView() {
        // when
        activity.onSaveInstanceState(bundle);

        // then
        verify(presenter).onSave();
    }

    @Test
    public void testCreate() {
        CategoriesActivity activity = controller.get();
        Fragment fragment = activity.getCurrentFragment(R.id.fragment_container);
        assertNull(fragment);

        controller.create();
        fragment = activity.getCurrentFragment(R.id.fragment_container);
        assertNotNull(fragment);
    }

    @Test
    public void testDisplayTextInfo() {
        // when
        activity.showTextInfo("test");

        // then
        verify(androidWrapper).showToast(activity, "test", Toast.LENGTH_SHORT);
    }

    @Test
    public void testToggleEditMode() {
        // given
        CategoriesActivity spy = spy(activity);
        CategoriesFormFragment fragment = CategoriesFormFragment.newInstance();

        // when
        spy.toggleViewMode(null);

        // then
        verify(spy).replaceFragment(
                Mockito.any(),
                any(CategoriesFormFragment.class),
                eq(R.id.fragment_container),
                eq(fragment.TAG),
                eq(true));

        // also when
        spy.toggleViewMode(null);

        // then
        verify(spy).popFragmentBackStack();
    }
}