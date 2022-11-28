package com.employee.base;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.employee.utilities.*;

public class TestBaseClass {
	public static RequestSpecification httprequest;
	public static Response response;
	public static String empID="2";
	public Logger logger;
	ReadConfig readConfig = new ReadConfig();

	@BeforeClass
	public void setup() {

		logger = Logger.getLogger("EmployeeRestAPI");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);

		RestAssured.baseURI = readConfig.getBaseURI();
		httprequest = RestAssured.given();
		
	}

}
