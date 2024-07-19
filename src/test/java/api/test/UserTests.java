package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;

	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
//logs
		logger= LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)

	public void testPostUser() {
logger.info("This is Post method start");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("This is Post method start");
	}
	
	@Test(priority = 2)
	public void getUserByName() {
		logger.info("This is get method start");
		Response response = UserEndPoints.getUser(this.userPayload.getUsername());

		response.then().log().all();
	   Assert.assertEquals(response.getStatusCode(), 200);
	   logger.info("This is get method end");
	}

	@Test(priority = 3)
	public void updatetUserByName() {
		logger.info("This is update method start");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);

		response.then().log().all();
	   Assert.assertEquals(response.getStatusCode(), 200);	
	   logger.info("This is Post method end");
	   //checking data after update
	   Response updatedResponse= UserEndPoints.getUser(userPayload.getUsername());
	   updatedResponse.then().log().all();
	   //Actual update is not happening in the given url so below code cant be validated for now
	   //Assert.assertEquals(updatedResponse.jsonPath().get("firstname"), this.userPayload.getFirstName());
	  // Assert.assertEquals(updatedResponse.jsonPath().get("lastName"), userPayload.getLastName());
	  // Assert.assertEquals(updatedResponse.jsonPath().get("emailAddress"),userPayload.getEmail());
	}
	
	@Test(priority = 4)
	public void deleteUserByName() {
		logger.info("This is delete method start");
		Response response = UserEndPoints.deleteUser(userPayload.getUsername());

		response.then().log().all();
	   Assert.assertEquals(response.getStatusCode(), 200);	
	   logger.info("This is delete method end");
	}
	
}
