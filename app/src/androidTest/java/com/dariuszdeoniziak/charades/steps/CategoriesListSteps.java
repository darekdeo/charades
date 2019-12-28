package com.dariuszdeoniziak.charades.steps;

import com.dariuszdeoniziak.charades.test.CategoriesListRobot;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

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
