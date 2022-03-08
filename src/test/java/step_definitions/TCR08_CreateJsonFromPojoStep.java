package step_definitions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo_classes.GoRest;

public class TCR08_CreateJsonFromPojoStep {

    GoRest goRest;

    @Given("I create a pojo for json creation")
    public void iCreateAPojoForJsonCreation() {
        goRest = new GoRest();
    }

    @When("I set the pojo fields for json creation")
    public void iSetThePojoFieldsForJsonCreation() {
        goRest.setEmail("david@email.com");
        goRest.setGender("male");
        goRest.setName("David");
        goRest.setStatus("inactive");
    }

    @Then("I create an json entry")
    public void iCreateAnJsonEntry() {
        // The Gson module is used to create JSON from a POJO
        // .setPrettyPrinting() ensures that the json is formatted
        // and doesn't appear over a single line when printed
        Gson goRestJson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(goRestJson.toJson(goRest));
    }
}
