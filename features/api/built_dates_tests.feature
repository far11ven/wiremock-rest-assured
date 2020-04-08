@api @built-dates @positive
Feature: API Tests related to "car-types/built-dates"

  @bmw
  Scenario: Verify list of built dates for manufacturer code =130 (BMW), make-type = 'X1' & when user doesn't specify any locale
    Given As a user I want to execute 'built-dates' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | manufacturer | 130 |
      | main-type    | X1  |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    #And Verify actual schema of response

  @fr @bmw @regression
  Scenario: Verify list of built dates for manufacturer code =130 (BMW), make-type = 'X1' & locale=FR
    Given As a user I want to execute 'built-dates' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale       | FR  |
      | manufacturer | 130 |
      | main-type    | X1  |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    #And Verify actual schema of response

  @fr @audi
  Scenario: Verify list of built dates for manufacturer code =060 (AUDI), make-type = 'Q3' & locale=FR
    Given As a user I want to execute 'built-dates' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale       | FR  |
      | manufacturer | 060 |
      | main-type    | Q3  |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    #And Verify actual schema of response

