package com.example.api.stepdefinitions;

import com.example.api.constants.ApiEndPoints;
import com.example.api.utils.RestClient;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;

public class ApiSteps {
	
	private RestClient client;
	private Response response;
	private JsonObject requestBody;
	
	public ApiSteps() {
	    this.client = new RestClient();
	}
	
	@Given("I have a valid user ID")
	public void validateUser() {
	    
	}
	
	@When("I perform a GET operation for the user")
	public void getUser() {
	    response =client.get(ApiEndPoints.USER.replace("{id}", "2"));
	}
	
	@Then("I should get a {int} status code and user details")
	public void verifyUserResponse(int statusCode) {
	    assertEquals(statusCode, response.getStatusCode());
	    JsonPath path = new JsonPath(response.asString());
	    System.out.println("Response Body : " + response.getBody().asString());
	}

}
