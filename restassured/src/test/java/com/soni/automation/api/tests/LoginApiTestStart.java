package com.soni.automation.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response; 
import io.restassured.specification.RequestSpecification;

/**	
 * 
 * Interview Answer ⭐⭐⭐⭐⭐
 Q: When does method chaining work?
A good answer is:Method chaining works when a method returns an object that provides the next method in the chain. In fluent APIs like Rest Assured, methods such as header() and body() return the same RequestSpecification object (typically by returning this), allowing multiple method calls on the same object. The chain ends when a method returns a different type that doesn't support the next operation.
⭐ Easy Formula to Remember
Don't remember:"If every method returns something, chaining works." ❌
Remember this:Method chaining depends on the return type and the methods available on that returned object—not simply on the fact that a method returns a value.
That's the fundamental rule used by Java libraries like Rest Assured, Selenium, Playwright, and the Java Stream API.
 * 
 */
public class LoginApiTestStart {
	
	 @Test(description=" Test the login API functionality")
	public void testLoginApi() {
		 RestAssured.baseURI = "http://64.227.160.186:8080"; // no need to create object of RestAssured class as all methods are static
		 RequestSpecification x = RestAssured.given();// focus  returntype of given method is RequestSpecification  // java is typed langenuage so we can use method chaining to call multiple methods on the same object
		 RequestSpecification y=x.header("Content-Type", "application/json");
		 RequestSpecification z =  y.body("{\r\n"
		 					    		+ "  \"username\": \"aks.igec@gmail.com\",\r\n"
		 					    		+ "  \"password\": \"Kripalukunj@99\"\r\n"
		 					    		+ "}");
		   Response  res=  z.post("/api/auth/login");
	 
		   System.out.println("Response code is "+res.getStatusCode());
		   
		   System.out.println("Response body is "+res.asPrettyString());
		                      
		   Assert.assertEquals(res.getStatusCode(), 200, "Status code is not 200");
		                                                     
	    
		
	}

}

