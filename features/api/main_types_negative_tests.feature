@api @main-types @negative
Feature: API Tests related to "car-types/main-types"

  @authentication-check @regression
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'POST'
    Given As a user I want to execute 'main-types' endpoint without 'wa_key'
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'POST' request
    Then Verify response status code is '401'

  @missing-params
  Scenario: Verify 'Bad Request' error is received when required query param 'manufacturer' & 'locale' are missing
    Given As a user I want to execute 'main-types' endpoint
    When I set headers as
      | contentType | application/json |
    And I as a user submit the 'GET' request
    Then Verify response status code is '400'
    And Verify error is received
      | Bad Request |

  @missing-params @regression
  Scenario: Verify 'Bad Request' error is received when required query param 'manufacturer' is missing
    Given As a user I want to execute 'main-types' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'GET' request
    Then Verify response status code is '400'
    And Verify error is received
      | Bad Request |

  @request-type
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'POST'
    Given As a user I want to execute 'main-types' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'POST' request
    Then Verify response status code is '405'
    And Verify error is received
      | Method Not Allowed |

  @request-type
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'PUT'
    Given As a user I want to execute 'main-types' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'PUT' request
    Then Verify response status code is '403'

  @request-type
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'PATCH'
    Given As a user I want to execute 'main-types' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'PATCH' request
    Then Verify response status code is '403'

  @request-type @regression
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'DELETE'
    Given As a user I want to execute 'main-types' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'DELETE' request
    Then Verify response status code is '403'

  @invalid-values @regression
  Scenario: Verify empty 'wkda' is received, when user provides invalid manufacturer code as 'BMW'
    Given As a user I want to execute 'main-types' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
      | manufacturer | BMW |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify 'wkda' does-not contain any values

  @invalid-values
  Scenario: Verify empty 'wkda' is received, when user provides invalid value valid manufacture code = 930 (invalid)
    Given As a user I want to execute 'main-types' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
      | manufacturer | 999 |
      | main-type    | XUV  |
    And I as a user submit the 'GET' request
    Then Verify response status code is '200'
    And Verify 'wkda' does-not contain any values

