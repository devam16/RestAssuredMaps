package stepDefinitions;

import static io.restassured.RestAssured.given;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class commonStepDef extends Utils {

	RequestSpecification req;
	ResponseSpecification resSpec;
	Response res;
	static String Place_id;

	@Given("Add Place Payload with {string},{string},{string},{string}")
	public void add_place_payload(String address, String lang, String name, String PhnNo) throws IOException {

		req = given().spec(getRequestSpecification()).body(TestDataBuild.addPlacePayload(address, lang, name, PhnNo));
	}

	@When("User Calls {string} with {string} HTTP request")
	public void user_calls_with_post_http_request(String resource, String httpMethod) {

		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println("The resource Url path : " + resourceAPI.getAPIResource());

		if (httpMethod.equalsIgnoreCase("POST")) {
			res = req.when().post(resourceAPI.getAPIResource());
		} else if (httpMethod.equalsIgnoreCase("GET")) {
			res = req.when().get(resourceAPI.getAPIResource());
		} else if (httpMethod.equalsIgnoreCase("DELETE")) {
			res = req.when().delete(resourceAPI.getAPIResource());
		} else if (httpMethod.equalsIgnoreCase("PUT")) {
			res = req.when().put(resourceAPI.getAPIResource());
		} else {
			System.out.println("HTTP Method Not Defined");
		}

	}

	@Then("the API call is successful with status code {int}")
	public void the_api_call_is_successful_with_status_code(Integer statusCode) {

//	resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//	 .then().spec(resSpec).extract().response();

		Assert.assertEquals(Integer.toString(res.getStatusCode()), Integer.toString(statusCode));

	}

	@Then("{string} in the Response body is {string}")
	public void in_the_response_body_is(String key, String value) {
		assertEquals(getJsonPath(res, key), value);

	}

	@Then("verify place_id generated maps to {string} using {string}")
	public void verify_place_id_generated_maps_to_using(String expectedName, String APIRequest) throws IOException {

		Place_id = getJsonPath(res, "place_id");
		req = given().spec(getRequestSpecification()).queryParam("place_id", Place_id);
		user_calls_with_post_http_request(APIRequest, "GET");
		String actualName = getJsonPath(res, "name");
		assertEquals(actualName, expectedName);
	}
	@Then("verify address updated maps to {string} using {string}")
	public void verify_address_updated_maps_to_using(String expectedAddress, String APIRequest) throws IOException {
		req = given().spec(getRequestSpecification()).queryParam("place_id", Place_id);
		user_calls_with_post_http_request(APIRequest, "GET");
		String actualAddress = getJsonPath(res, "address");
		assertEquals(actualAddress, expectedAddress);
	}

	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		req = given().spec(getRequestSpecification()).body(TestDataBuild.DeletePlaceAPI(Place_id));
	}

	@Given("Update Place Payload with {string}")
	public void update_place_payload_with(String address) throws IOException {
		req = given().spec(getRequestSpecification()).body(TestDataBuild.UpdatePlaceAPI(Place_id, address));
	}

}
