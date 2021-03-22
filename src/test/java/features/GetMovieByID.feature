Feature: Validate the Movie' API from MovieDB website

  Scenario: Verify that the user get the movie based on their ID
    Given Movie Id is given
    When User call http GET method
    Then The API call is success with status code 200