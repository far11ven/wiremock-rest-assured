@api @manufacturer @negative
Feature: API Tests related to "car-types/manufacturer"

  @authentication-check @regression
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'POST'
    Given As a user I want to execute 'manufacturer' endpoint without 'wa_key'
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'POST' request
    Then Verify response status code is '401'

  @request-type
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'POST'
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'POST' request
    Then Verify response status code is '405'
    And Verify error is received
      | Method Not Allowed |

  @request-type @regression
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'PUT'
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'PUT' request
    Then Verify response status code is '403'

  @request-type
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'PATCH'
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'PATCH' request
    Then Verify response status code is '403'

  @request-type
  Scenario: Verify "Method Not Allowed" error is received, when user provides request Type as 'DELETE'
    Given As a user I want to execute 'manufacturer' endpoint
    When I set headers as
      | contentType | application/json |
    When I set query params as
      | locale | FR |
    And I as a user submit the 'DELETE' request
    Then Verify response status code is '403'



