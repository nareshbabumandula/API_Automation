@smoke
Feature: REST API Testing with reqres.in CRUD operations

Background:
Given I have a valid user ID

@GET
Scenario: Get a user from reqres.in
When I perform a GET operation for users
Then I should get status code as 200 in the response

@POST
Scenario: Create a new user in reqres.in
When I perform a POST operation for the user
Then I should get status code as 201 in the response

@PUT
Scenario: Updating an existing user
When I perform PUT operation for updating user with id 7
Then I should get status code as 200 in the response

@DELETE
Scenario: Deleting an existing user
When I perform DELETE operation for user with id 2
Then I should get status code as 204 in the response

@dryrun
Scenario: Get a user list from the resource
When I perform a GET operation for a from a user list
Then I should get status code as 200 in the response


