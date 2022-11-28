package com.employee.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBaseClass;

import io.restassured.http.Method;

public class TC002_GET_Single_Employee_Details extends TestBaseClass {
	@BeforeClass
	public void getAllEmployees() {
		logger.info(" $$ Started TC002 Get Single Employee ID $$");

		response = httprequest.request(Method.GET, "/employee/" + empID);

	}

	@Test(priority = 0)
	void checkResponsebody() {
		
		String responsebody = response.getBody().asString();
		logger.info("Response body==> " + responsebody);
		Assert.assertTrue(responsebody != null);
		Assert.assertTrue(responsebody.contains(empID));
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

	@Test(priority = 3)
	void checkStatusline() {
		
		String statusline = response.getStatusLine();
		logger.info("Status Line==> " + statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}

	@Test(priority = 4)
	void checkContentType() {
		
		String contentType = response.getContentType();
		logger.info("Content Type==> " + contentType);
		Assert.assertEquals(contentType, "application/json");
	}

	@Test(priority = 5)
	void checkContentLength() {
		
		String contentlength = response.header("Content-Length");
		logger.info("Server Type==> " + contentlength);
		Assert.assertTrue(Integer.parseInt(contentlength)<1500);
	}

	@AfterClass
	void teardown() {
		logger.info(" $$ Finished TC002 Get Single Employee detail $$");
	}
}
