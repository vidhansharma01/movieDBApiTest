package RestAssured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.PostRating;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {
    private static String key;
    public static void main(String args[]) throws IOException {
        // RestAssured.baseURI = "http://api.themoviedb.org/3";
        PostRating postRating = new PostRating();
        postRating.setValue(8.5);
        key = init();
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("http://api.themoviedb.org/3")
                .addQueryParam("api_key", key)
                .addQueryParam("guest_session_id", "5aa829db2aca61e5a42d9e5bb9af99ad")
        .setContentType(ContentType.JSON).build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(201)
                .expectContentType(ContentType.JSON).build();

        RequestSpecification res = given().log().all().spec(requestSpecification).body(postRating);
        Response response = res.when().post("/movie/590706/rating").then().spec(responseSpecification)
                .extract().response();
        String responseString=response.asString();
        System.out.println(responseString);
    }
    public static String init() throws IOException {
        FileInputStream fs = new FileInputStream("C:\\Users\\Vidhan Chandra\\Downloads\\movieDB\\src\\test\\java\\RestAssured\\Api.properties");
        Properties properties = new Properties();
        properties.load(fs);
        String key = properties.getProperty("api_key");
        return key;
    }
}
