@api @e2e
Feature: E2e tests covering all 3 endpoints

  @positive
  Scenario Outline: Verify list of built dates for selected main-types of BMW & AUDI within locale = [ FR (France), UK (United Kingdom), DE (Germany)]
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
      | locale | <LOCALE> |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response
    And Verify all manufacturer codes are part of list of all available manufacturer codes

    #get all the main types for a manufacturer
    Given As a user I want to execute 'main-types' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale       | <LOCALE>            |
      | manufacturer | <MANUFACTURER_CODE> |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify actual schema of response

  #get all the built types for a manufacturer main type
    Given As a user I want to execute 'built-dates' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale       | <LOCALE>            |
      | manufacturer | <MANUFACTURER_CODE> |
      | main-type    | <MAIN_TYPE_CODE>    |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    #And Verify actual schema of response

    Examples:
      | LOCALE | MANUFACTURER_CODE | MAIN_TYPE_CODE |
      | FR     | 130               | X1             |
      | FR     | 060               | Q3             |
      | UK     | 130               | X1             |
      | UK     | 060               | Q3             |
      | DE     | 130               | X1             |
      | DE     | 060               | Q3             |

