package RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AppTest {
    public static void main(String args[]) {

        RestAssured.baseURI = "https://api.themoviedb.org/3";
        String res = given()
                .log().all().queryParam("api_key", "6e6bc4c62b7127d76ab425e59a75943a")
                .queryParam("language","en-US")
                .when().get("/movie/464052")
                .then().assertThat().log().all().statusCode(200).extract().asString();
        JsonPath jsonPath1 = new JsonPath(res);
        String website = jsonPath1.getString("homepage");
        System.out.println(website);

    JsonPath jsonPath = new JsonPath(Payload.CoursePrice());
    int count = jsonPath.getInt("courses.size()");
    System.out.println(count);
    int sum = 0;
        for(int i = 0 ; i < count; i++){
            int price = jsonPath.getInt("courses[" + i + "].price");
            int copies=jsonPath.getInt("courses["+i+"].copies");
            int amount = price * copies;
            sum += amount;
        }
        System.out.println(sum);
    }
    @Test(dataProvider = "movieData")
    public void getMovie(){

    }

    @DataProvider(name = "movieData")
    public Object[][] getData(){
        return new Object[][]{
                {"edej", "dedefe"}
        };
    }
}
