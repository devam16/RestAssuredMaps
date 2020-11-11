package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException{
		commonStepDef cdf=new commonStepDef();
		
		if(commonStepDef.Place_id==null)
		{
		System.out.println("Executing ADDPLace API by Default Hook");
		cdf.add_place_payload("Homeless", "Persian", "Trego", "4209876543");
		cdf.user_calls_with_post_http_request("AddPlaceAPI", "POST");
		cdf.verify_place_id_generated_maps_to_using("Trego", "GetPlaceAPI");
		}
		
	}
	
	@Before("@UpdatePlace")
	public void beforeUpdateScenario() throws IOException{
		commonStepDef cdf=new commonStepDef();
		System.out.println("Executing ADDPLace API by Default Hook");
		cdf.add_place_payload("Homeless", "Persian", "Trego", "4209876543");
		cdf.user_calls_with_post_http_request("AddPlaceAPI", "POST");
		cdf.verify_place_id_generated_maps_to_using("Trego", "GetPlaceAPI");
		}
	

}
