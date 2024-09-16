package utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestUtilities {
	
	public static void setBaseURI() {
		RestAssured.baseURI="https://reqres.in";
	}
	
	public static void setContentType() {
		RestAssured.given().header("Content-Type", "application/json");
	}

}
