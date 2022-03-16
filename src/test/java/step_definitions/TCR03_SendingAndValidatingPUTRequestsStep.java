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

public class TCR03_SendingAndValidatingPUTRequestsStep {

    Response response;
    String updatedName;
    String updatedEmail;
    String updatedStatus;
    String id;
    @Given("I submit a put request")
    public void iSubmitAPutRequest() {
        LineDrawer.HorizontalLineDrawer();
        updatedName = "testupdated";
        updatedStatus = "active";
        updatedEmail = "testupdated1@email.com";
        id = GoRestUserConfig.existentId;
        // the json we will add to the database
        String userExampleJson = "{\n" +
                "    \"name\": \"" + updatedName + "\",\n" +
                "    \"email\": \"" + updatedEmail + "\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"status\": \"" + updatedStatus + "\"\n" +
                "}";
        // submit PUT request
        response = given()
                        .auth().oauth2(GoRestUserConfig.token).body(userExampleJson)
                .when()
                        .header("Content-Type", "application/json")
                        .put("https://gorest.co.in/public/v2/users/" + id);
        response.prettyPrint();
        LineDrawer.HorizontalLineDrawer();
    }

    @When("The put request was successful")
    public void thePutRequestWasSuccessful() {
        // We check the status of the request by retrieving the HTTP status code
        // 200 refers to a successful response
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println("Request successful (status code " + response.getStatusCode() + ")");
        LineDrawer.HorizontalLineDrawer();
    }

    @Then("I am able to validate the entry has been updated")
    public void iAmAbleToValidateTheEntryHasBeenUpdated() {
        // here we pull the data using a GET request
        // then we check our response body contains the updated attributes
        Response getUpdatedResponse = given().auth().oauth2(GoRestUserConfig.token).get("https://gorest.co.in/public/v2/users/" + id);
        getUpdatedResponse.then().body("email", containsString(updatedEmail));
        getUpdatedResponse.then().body("status", containsString(updatedStatus));
        getUpdatedResponse.then().body("name", containsString(updatedName));
        System.out.println("Validation successful");
        // set id to the new id
        GoRestUserConfig.existentId = getUpdatedResponse.jsonPath().get("id").toString();
        System.out.println("ID of the updated entry is set to old ID.");
        LineDrawer.HorizontalLineDrawer();
    }

}
