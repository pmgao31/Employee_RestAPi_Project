package com.employee.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBaseClass;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC006_DELETE_Employee_Record extends TestBaseClass {
	public static String empdata="4";
	@BeforeClass
	void postemployee() {
		logger.info("$$ Started TC006 Delete Employee Record $$");
//		Response response1= httprequest.request(Method.GET, "/employees");
//		JsonPath jsp = response1.jsonPath();
//		String empdata = jsp.get("[0].id");
//		System.out.println(empdata);
		
		response = httprequest.request(Method.PUT, "/delete/" + empdata);

	}

	@Test(priority = 0)
	void checkResponsebody() {

		String responsebody = response.getBody().asString();
		logger.info("Response body==> " + responsebody);
		Assert.assertTrue(responsebody != null);
		Assert.assertTrue(responsebody.contains(empdata));
	}

	@Test(priority = 1)
	void checkStatusCode() {

		int statuscode = response.getStatusCode();
		logger.info("Status Code==> " + statuscode);
		Assert.assertEquals(statuscode, 200);
	}

	@Test(priority = 2)
	void checkResponseTime() {

		long responseTime = response.getTime();
		logger.info("Response Time==> " + responseTime);
		if (responseTime > 2000)
			logger.warn("Response time is greater than 2000");
		Assert.assertTrue(responseTime < 2000);
	}

	@AfterClass
	void teardown() {
		logger.info("$$ Finished TC006 Delete Employee Record $$");
	}
}
