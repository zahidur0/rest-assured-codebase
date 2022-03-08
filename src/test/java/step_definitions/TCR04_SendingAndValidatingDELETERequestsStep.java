package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import rest_assured_api_configs.GoRestUserConfig;
import static org.hamcrest.core.IsNot.not;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class TCR04_SendingAndValidatingDELETERequestsStep {

    Response response;
    String id;

    @Given("I submit a delete request")
    public void iSubmitADeleteRequest() {
        // submit a DELETE request
        id = GoRestUserConfig.existentId;
        response = given()
                        .auth().oauth2(GoRestUserConfig.token)
                .when()
                        .delete("https://gorest.co.in/public/v2/users/" + id);
    }

    @When("The delete request was successful")
    public void theDeleteRequestWasSuccessful() {
        // check DELETE request was successful
        Assert.assertEquals(204, response.getStatusCode());
    }

    @Then("I am able to validate the entry has been deleted")
    public void iAmAbleToValidateTheEntryHasBeenDeleted() {
        // pull in data using a GET request
        Response response = given().get("https://gorest.co.in/public/v2/users");
        // check that none of entries have the id that we deleted
        response.then().body("id", not(containsString(id)));
        // reset the existent id since the id we were dealing with is now removed
        GoRestUserConfig.existentId = "2560";
    }
}
