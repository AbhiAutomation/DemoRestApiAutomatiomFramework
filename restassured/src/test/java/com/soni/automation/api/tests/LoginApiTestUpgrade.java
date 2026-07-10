/*
 * "For enterprise frameworks, we avoid hard-coded values because they reduce reusability, increase maintenance effort, and make the framework difficult to scale."
If the interviewer asks: "What exactly is hard-coded here?"
You can answer:Base URI,endpoint,Username,Password,Content-Type,Request Body,Expected Status Code, and any other values that are directly written in the code instead of being externalized to configuration files or environment variables are considered hard-coded.
 * */

/**
"Does static import improve performance?"
Answer:
"No. Static import is purely a compile-time language feature. It does not improve runtime performance; its purpose is to improve code readability and reduce boilerplate."
 * */

package com.soni.automation.api.tests;
//Static import allows calling RestAssured's static methods (given(), when(), then()) directly,	//resulting in cleaner, more readable, and fluent test code.
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class LoginApiTestUpgrade {
	
	 @Test(description=" Test the login API functionality")
	public void testLoginApi() {
		// RestAssured.baseURI = "http://64.227.160.186:8080"; // rmoved this line as we can directly use baseUri method in method chaining
		// RequestSpecification x = RestAssured.given();// removed restAssured because using import static io.restassured.RestAssured.*; so that we can use its methods directly without class name to improve readability and maintainability of the code
		 Response  res= given().baseUri("http://64.227.160.186:8080").header("Content-Type", "application/json") .body("{\r\n"
		    		+ "  \"username\": \"aks.igec@gmail.com\",\r\n"
		    		+ "  \"password\": \"Kripalukunj@99\"\r\n"
		    		+ "}").post("/api/auth/login");
	   System.out.println("Response code is "+res.getStatusCode());
	   System.out.println("Response body is "+res.asPrettyString());
   
	   Assert.assertEquals(res.getStatusCode(), 200, "Status code is not 200");
		                                                     
	   
		
	}

}
