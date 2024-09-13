package com.example.api.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/com/example/api/features",
glue= {"com.example.api.stepdefinitions"}, plugin= {"pretty", "html:target/cucumber-reports.html"},
tags="@POST or not @regression or not @dryrun")
public class TestRunner {

}
