package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resource.RatingDataBuild;
import resource.Utils;

import static io.restassured.RestAssured.given;

public class StepDefinations extends Utils {
    RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    Response response;
    RatingDataBuild ratingDataBuild = new RatingDataBuild();

    /*Get movie name based on ID*/
    @Given("^Movie Id is given$")
    public void movie_id_is_given() throws Throwable {
        reqSpec = given().spec(requestSpecification());
    }

    @When("^User call http GET method$")
    public void user_call_http_get_method() throws Throwable {
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response = reqSpec.when().get("/movie/590706").then().spec(resSpec)
                .extract().response();
    }

    @Then("^The API call is success with status code 200$")
    public void the_api_call_is_success_with_status_code_200() throws Throwable {
        Assert.assertEquals(response.statusCode(), 200);
    }
    /*Rate a movie*/
    @Given("^Movie Id is given along with rating$")
    public void movie_id_is_given_along_with_rating() throws Throwable {
        reqSpec = given().spec(requestSpecification().queryParam("guest_session_id", getSessionId())).body(ratingDataBuild.addRating());
    }
    @When("^User call http POST method$")
    public void user_call_http_post_method() throws Throwable {
        resSpec = new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();
        response = reqSpec.when().post("/movie/590706/rating").then().spec(resSpec)
                .extract().response();
    }

    @Then("^The API call is success with status code 201$")
    public void the_api_call_is_success_with_status_code_201() throws Throwable {
        Assert.assertEquals(response.statusCode(), 201);
    }

}
