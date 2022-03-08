package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import rest_assured_api_configs.GoRestUserConfig;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TCR13_GetListFromResponseStep {

    Response response;

    @Given("I submit a GET request for users information")
    public void iSubmitAGETRequestForUsersInformation() {
        response = given().auth().oauth2(GoRestUserConfig.token)
                .when().get("https://gorest.co.in/public/v2/users");
        System.out.println(response.asString());
    }

    @When("GET request for user information is successful")
    public void getRequestForUserInformationIsSuccessful() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("I can retrieve a list of user names")
    public void iCanRetrieveAListOfUserNames() {
        // by default .path() assumes you are navigating
        // through a JSON formatted response. If the response
        // returns an XML then using .path() to navigate will not work
        List<String> listOfNames = response.path("name");
        System.out.println(listOfNames);
    }
}
