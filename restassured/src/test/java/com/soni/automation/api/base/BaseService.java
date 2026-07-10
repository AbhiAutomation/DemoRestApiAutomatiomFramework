/**
  BaseService centralizes all common API request configuration.
 *
 * Responsibilities:
 * 1. Create and configure RequestSpecification.
 * 2. Manage Base URI.
 * 3. Configure common headers.
 * 4. Configure authentication.
 * 5. Execute HTTP requests.
 * 6. Return the Response object to the calling service.
  * 
 * Note :It is not responsible for business functionality.
 */
/**
 *   BASE_URI:Rest Assured uses the baseUri() method to configure the server address, so I kept the constant name as BASE_URI to match the framework's terminology and maintain consistency. Functionally, either name works as long as the team follows a consistent convention
 *  "static means there's only one shared copy of the variable1 for the entire class, which avoids unnecessary duplication across objects. final makes the value immutable after initialization, preventing accidental modification. Together, static final is the standard way to define constants like a base URL, timeout, or retry count in Java."
 *  BASE_URI = "http://64.227.160.186:8080"
 *  BASE_URL = "http://64.227.160.186:8080/api/auth/login"  //BASE_URI+ENDPOINT
 */
package com.soni.automation.api.base;

import  static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {
	private static final String BASE_URI = "http://64.227.160.186:8080";// Class Variable (Static Variable) exists once.
	private RequestSpecification requestSpec; //instance variable to hold the RequestSpecification object  because because every object gets its own copy
	 public BaseService()
	 {
		 requestSpec = given().baseUri(BASE_URI).contentType(ContentType.JSON);  
	 }
	 
	 protected  Response  postRequest(String payload, String endpoint) {
		
		 return  requestSpec.body(payload).post(endpoint);
	 }

}
