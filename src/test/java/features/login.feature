Feature: Login and fetch user
Scenario: Login with valid credentials and fetch user details
Given I have the login payload
When I send the login request
Then I should recieve the token
And I use the token to get user details
And The user name should be Janet


