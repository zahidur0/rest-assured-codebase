package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import rest_assured_api_configs.GoRestUserConfig;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TCR10_ValidateAgainstJsonSchemaStep {

    Response response;

    @Given("I have a JSON response")
    public void iHaveAJSONResponse() {
        String id = GoRestUserConfig.existentId;
        System.out.println(id);
        response = given()
                        .auth().oauth2(GoRestUserConfig.token)
                .when()
                        .get("https://gorest.co.in/public/v2/users/" + id + ".json");
        System.out.println(response.asString());
    }

    @And("I have a JSON schema")
    public void iHaveAJSONSchema() {
        // here we check if the JSON schema exists
        Assert.assertTrue(new File("src/main/resources/GoRestJsonSchema.json").isFile());
    }

    @Then("I find the JSON response agrees with the schema")
    public void iFindTheJSONResponseAgreesWithTheSchema() {
        // check if the response agrees with the format dictated by the JSON schema
        response.then().body(matchesJsonSchemaInClasspath("GoRestJsonSchema.json"));
    }
}
