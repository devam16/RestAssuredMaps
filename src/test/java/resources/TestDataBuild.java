package resources;

import java.util.ArrayList;

import pojo.AddPlaceAPI;
import pojo.location;

public class TestDataBuild {
	
	
	public static AddPlaceAPI addPlacePayload(String address,String language,String name,String phnNo) {
		
		AddPlaceAPI apr=new AddPlaceAPI();
		apr.setAccuracy(50);
		apr.setAddress(address);
		apr.setLanguage(language);
		apr.setName(name);
		apr.setPhone_number(phnNo);
		ArrayList<String> types=new ArrayList<String>();
		types.add("shoe");
		types.add("park");
		apr.setTypes(types);
		location loc=new location();
		loc.setLat(87.0009);
		loc.setLng(-90.876);
		apr.setLocation(loc);
		
		return apr;
	}
	
public static String DeletePlaceAPI(String id) {
		
		return "{\r\n" + 
				"    \"place_id\":\""+id+"\"\r\n" + 
				"}\r\n" + 
				"";
	}

public static String UpdatePlaceAPI(String id, String address) {
	
	String req= "{\r\n" + 
			"\"place_id\":\""+id+"\",\r\n" + 
			"\"address\":\""+address+"\",\r\n" + 
			"\"key\":\"qaclick123\"\r\n" + 
			"}\r\n" + 
			"";
	return req;
}

}
