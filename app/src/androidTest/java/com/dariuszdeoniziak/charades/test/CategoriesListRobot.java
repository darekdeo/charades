package com.dariuszdeoniziak.charades.test;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.views.activities.CategoriesActivity;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class CategoriesListRobot {

    private ActivityScenario<CategoriesActivity> scenario;

    public void launchCategoriesActivity() {
        scenario = ActivityScenario.launch(CategoriesActivity.class);
    }

    public void verifyHeaderTextIs(String text) {
        Espresso.onView(withId(R.id.categories_title))
                .check(matches(withText(text)));
    }

    public void close() {
        scenario.close();
    }
}
