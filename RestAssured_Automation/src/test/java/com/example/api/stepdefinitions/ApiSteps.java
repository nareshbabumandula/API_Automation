package com.example.api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import com.example.api.constants.ApiEndPoints;
import com.example.api.utils.RestClient;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

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
		JsonPath jsonResponse = new JsonPath(response.asString());
		int ID = jsonResponse.getInt("data.id");
		String email = jsonResponse.getString("data.email");
		String first_name = jsonResponse.getString("data.first_name");
		String last_name = jsonResponse.getString("data.last_name");

		assert email.equals("janet.weaver@reqres.in");
		assert first_name.equals("Janet");
		assert last_name.equals("Weaver");
	}

	@Then("I should get a {int} status code and user details")
	public void verifyUserResponse(int statusCode) {
		assertEquals(statusCode, response.getStatusCode());
	}

	@When("I perform a POST operation for the user")
	public void createUser() {
		String requestBody = "{\r\n"
				+ "    \"name\": \"Saiteja\",\r\n"
				+ "    \"job\": \"QA Lead\"\r\n"
				+ "}";
		response = given().header("Content-Type", "application/json")
				.body(requestBody)
				.when()
				.post(ApiEndPoints.CREATEUSER)
				.then()
				.extract().response();
		System.out.println(response.getStatusCode());
		assertEquals(201, response.getStatusCode());
		
		JsonPath jsonResponse = new JsonPath(response.asString());
		String name = jsonResponse.getString("name");
		String job = jsonResponse.getString("job");
		
		assert name.equals("Saiteja");
		assert job.equals("QA Lead");

	}


}
