package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class StepDefinations {
    @Given("^Movie Id is given$")
    public void movie_id_is_given() throws Throwable {
        RestAssured.baseURI = "https://api.themoviedb.org/3";

    }

    @When("^User call http GET method$")
    public void user_call_http_get_method() throws Throwable {

    }

    @Then("^The API call is success with status code 200$")
    public void the_api_call_is_success_with_status_code_200() throws Throwable {

    }
}
