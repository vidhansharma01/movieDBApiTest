package RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;

public class HttpApiTest {
    public static void main(String args[]) throws IOException {
        RestAssured.baseURI = "http://api.themoviedb.org/3";
        String key = init();
        String response = given().log().all().queryParam("api_key", key).when()
                .get("movie/590706").then().log().all().assertThat().statusCode(200)
                .body("original_title", equalTo("Jiu Jitsu"))
                .extract().response().asString();
        JsonPath jsonPath = new JsonPath(response);
        String movieName = jsonPath.getString("original_title");
        System.out.println(movieName);
        Assert.assertEquals(movieName, "Jiu Jitsu");
    }
    public static String init() throws IOException {
        FileInputStream fs = new FileInputStream("C:\\Users\\Vidhan Chandra\\Downloads\\movieDB\\src\\test\\java\\RestAssured\\Api.properties");
        Properties properties = new Properties();
        properties.load(fs);
        String key = properties.getProperty("api_key");
        return key;
    }
}
