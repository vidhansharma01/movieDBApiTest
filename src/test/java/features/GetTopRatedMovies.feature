Feature: Validate the Movie' API from MovieDB website

  Scenario: Verify that the user is able to get the list of top rated movies
    Given Resource Url is given
    When User call http GET method
    Then The API call is success with status code 200