package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.example.api.constants.ApiEndPoints;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.RestUtilities;

public class UserSteps {

	private Response response;

	public UserSteps() {
		RestUtilities.setBaseURI();
		RestUtilities.setContentType();
	}

	@Given("I have a valid user ID")
	public void validateUser() {
		
	}

	@When("I perform a GET operation for users")
	public void getUser() {
		response =given()
				.when()
				.get(ApiEndPoints.USERS)
				.then()
				.extract().response();
				
		JsonPath jsonResponse = new JsonPath(response.asString());
		int ID = jsonResponse.getInt("data[0].id");
		String email = jsonResponse.getString("data[0].email");
		String first_name = jsonResponse.getString("data[0].first_name");
		String last_name = jsonResponse.getString("data[0].last_name");

		assert email.equals("michael.lawson@reqres.in");
		assert first_name.equals("Michael");
		assert last_name.equals("Lawson");

		// Loop through JSON response
		JsonPath path = new JsonPath(response.asString());
		List<Map<String, Object>> users = path.getList("data");
        System.out.println("No of objects in json array are : " + path.getList("data").size());
		for (Map<String, Object> user : users) {
			System.out.println("ID : " + user.get("id"));
			System.out.println("Email : " + user.get("email"));
			System.out.println("Firstname : " + user.get("first_name"));
			System.out.println("Lastname" + user.get("last_name"));
			System.out.println("Avatar : " + user.get("avatar"));
		}
		
	
		// JSON parsing
		JSONObject obj = new JSONObject("{\r\n"
				+ "    \"data\": {\r\n"
				+ "        \"id\": 2,\r\n"
				+ "        \"email\": \"janet.weaver@reqres.in\",\r\n"
				+ "        \"first_name\": \"Janet\",\r\n"
				+ "        \"last_name\": \"Weaver\",\r\n"
				+ "        \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\r\n"
				+ "    },\r\n"
				+ "    \"support\": {\r\n"
				+ "        \"url\": \"https://reqres.in/#support-heading\",\r\n"
				+ "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\r\n"
				+ "    }\r\n"
				+ "}");
		
		System.out.println("ID is : " + obj.getInt("data.id"));
		System.out.println("ID is : " + obj.getInt("data.email"));
		
		
	}

	@Then("I should get status code as {int} in the response")
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

	@When("I perform PUT operation for updating user with id {int}")
	public void updateUser(Integer int1) {
		String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}"; 
		response = given()
				.header("Content-Type", "application/json")
				.body(requestBody)
				.when()
				.put(ApiEndPoints.USER.replace("{id}", int1.toString()))
				.then()
				.extract().response();
	}

	@When("I perform DELETE operation for user with id {int}")
	public void deleteUser(Integer int1) {
		response = given()
				.when()
				.delete(ApiEndPoints.USER.replace("{id}", int1.toString()))
				.then()
				.extract().response();
	}
	
	@When("I perform a GET operation for a from a user list")
	public void getUserResource() {
		response =given()
				.when()
				.get(ApiEndPoints.LISTRESOURCE)
				.then()
				.extract().response();
	}

}
