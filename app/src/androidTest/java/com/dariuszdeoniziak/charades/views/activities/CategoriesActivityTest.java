package com.dariuszdeoniziak.charades.views.activities;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.presenters.CategoriesActivityPresenter;
import com.dariuszdeoniziak.charades.utils.AndroidStaticsWrapper;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesFormFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesListFragment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.verify;


@RunWith(AndroidJUnit4.class)
public class CategoriesActivityTest {

    @Mock
    CategoriesActivityPresenter presenter;
    @Mock
    AndroidStaticsWrapper androidStaticsWrapper;

    @Rule
    public ActivityScenarioRule<CategoriesActivity> scenarioRule = new ActivityScenarioRule<>(CategoriesActivity.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        scenarioRule.getScenario().onActivity((activity -> {
            activity.replace(presenter, androidStaticsWrapper);
        }));
    }

    @Test
    public void onTakeView() {
        // given
        scenarioRule.getScenario().moveToState(Lifecycle.State.CREATED);

        // when activity starts
        scenarioRule.getScenario().moveToState(Lifecycle.State.STARTED);

        // then
        scenarioRule.getScenario().onActivity((activity) -> {
            verify(presenter).onTakeView(activity);
        });
    }

    @Test
    public void onDropView() {
        // when activity stops
        scenarioRule.getScenario().moveToState(Lifecycle.State.CREATED);

        // then
        verify(presenter).onDropView();
    }

    @Test
    public void testCreate() {
        // when activity has just been created

        // then it should display categories list
        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));
        scenarioRule.getScenario().onActivity((activity) -> {
            Fragment fragment = activity.getCurrentFragment(R.id.fragment_container);
            Assert.assertNotNull(fragment);
            Assert.assertTrue(fragment instanceof CategoriesListFragment);
        });
    }

    @Test
    public void testToggleEditMode() {
        // given activity with categories list
        scenarioRule.getScenario().onActivity((activity -> {
            Fragment fragment = activity.getCurrentFragment(R.id.fragment_container);
            Assert.assertNotNull(fragment);
            Assert.assertTrue(fragment instanceof CategoriesListFragment);
        }));

        // when user performs click on button to add new category
        onView(withId(R.id.button_plus)).perform(click());

        // then categories form should be displayed
        scenarioRule.getScenario().onActivity((activity) -> {
            Fragment fragment = activity.getCurrentFragment(R.id.fragment_container);
            Assert.assertNotNull(fragment);
            Assert.assertTrue(fragment instanceof CategoriesFormFragment);
        });

        // and when user perform click on the button again
        onView(withId(R.id.button_plus)).perform(click());

        // then form should be closed and categories list should be displayed
        scenarioRule.getScenario().onActivity((activity) -> {
            Fragment fragment = activity.getCurrentFragment(R.id.fragment_container);
            Assert.assertNotNull(fragment);
            Assert.assertTrue(fragment instanceof CategoriesListFragment);
        });
    }
}