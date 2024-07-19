package api.endpoints;


import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;

//created to perform CURD operations
public class UserEndPoints2 {
	
	// method created for getting URL's from properties file
			static ResourceBundle getURL()
			{
				ResourceBundle routes= ResourceBundle.getBundle("routes"); // Load properties file  // name of the properties file
				return routes;
			}
	public static Response createUser(User payload)
	{
		Response response=given().contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(getURL().getString("post_url"));
		
		return response; 
	}
	
	public static Response getUser(String userName)
	{
		Response response=given().pathParam("username", userName)
		
		.when()
		.get(getURL().getString("get_url"));
		
		return response; 
	}

	public static Response updateUser(String userName, User payload)
	{
		Response response=given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload).pathParam("username", userName)
		
		.when()
		.get(getURL().getString("update_url"));
		
		return response; 
	}

	public static Response deleteUser(String userName)
	{
		Response response=given().pathParam("username", userName)
		
		.when()
		.get(getURL().getString("delete_url"));
		
		return response; 
	}
}
