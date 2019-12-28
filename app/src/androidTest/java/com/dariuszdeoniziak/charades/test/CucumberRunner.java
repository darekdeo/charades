package com.dariuszdeoniziak.charades.test;

import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberAndroidJUnitRunner;

@CucumberOptions(
        features = "features",
        glue = "com.dariuszdeoniziak.charades.steps"
)
public class CucumberRunner extends CucumberAndroidJUnitRunner {
}
