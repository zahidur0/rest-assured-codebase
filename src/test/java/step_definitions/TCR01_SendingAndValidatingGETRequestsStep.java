package step_definitions;

import groovy.json.JsonOutput;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import line_drawers.LineDrawer;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TCR01_SendingAndValidatingGETRequestsStep {
    Response response;
    String singleUserUrl = "https://reqres.in/api/users/2";
    String multipleUsersUrl = "https://reqres.in/api/users?page=2";

    @Given("I send a request for a single object")
    public void i_send_a_request_for_a_single_object(){
        LineDrawer.HorizontalLineDrawer();
        // In order to send a get request we use the static given() method to first set up a RequestSpecification
        // We then call get() on that RequestSpecification object providing a URL to the endpoint we want
        // This will return a Response object that we can extract to a variable
        // Here we are hitting an endpoint that will return a single user
        response = given().get(singleUserUrl);
        response.prettyPrint();
        LineDrawer.HorizontalLineDrawer();
    }

    @Given("I send a request for multiple objects")
    public void i_send_a_request_for_multiple_objects(){
        // Here we are doing the same thing, but hitting an endpoint that will return multiple users
        response = given().get(multipleUsersUrl);
        response.prettyPrint();
        LineDrawer.HorizontalLineDrawer();
    }

    @When("The request is successful")
    public void the_request_successful(){
        // Here we call the response variable to ensure the call was successful
        // The statusCode method will throw an exception if the code doesn't match the one provided
        // With get requests, a 200 status code means that it was successful
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println("Request successful (status code " + response.getStatusCode() + ")");
        LineDrawer.HorizontalLineDrawer();
    }

    @Then("I am able to validate the returned object")
    public void i_am_able_to_validate_the_returned_object(){
        response.then().body("data.id", equalTo(2));
        response.then().body("data.email", equalTo("janet.weaver@reqres.in"));
        response.then().body("data.first_name", equalTo("Janet"));
        response.then().body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
        System.out.println("Validation successful");
        LineDrawer.HorizontalLineDrawer();
    }

    @Then("I am able to validate specific objects are present")
    public void i_am_able_to_validate_specific_objects(){
        response.then().body("data.id", hasItems(7, 8,9,10));
        response.then().body("data.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in"));
        response.then().body("data.first_name", hasItems("Michael", "Lindsay", "Tobias", "Byron"));
        response.then().body("data.last_name", hasItems("Lawson", "Ferguson", "Funke", "Fields"));
        response.then().body("data.avatar", hasItems("https://reqres.in/img/faces/7-image.jpg", "https://reqres.in/img/faces/8-image.jpg"));
        System.out.println("Validation successful");
        LineDrawer.HorizontalLineDrawer();
    }

}
