package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import line_drawers.LineDrawer;
import org.junit.Assert;
import rest_assured_api_configs.GoRestUserConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class TCR02_SendingAndValidatingPOSTRequestsStep{

    String email;
    Response response;

    @Given("I submit a post request")
    public void iSubmitAPostRequest() {
        email = "test1@email.com";
        // JSON that will be posted (correctly formatted)
        String userExampleJson = "{\n" +
                "    \"name\": \"test\",\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";
        // As part of the 'given' section we are setting the authentication token and supplying the information we wish
        // to post in a JSON format. During the 'when' section we are adding a header and specifying the content
        // we will be supply is in a JSON format. We then supply the post url. Finally, we extract the response.
        LineDrawer.HorizontalLineDrawer();
        response = given()
                        .auth().oauth2(GoRestUserConfig.token).body(userExampleJson)
                .when()
                        .header("Content-Type", "application/json")
                        .post("https://gorest.co.in/public/v2/users");
        // we can return the response as a string
        String jsonResponse = response.asString();
        System.out.println(jsonResponse);
        LineDrawer.HorizontalLineDrawer();
    }

    @When("The post request was successful")
    public void thePostRequestWasSuccessful() {
        // check if the request is successful
        Assert.assertEquals(201, response.getStatusCode());
        System.out.println("Request successful (status code " + response.getStatusCode() + ")");
        LineDrawer.HorizontalLineDrawer();
    }

    @Then("I validate the entry has been added")
    public void iValidateTheEntryHasBeenAdded() {
        // check if the body of the response contains the email we posted
        response.then().body("email", containsString(email));
        System.out.println("Validation successful");
        LineDrawer.HorizontalLineDrawer();
    }
}
