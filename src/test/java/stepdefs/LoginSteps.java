package stepdefs;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginSteps {
	Map<String, String> loginPayload = new HashMap<>();
	Response response;
	String token;
	
	// Edited class

	@Given("I have the login payload")
	public void I_have_the_login_payload() {

		loginPayload.put("email", "eve.holt@reqres.in");
		loginPayload.put("password", "cityslicka");
	}

	@When("I send the login request")
	public void I_send_the_login_request() {

		response = RestAssured.given().header("x-api-key", "reqres-free-v1").header("Content-Type", "application/json")
				.body(loginPayload).when().post("https://reqres.in/api/login");

		response.prettyPrint();
	}

	@Then("I should recieve the token")
	public void I_should_recieve_the_token() {
		System.out.println(response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);

		System.out.println(response.jsonPath().getString("token"));

		token = response.jsonPath().getString("token");
		Assert.assertNotNull(token);

	}

	@And("I use the token to get user details")
	public void I_use_the_token_to_get_user_details() {
		response = RestAssured.given().header("x-api-key", "reqres-free-v1").header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token).when().get("https://reqres.in/api/users/2");

		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@And("The user name should be Janet")
	public void The_user_name_should_be_Janet() {
		Assert.assertEquals(response.jsonPath().getString("data.first_name"), "Janet");

	}
}
