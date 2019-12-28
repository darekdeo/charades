package com.dariuszdeoniziak.charades.steps;

import com.dariuszdeoniziak.charades.test.CategoriesListRobot;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CategoriesListSteps {

    private CategoriesListRobot robot = new CategoriesListRobot();

    @When("I start the categories list screen")
    public void iStartTheCategoriesListScreen() {
        Assert.assertNotNull(robot);
        robot.launchCategoriesActivity();
    }

    @Then("I expect to see header {string}")
    public void iExpectToSeeHeader(String text) {
        robot.verifyHeaderTextIs(text);
    }
}
