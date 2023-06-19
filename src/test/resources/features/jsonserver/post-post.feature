Feature: Post Creating
  As a Frontend Developer
  I want to add Post through API
  So that It can be available to applications.

  Background:
    Given The Endpoint "http://localhost:3000/posts" is available

  @post-200
  Scenario: Create Post
    When A Post Request is sent with values "Testing", "Frontend"
    Then A Response is received with Status 200
    And An Post Response is included in Response Body, with values id, "Testing", "Frontend"

