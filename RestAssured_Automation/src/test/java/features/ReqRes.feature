@smoke
Feature: REST API Testing with reqres.in CRUD operations

Background:
Given I have a valid user ID

@GET
Scenario: Get a user from reqres.in
When I perform a GET operation for users
Then I should get a 200 status code and user details

@POST
Scenario: Create a new user in reqres.in
When I perform a POST operation for the user
Then I should get a 201 status code and user details

@PUT
Scenario: Updating an existing user
When I perform PUT operation for updating user with id 7
Then I should get status code 200

@DELETE
Scenario: Deleting an existing user
When I perform DELETE operation for user with id 2
Then I should get status code 204

