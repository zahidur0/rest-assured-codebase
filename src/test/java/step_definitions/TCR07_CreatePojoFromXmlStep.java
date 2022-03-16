package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import line_drawers.LineDrawer;
import pojo_classes.GoRest;
import rest_assured_api_configs.GoRestUserConfig;

import static io.restassured.RestAssured.given;

public class TCR07_CreatePojoFromXmlStep {

    Response response;
    GoRest goRestObject;
    @Given("I receive a XML response")
    public void iReceiveAXMLResponse() {
        LineDrawer.HorizontalLineDrawer();
        String id = GoRestUserConfig.existentId;
        response = given()
                        .auth().oauth2(GoRestUserConfig.token)
                .when()
                        .get("https://gorest.co.in/public/v2/users/" + id + ".xml")
                .then()
                        .extract().response();
        System.out.println(response.asString());
        LineDrawer.HorizontalLineDrawer();
    }

    @When("I serialise an object using the XML response")
    public void iSerialiseAnObjectUsingTheXMLResponse() {
        // return pojo from xml
        goRestObject = response.getBody().as(GoRest.class);
        System.out.println("XML mapped to POJO");
        LineDrawer.HorizontalLineDrawer();
    }

    @Then("I am able to validate the XML has successfully been serialised as a POJO")
    public void iAmAbleToValidateTheXMLHasSuccessfullyBeenSerialisedAsAPOJO() {
        System.out.println(goRestObject.toString());
        LineDrawer.HorizontalLineDrawer();
    }
}
