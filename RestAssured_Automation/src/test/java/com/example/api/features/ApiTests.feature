Feature: REST API Testing with reqres.in CRUD operations

Background:
Given I have a valid user ID

Scenario: Get a user from reqres.in
When I perform a GET operation for the user
Then I should get a 200 status code and user details