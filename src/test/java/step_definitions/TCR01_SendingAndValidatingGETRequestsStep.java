package step_definitions;

import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import rest_assured_api_configs.GoRestUserConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TCR01_SendingAndValidatingGETRequestsStep {
    ValidatableResponse response;
    String singleUserUrl = "https://reqres.in/api/users/2";
    String multipleUsersUrl = "https://reqres.in/api/users?page=2";

    @Given("I send a request for a single object")
    public void i_send_a_request_for_a_single_object(){
        // In order to send a get request we use the static given() method to first set up a RequestSpecification
        // We then call get() on that RequestSpecification object providing a URL to the endpoint we want
        // This will return a Response object that we can extract to a variable
        // Here we are hitting an endpoint that will return a single user
        response = given().get(singleUserUrl).then();
    }

    @Given("I send a request for multiple objects")
    public void i_send_a_request_for_multiple_objects(){
        // Here we are doing the same thing, but hitting an endpoint that will return multiple users
        response = given().get(multipleUsersUrl).then();
    }

    @When("The request is successful")
    public void the_request_successful(){
        // Here we call the response variable to ensure the call was successful
        // The statusCode method will throw an exception if the code doesn't match the one provided
        // With get requests, a 200 status code means that it was successful
        response.statusCode(200);
    }

    @Then("I am able to validate the returned object")
    public void i_am_able_to_validate_the_returned_object(){
        response.body("data.id", equalTo(2));
        response.body("data.email", equalTo("janet.weaver@reqres.in"));
        response.body("data.first_name", equalTo("Janet"));
        response.body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
    }

    @Then("I am able to validate specific objects are present")
    public void i_am_able_to_validate_specific_objects(){
        response.body("data.id", hasItems(7, 8,9,10));
        response.body("data.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in"));
        response.body("data.first_name", hasItems("Michael", "Lindsay", "Tobias", "Byron"));
        response.body("data.last_name", hasItems("Lawson", "Ferguson", "Funke", "Fields"));
        response.body("data.avatar", hasItems("https://reqres.in/img/faces/7-image.jpg", "https://reqres.in/img/faces/8-image.jpg"));
    }

}
