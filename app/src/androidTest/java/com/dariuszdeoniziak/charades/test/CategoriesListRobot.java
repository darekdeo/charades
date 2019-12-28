package com.dariuszdeoniziak.charades.test;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.views.activities.CategoriesActivity;

import org.junit.Rule;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class CategoriesListRobot {

    @Rule
    public ActivityTestRule<CategoriesActivity> testRule = new ActivityTestRule<>(CategoriesActivity.class, false, false);

    public void launchCategoriesActivity() {
        testRule.launchActivity(null);
    }

    public void verifyHeaderTextIs(String text) {
        Espresso.onView(withId(R.id.categories_title))
                .check(matches(withText(text)));
    }
}
