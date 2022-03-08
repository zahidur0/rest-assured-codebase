package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import rest_assured_api_configs.GoRestUserConfig;

import static io.restassured.RestAssured.get;

public class TCR14_UseRequestSpecificationForAuthenticationStep {

    Response response;

    @Given("I setup a RequestSpecification object")
    public void iSetupARequestSpecificationObject() {
        GoRestUserConfig.setup();
    }

    @When("I submit a GET request with no explicit authorisation specified")
    public void iSubmitAGETRequestWithNoExplicitAuthorisationSpecified() {
        // we no longer need to explicitly specify authorisation type
        // and full URL since these are define in our RequestSpecification
        // object in the setup method
        response = get("users");
    }

    @Then("the submit request is successful")
    public void theSubmitRequestIsSuccessful() {
        Assert.assertEquals(200, response.getStatusCode());
        response.prettyPeek();
    }
}
