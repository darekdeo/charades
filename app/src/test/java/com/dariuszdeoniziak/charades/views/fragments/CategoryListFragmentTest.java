package com.dariuszdeoniziak.charades.views.fragments;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;
import com.dariuszdeoniziak.charades.presenters.CategoryListPresenter;
import com.dariuszdeoniziak.charades.views.CategoryListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.FragmentController;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, application = App.class)
public class CategoryListFragmentTest {

    CategoryListFragment fragment;
    FragmentController<CategoryListFragment> controller;

    @Mock CategoryListPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        fragment = Robolectric.buildFragment(CategoryListFragment.class).create().get();
        assertNotNull(fragment);
        fragment.replace(presenter); // replace injected presenter with mock

        controller = Robolectric.buildFragment(CategoryListFragment.class);
    }

    @After
    public void tearDown() {
        reset(presenter);
    }

    @Test
    public void onTakeView() throws Exception {
        fragment.onResume();
        verify(presenter).onTakeView(fragment);
    }

    @Test
    public void onDropView() throws Exception {
        fragment.onDestroyView();
        verify(presenter).onDropView();
    }

}