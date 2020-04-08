@api @main-types @positive
Feature: API Tests related to "car-types/main-types"

  @bmw
  Scenario: Verify list of all car model codes, for manufacturer code =130 (BMW) & when user doesn't specify any locale
    Given As a user I want to execute 'main-types' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | manufacturer | 130 |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response

  @fr @bmw @regression
  Scenario: Verify list of all car model codes, when user provides locale = FR (France) & manufacturer code =130 (BMW)
    Given As a user I want to execute 'main-types' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale       | FR  |
      | manufacturer | 130 |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response

  @fr @audi
  Scenario: Verify list of all car model codes, when user provides locale = FR (France) & manufacturer code =060 (AUDI)
    Given As a user I want to execute 'main-types' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale       | FR  |
      | manufacturer | 060 |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response
