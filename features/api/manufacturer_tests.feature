@api @manufacturer @positive
Feature: API Tests related to "car-types/manufacturer"

  @all @regression
  Scenario: Verify list of all Car manufacturer, when user doesn't provide any locale
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response

  @fr @regression
  Scenario: Verify list of all Car manufacturer, when user provides locale = FR (France)
    #get all available manufacturer codes
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response
    And I store all the available car manufacturer
    #get all available manufacturer codes for locale = FR
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response
    And Verify all manufacturer codes are part of list of all available manufacturer codes

  @uk
  Scenario: Verify list of all Car manufacturer for 'United Kingdom', when user provides locale = UK (United Kingdom)
    #get all available manufacturer codes
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response
    And I store all the available car manufacturer
    #get all available manufacturer codes for locale = FR
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | UK |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response
    And Verify all manufacturer codes are part of list of all available manufacturer codes