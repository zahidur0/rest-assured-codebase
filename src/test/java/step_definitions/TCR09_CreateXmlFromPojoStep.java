package step_definitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import line_drawers.LineDrawer;
import pojo_classes.GoRest;

public class TCR09_CreateXmlFromPojoStep {
    // we enable the indentation feature in the xml mapper
    // this ensures that the xml is indented appropriately
    // instead of begin printed all on a single line
    ObjectMapper xmlMapper = new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
    String xml;
    GoRest goRest;
    @Given("I create a pojo")
    public void iCreateAPojo() {
        LineDrawer.HorizontalLineDrawer();
        goRest = new GoRest();
        System.out.println("GoRest object created");
        LineDrawer.HorizontalLineDrawer();
    }

    @When("I set the pojo fields")
    public void iSetThePojoFields() {
        goRest.setEmail("david@email.com");
        goRest.setGender("male");
        goRest.setName("David");
        goRest.setStatus("inactive");
        System.out.println("Object values set");
        LineDrawer.HorizontalLineDrawer();
    }

    @Then("I create an XML entry")
    public void iCreateAnXMLEntry() throws JsonProcessingException {
        // by default the root tag name will be the name of the class
        // which in this case is 'GoRest'
        // we can change this using .withRootName to anything we wish
        xml = xmlMapper.writer().withRootName("hash").writeValueAsString(goRest);
        System.out.println(xml);
        LineDrawer.HorizontalLineDrawer();
    }

}
