package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import line_drawers.LineDrawer;
import pojo_classes.GoRest;
import pojo_classes.reqres.Root;
import rest_assured_api_configs.GoRestUserConfig;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class TCR06_CreatePojoFromJsonStep {

    Response response;
    Response nestedResponse;
    GoRest goRestObject;
    Root rootReqresObject;

    @Given("I receive a JSON response")
    public void iReceiveAJSONResponse() {
        LineDrawer.HorizontalLineDrawer();
        String id = GoRestUserConfig.existentId;
        response = given()
                .auth().oauth2(GoRestUserConfig.token)
                .when()
                .get("https://gorest.co.in/public/v2/users/" + id);
        System.out.println(response.asString());
        LineDrawer.HorizontalLineDrawer();
    }

    @When("I serialise an object using the JSON response")
    public void iSerialiseAnObjectUsingTheJSONResponse() {
        // returns a pojo from json
        goRestObject = response.getBody().as(GoRest.class);
        System.out.println("JSON mapped to POJO");
        LineDrawer.HorizontalLineDrawer();
    }

    @Then("I am able to verify the JSON has successfully been serialised as a POJO")
    public void iAmAbleToVerifyTheJSONHasSuccessfullyBeenSerialisedAsAPOJO() {
        System.out.println(goRestObject.toString());
        LineDrawer.HorizontalLineDrawer();
    }

    @Given("I receive a nested JSON response")
    public void iReceiveANestedJSONResponse() {
        // prettyPeek() prints out the GET response to the console immediately
        nestedResponse = when().get("https://reqres.in/api/users/2");
        nestedResponse.prettyPrint();
        LineDrawer.HorizontalLineDrawer();
    }

    @When("I serialise a nested object using the JSON response")
    public void iSerialiseANestedObjectUsingTheJSONResponse() {
        // returns the nested json as a Root object that we defined in the reqres package
        rootReqresObject = nestedResponse.as(Root.class);
        System.out.println("Nested JSON mapped to POJO");
        LineDrawer.HorizontalLineDrawer();
    }

    @Then("I can access nested data using pojos")
    public void iCanAccessNestedDataUsingPojos() {
        System.out.println(rootReqresObject.getData().toString());
        System.out.println(rootReqresObject.getSupport().toString());
        LineDrawer.HorizontalLineDrawer();
    }
}
