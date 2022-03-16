package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import line_drawers.LineDrawer;
import rest_assured_api_configs.GoRestUserConfig;

import static io.restassured.RestAssured.given;

public class TCR05_MeasuringAPIResponseTimeStep {

    Response getResponse;
    Response postResponse;
    String email;
    long getResponseTime;
    long postResponseTime;

    @Given("I submit a timed post request")
    public void iSubmitATimedPostRequest() {
        LineDrawer.HorizontalLineDrawer();
        email = "uniquetest1@email.com";
        // JSON that will be posted (correctly formatted)
        String userExampleJson = "{\n" +
                "    \"name\": \"test\",\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";
        // As part of the 'given' section we are setting the authentication token and supplying the information we wish
        // to post in a JSON format. During the 'when' section we are adding a header and specifying that the content
        // we will be supply is in a JSON format. We then supply the post url. Finally, we extract the response.
        postResponse = given()
                        .auth().oauth2(GoRestUserConfig.token).body(userExampleJson)
                .when()
                        .header("Content-Type", "application/json").post("https://gorest.co.in/public/v2/users");
        postResponse.then().assertThat().statusCode(201);
        // we can return the response as a string
        String jsonResponse = postResponse.asString();
        System.out.println(jsonResponse);
        LineDrawer.HorizontalLineDrawer();
    }

    @Given("I submit a timed get request")
    public void iSubmitAGetRequest() {
        // pull in data using a GET request
        getResponse = given()
                        .auth().oauth2(GoRestUserConfig.token)
                .when()
                        .get("https://gorest.co.in/public/v2/users");
        getResponse.then().assertThat().statusCode(200);
        System.out.println("Request successful (status code " + getResponse.getStatusCode() + ")");
        LineDrawer.HorizontalLineDrawer();
    }

    @When("I return the time taken for both requests")
    public void iReturnTheTimeTakenForBothRequests() {
        getResponseTime = getResponse.getTime();
        System.out.println("The GET API response time was: " + getResponseTime + " ms");
        postResponseTime = postResponse.getTime();
        System.out.println("The POST API response time was: " + postResponseTime + " ms");
        LineDrawer.HorizontalLineDrawer();
    }

    @Then("I compare the get api response time to the post api response time")
    public void iCompareTheGetApiResponseTimeToThePostApiResponseTime() {
        if (getResponseTime < postResponseTime) {
            System.out.println("The POST request response took longer than the GET request response.");
        }
        else {
            System.out.println("The GET request response took longer than the POST request response.");
        }
        LineDrawer.HorizontalLineDrawer();
    }
}