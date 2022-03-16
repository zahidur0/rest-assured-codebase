package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import line_drawers.LineDrawer;
import org.junit.Assert;
import rest_assured_api_configs.GoRestUserConfig;

import static io.restassured.RestAssured.given;

public class TCR12_GettingHeadersStep {

    Response response;

    @Given("I submit a GET request")
    public void iSubmitAGETRequest() {
        LineDrawer.HorizontalLineDrawer();
        response = given().auth().oauth2(GoRestUserConfig.token)
                .when().get("https://gorest.co.in/public/v2/users")
                .then().extract().response();
        System.out.println(response.asString());
        LineDrawer.HorizontalLineDrawer();
    }

    @When("GET request is successful")
    public void getRequestIsSuccessful() {
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println("Request successful (status code " + response.getStatusCode() + ")");
        LineDrawer.HorizontalLineDrawer();
    }

    @Then("I can extract all headers")
    public void iCanExtractAllHeaders() {
        // get all the headers from the response and print them
        Headers headers = response.getHeaders();
        for (Header header: headers) {
            System.out.println(header);
        }
        LineDrawer.HorizontalLineDrawer();
    }

    @Then("I can extract one header")
    public void iCanExtractOneHeader() {
        // get a particular header using .getHeader()
        // we assert that the string returned contains
        // 'application/json' because we know the response takes a
        // JSON format
        String header = response.getHeader("Content-Type");
        Assert.assertTrue(header.contains("application/json"));
        System.out.println(header);
        LineDrawer.HorizontalLineDrawer();
    }
}
