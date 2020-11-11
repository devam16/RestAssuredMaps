Feature: Validating Updation of Place Address 


@UpdatePlace
Scenario: Verify Updation of Place APIs
	Given Update Place Payload with "ApartmentNo 20, Above Central Perk"
	When User Calls "UpdatePlaceAPI" with "PUT" HTTP request
	Then the API call is successful with status code 200
	Then "msg" in the Response body is "Address successfully updated"
	And verify address updated maps to "ApartmentNo 20, Above Central Perk" using "GetPlaceAPI"



@DeletePlace
Scenario: Verify Delete Place API
	Given Delete Place Payload
	When User Calls "DeletePlaceAPI" with "POST" HTTP request
	Then the API call is successful with status code 200
	And "status" in the Response body is "OK"
