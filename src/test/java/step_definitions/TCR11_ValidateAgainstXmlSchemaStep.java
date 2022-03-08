package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import rest_assured_api_configs.GoRestUserConfig;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

public class TCR11_ValidateAgainstXmlSchemaStep {

    Response response;

    @Given("I have a XML response")
    public void iHaveAXMLResponse() {
        String id = GoRestUserConfig.existentId;
        response = given()
                        .auth().oauth2(GoRestUserConfig.token)
                        .header("Content-Type", "application/xml")
                        .header("Accept", "application/xml")
                .when()
                        .get("https://gorest.co.in/public/v2/users/"+ id + ".xml")
                .then()
                        .extract().response();
        System.out.println(response.asString());
    }

    @And("I have a XML schema")
    public void iHaveAXMLSchema() {
        // here we check if the JSON schema exists
        Assert.assertTrue(new File("src/main/resources/GoRestXmlSchema.xsd").isFile());
    }

    @Then("I find the XML response agrees with the schema")
    public void iFindTheXMLResponseAgreesWithTheSchema() {
        // check if the response agrees with the format dictated by the XML schema
        response.then().body(matchesXsdInClasspath("GoRestXmlSchema.xsd"));
    }

}
