package com.example.api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestClient {
	
	public RestClient() {
		RestAssured.baseURI="https://reqres.in";
	}
	
	public Response get(String endpoint) {
		return given().when().get(endpoint).then().extract().response();
	}

}
