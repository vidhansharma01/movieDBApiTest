Feature: Validate the Movie' API from MovieDB website


  Scenario: Verify that the user can rate a movie
    Given Movie Id is given along with rating
    When User call http POST method
    Then The API call is success with status code 201