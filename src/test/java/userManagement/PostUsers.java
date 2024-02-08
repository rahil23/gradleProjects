package userManagement;

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import utils.JsonReader;
import utils.LoadProperties;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PostUsers {


    public PostUsers() throws IOException, ParseException {
    }
    String serverName = LoadProperties.propertyReader("config.properties","user");
    String endpoint = JsonReader.getTestData("endpoint");
    String URL = serverName+endpoint;
    Response response =  given()
            .when()
            .get(URL)
            .then()
            .extract()
            .response();
}
