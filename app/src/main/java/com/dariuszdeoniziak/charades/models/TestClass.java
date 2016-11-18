package com.dariuszdeoniziak.charades.models;

import android.app.Activity;

import javax.inject.Inject;

public class TestClass {

    private String say = "i don't know where i am";

    @Inject
    public TestClass(Activity activity) {
        if (activity != null)
            say = "i am at " + activity.getLocalClassName();
    }

    public String say() {
        return say;
    }
}
