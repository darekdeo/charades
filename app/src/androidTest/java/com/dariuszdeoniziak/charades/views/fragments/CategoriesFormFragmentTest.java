package com.dariuszdeoniziak.charades.views.fragments;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.presenters.CategoriesFormPresenter;
import com.dariuszdeoniziak.charades.utils.AndroidStaticsWrapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class CategoriesFormFragmentTest {

    private FragmentScenario<CategoriesFormFragment> fragmentScenario;

    @Mock CategoriesFormPresenter presenter;
    @Mock AndroidStaticsWrapper androidWrapper;

    @Before
    public void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());

        MockitoAnnotations.initMocks(this);
        fragmentScenario = FragmentScenario.launchInContainer(CategoriesFormFragment.class);
        fragmentScenario.onFragment((fragment) -> {
            fragment.replace(presenter, androidWrapper); // replace injected presenter with mock
        });
    }

    @After
    public void tearDown() {
        reset(presenter, androidWrapper);
    }

    @Test
    public void onTakeView() {
        // given
        fragmentScenario.moveToState(Lifecycle.State.CREATED);

        // when fragment starts
        fragmentScenario.moveToState(Lifecycle.State.STARTED);

        // then
        fragmentScenario.onFragment((fragment) -> {
            verify(presenter).onTakeView(fragment);
            verify(presenter).loadCategory(fragment.categoryId);
        });
    }

    @Test
    public void onDropView() {
        // when fragment stops
        fragmentScenario.moveToState(Lifecycle.State.CREATED);

        // then
        verify(presenter).onDropView();
    }

    @Test
    public void onTitleEdited() {
        // given
        String testText = "test title";

        // when user types in form title text
        onView(withId(R.id.form_category_title)).perform(click()).perform(typeText(testText));
        onView(withId(R.id.form_category_title)).check(matches(withText(testText)));

        // then text should be saved
        verify(presenter).saveCategoryTitle(testText);
    }
}
