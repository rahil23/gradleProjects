package userManagement;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.JsonReader;
import utils.LoadProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GetUsers
{
    @Test
    public void getUserData()
    {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
       Response response =  given()
                .when()
                .get("/todos/1")
                .then()
               .extract()
               .response();
       System.out.println(response.prettyPrint());
     //  assertThat(response.jsonPath().getList("title"),hasItems("wewe","ewddfs"));
                //.body("title",equal)
    }

    @Test
    public void validateTestDataFromJson() throws IOException, ParseException {
        String username = JsonReader.getTestData("username");
        System.out.println(username);

    }

    @Test
    public void validateTestDataFromPropertyFile() throws IOException, ParseException {
        String serverName = LoadProperties.propertyReader("config.properties","user");
        String endpoint = JsonReader.getTestData("userAdd");
        String URL = serverName+endpoint;
        Response response =  given()
                .when()
                .body("{\"name\":\"TestUser\",\"job\",\"engineer\"}")
                .post(URL)
                .then()
                .extract()
                .response();
        System.out.println(response.prettyPrint());
       String language= JsonReader.getJsonArrayData(2);
        System.out.println("hi " + language);
     JSONArray jsonArray =  JsonReader.getJsonArray("contact");
        Iterator iterator = jsonArray.iterator();
     while(iterator.hasNext())
     {
         System.out.println(iterator.next());
     }

    }

    @Test
    public void hitPostRequestJSONFile() throws IOException, ParseException {
        String serverName = LoadProperties.propertyReader("config.properties","user");
        String endpoint = JsonReader.getTestData("userAdd");
        String URL = serverName+endpoint;
        Response response =  given()
                .when()
                .body(IOUtils.toString(
                        new FileInputStream(new File(System.getProperty("user.dir")+"//resources//testData//post.json"))
                ))
                .post(URL)
                .then()
                .extract()
                .response();
        System.out.println(response.prettyPrint());


    }
}
