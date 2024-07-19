package api.endpoints;


import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;

//created to perform CURD operations
public class UserEndPoints {
	
	public static Response createUser(User payload)
	{
		Response response=given().contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.post_url);
		
		return response; 
	}
	
	public static Response getUser(String userName)
	{
		Response response=given().pathParam("username", userName)
		
		.when()
		.get(Routes.get_url);
		
		return response; 
	}

	public static Response updateUser(String userName, User payload)
	{
		Response response=given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload).pathParam("username", userName)
		
		.when()
		.get(Routes.update_url);
		
		return response; 
	}

	public static Response deleteUser(String userName)
	{
		Response response=given().pathParam("username", userName)
		
		.when()
		.get(Routes.delete_url);
		
		return response; 
	}
}
