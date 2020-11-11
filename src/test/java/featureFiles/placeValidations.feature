Feature: Validating Place APIs


@AddPlace
Scenario Outline: Verify Add Place APIs
	Given Add Place Payload with "<address>","<lang>","<name>","<phnNo>"
	When User Calls "AddPlaceAPI" with "POST" HTTP request
	Then the API call is successful with status code 200
	And "status" in the Response body is "OK"
	And "scope" in the Response body is "APP"
	And verify place_id generated maps to "<name>" using "GetPlaceAPI"
	
	Examples:
	|address			|lang			|name			|phnNo			|
	|257/189B			|English		|Lucifer		|0098765787		|
#	|M-1602				|French			|Sergio			|7896754302		|


@DeletePlace
Scenario: Verify Delete Place API
	Given Delete Place Payload
	When User Calls "DeletePlaceAPI" with "POST" HTTP request
	Then the API call is successful with status code 200
	And "status" in the Response body is "OK"
