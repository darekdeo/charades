package com.dariuszdeoniziak.charades.views.activities;

import com.dariuszdeoniziak.charades.presenters.CategoriesPresenter;
import com.dariuszdeoniziak.charades.utils.AndroidStaticsWrapper;
import com.dariuszdeoniziak.charades.utils.Mapper;
import com.dariuszdeoniziak.charades.views.CategoryScreen;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;


@RunWith(AndroidJUnit4.class)
public class CategoriesActivityTest {

    @Mock
    CategoriesPresenter presenter;
    @Mock
    AndroidStaticsWrapper androidStaticsWrapper;
    @Mock
    Mapper<BaseFragment, CategoryScreen> toCategoryScreenMapper;

    @Rule
    public ActivityScenarioRule<CategoriesActivity> scenarioRule = new ActivityScenarioRule<>(CategoriesActivity.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        scenarioRule.getScenario().onActivity((activity -> {
            activity.replace(presenter, androidStaticsWrapper, toCategoryScreenMapper);
        }));
    }

    @After
    public void tearDown() {
        reset(presenter, androidStaticsWrapper, toCategoryScreenMapper);
    }

    @Test
    public void onTakeView() {
        // given
        scenarioRule.getScenario().moveToState(Lifecycle.State.CREATED);

        // when activity starts
        scenarioRule.getScenario().moveToState(Lifecycle.State.STARTED);

        // then
        scenarioRule.getScenario().onActivity((activity) -> verify(presenter).onTakeView(activity));
    }

    @Test
    public void onDropView() {
        // when activity stops
        scenarioRule.getScenario().moveToState(Lifecycle.State.CREATED);

        // then
        verify(presenter).onDropView();
    }
}