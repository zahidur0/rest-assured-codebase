package rest_assured_api_configs;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.oauth2;

public class GoRestUserConfig {
    public static String token = "1234";
    public static String existentId = "16771";
    public static RequestSpecification goRestRequestSpec;

    @Before
    public static void setup() {
        AuthenticationScheme auth2 = oauth2(token);
        goRestRequestSpec = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public")
                .setBasePath("/v2/")
                .addHeader("Content-Type", "application/json")
                .setAuth(auth2)
                .build();

        RestAssured.requestSpecification = goRestRequestSpec;
    }
}
