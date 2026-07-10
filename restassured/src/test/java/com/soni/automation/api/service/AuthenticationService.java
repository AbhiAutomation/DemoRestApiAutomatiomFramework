package com.soni.automation.api.service;

import com.soni.automation.api.base.BaseService;

import io.restassured.response.Response;

public class AuthenticationService  extends BaseService{
	
	private static final String BASE_PATH = "/api/auth/"; // this is having all Authentication Services like login, logout, register etc as pe SWAGGER documentation
	//AuthService method is called login 
	public Response login(String payload) {
	 return postRequest(payload, BASE_PATH + "login");
	}
	

}
