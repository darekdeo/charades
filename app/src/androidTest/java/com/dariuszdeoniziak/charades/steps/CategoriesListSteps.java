package com.dariuszdeoniziak.charades.steps;

import com.dariuszdeoniziak.charades.test.CategoriesListRobot;

import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CategoriesListSteps {

    private CategoriesListRobot robot = new CategoriesListRobot();

    @After
    public void after() {
        robot.close();
    }

    @When("I start the categories list screen")
    public void iStartTheCategoriesListScreen() {
        robot.launchCategoriesActivity();
    }

    @Then("I expect to see header {string}")
    public void iExpectToSeeHeader(String text) {
        robot.verifyHeaderTextIs(text);
    }
}
