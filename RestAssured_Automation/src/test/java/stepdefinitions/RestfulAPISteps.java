package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import com.example.api.constants.ApiEndPoints;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestfulAPISteps {
	Response response;

	@When("I perform a GET operation for objects")
	public void i_perform_a_get_operation_for_objects() {
		response =given()
				.when()
				.get("https://api.restful-api.dev/objects")
				.then()
				.extract().response();

		JsonPath jsonResponse = new JsonPath(response.asString());
		int ID = jsonResponse.getInt("[0].id");
		String name = jsonResponse.getString("[0].name");
		String color = jsonResponse.getString("[0].data.color");

		System.out.println(ID);
		System.out.println(name);
		System.out.println(color);

		//get values of JSON array after getting array size
		int s = jsonResponse.getInt("[0].size()");
		for(int i = 0; i < s; i++) {
			int id = jsonResponse.getInt("[i].id");
			System.out.println(id);
		}
	}

	@Then("I should see status code as {int} in the response")
	public void verifyUserResponse(int statusCode) {
		assertEquals(statusCode, response.getStatusCode());
	}


}
