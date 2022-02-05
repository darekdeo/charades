package com.dariuszdeoniziak.charades.test;

import android.os.Bundle;

import androidx.multidex.MultiDex;

import io.cucumber.android.runner.CucumberAndroidJUnitRunner;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(
        features = "features",
        glue = "com.dariuszdeoniziak.charades.steps"
)
public class CucumberRunner extends CucumberAndroidJUnitRunner {

    @Override
    public void onCreate(Bundle bundle) {
        MultiDex.install(getTargetContext());
        super.onCreate(bundle);
    }
}
