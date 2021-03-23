package RestAssured;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import pojo.GetMovieBasedOnID;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HttpApiTest {
    private static String key;
    public static void main(String args[]) throws IOException {
        RestAssured.baseURI = "http://api.themoviedb.org/3";
        key = init();
        String response = given().log().all().queryParam("api_key", key).when()
                .get("movie/590706").then().log().all().assertThat().statusCode(200)
                .body("original_title", equalTo("Jiu Jitsu"))
                .extract().response().asString();
        JsonPath jsonPath = new JsonPath(response);
        String movieName = jsonPath.getString("original_title");
        System.out.println(movieName);
        Assert.assertEquals(movieName, "Jiu Jitsu");
        //getTopRatedMovies();
        //Get movie from ID using pojos
        getMoviePojos();
    }

    private static void getMoviePojos() {
        RestAssured.baseURI = "http://api.themoviedb.org/3";
        GetMovieBasedOnID getMovieBasedOnID = given().queryParam("api_key", key).expect().defaultParser(Parser.JSON)
                .when().get("movie/590706").as(GetMovieBasedOnID.class);
        System.out.println(getMovieBasedOnID.getOriginal_title());
//        JsonPath jsonPath = new JsonPath(response);
//        String movieName = jsonPath.getString("original_title");
//        System.out.println(movieName);
//        Assert.assertEquals(movieName, "Jiu Jitsu");

    }

    public static void getTopRatedMovies(){
        RestAssured.baseURI = "http://api.themoviedb.org/3";
        String response = given().queryParam("api_key", key)
                .queryParam("language", "en-US")
                .queryParam("page", "1")
                .when().get("movie/top_rated").then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath jsonPath = new JsonPath(response);
        int size =jsonPath.getInt("results.size()");
        //String arr = jsonPath.get("results[0].original_title");
        System.out.println(size);
        List<String> movieNames = new ArrayList<>();
        for(int i = 0 ;  i < size; i++){
            movieNames.add((String) jsonPath.get("results[" + i + "].original_title"));
            System.out.println(movieNames.get(i));
        }
    }
    public static String init() throws IOException {
        FileInputStream fs = new FileInputStream("C:\\Users\\Vidhan Chandra\\Downloads\\movieDB\\src\\test\\java\\RestAssured\\Api.properties");
        Properties properties = new Properties();
        properties.load(fs);
        String key = properties.getProperty("api_key");
        return key;
    }

}
