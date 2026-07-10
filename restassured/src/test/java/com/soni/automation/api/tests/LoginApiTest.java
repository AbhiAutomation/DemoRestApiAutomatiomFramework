package com.soni.automation.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.soni.automation.api.service.AuthenticationService;

import io.restassured.response.Response;


public class LoginApiTest  {
	
	 @Test(description=" Test the login API functionality")
	public void testLoginApi() {
		 
		 
		 AuthenticationService authService = new AuthenticationService();
		 Response res= authService.login("{\r\n"
		    		+ "  \"username\": \"aks.igec@gmail.com\",\r\n"
		    		+ "  \"password\": \"Kripalukunj@99\"\r\n"
		    		+ "}");
	
	    System.out.println("Response body is "+res.asPrettyString());
		                      
		   Assert.assertEquals(res.getStatusCode(), 200, "Status code is not 200");
		                                                     
	    
		
	}

}

