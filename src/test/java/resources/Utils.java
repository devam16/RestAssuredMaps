package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	static RequestSpecification reqSpec;
	public RequestSpecification getRequestSpecification() throws IOException
	{
		
		if(reqSpec==null) {
		PrintStream logger= new PrintStream(new FileOutputStream("logs.txt"));
		 reqSpec = new RequestSpecBuilder()
				 .setBaseUri(getGlobalValue("baseURL"))
				 .addHeader("Content-Type","application/json")
				 .addFilter(RequestLoggingFilter.logRequestTo(logger))
				 .addFilter(ResponseLoggingFilter.logResponseTo(logger))
				.addQueryParam("key", "qaclick123").build();	
		 
		 return reqSpec;
		}
		return reqSpec;
		 
	}
	
	public static String getGlobalValue(String key) throws IOException {
		String globalFileLoc = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\global.properties";
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(globalFileLoc);
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response res, String key) {
		String resp = res.asString();
	    JsonPath js=new JsonPath(resp);
	    return js.get(key).toString();
	}

}
