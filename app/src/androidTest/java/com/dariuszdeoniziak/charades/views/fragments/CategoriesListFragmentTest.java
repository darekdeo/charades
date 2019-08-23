package com.dariuszdeoniziak.charades.views.fragments;

import com.dariuszdeoniziak.charades.presenters.CategoriesListPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;


@RunWith(AndroidJUnit4.class)
public class CategoriesListFragmentTest {

    private FragmentScenario<CategoriesListFragment> fragmentScenario;

    @Mock CategoriesListPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        fragmentScenario = FragmentScenario.launchInContainer(CategoriesListFragment.class);
        fragmentScenario.onFragment((fragment) -> {
            fragment.replace(presenter); // replace injected presenter with mock
        });
    }

    @After
    public void tearDown() {
        reset(presenter);
    }

    @Test
    public void onStart() {
        // given
        fragmentScenario.moveToState(Lifecycle.State.CREATED);

        // when
        fragmentScenario.moveToState(Lifecycle.State.STARTED);

        // then
        fragmentScenario.onFragment((fragment) -> {
            verify(presenter).onTakeView(fragment);
            verify(presenter).loadCategories();
        });
    }

    @Test
    public void onStop() {
        // when fragment stops
        fragmentScenario.moveToState(Lifecycle.State.CREATED);

        // then
        verify(presenter).onDropView();
    }
}