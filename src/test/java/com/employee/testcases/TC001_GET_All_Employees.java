package com.employee.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBaseClass;
import io.restassured.http.Method;

public class TC001_GET_All_Employees extends TestBaseClass {

	@BeforeClass
	public void getAllEmployees() {
		logger.info(" $$ Started TC001 Get All Employees API $$ ");

		response = httprequest.request(Method.GET, "/employees");

	}

	@Test(priority = 0)
	void checkResponsebody() {
		logger.info(" $$ Checking Response body $$ ");

		String responsebody = response.getBody().asString();
		logger.info("Response body==> " + responsebody);
		Assert.assertTrue(responsebody != null);
	}

	@Test(priority = 1)
	void checkStatusCode() {
		logger.info(" $$ Checking Status Code $$ ");

		int statuscode = response.getStatusCode();
		logger.info("Status Code==> " + statuscode);
		Assert.assertEquals(statuscode, 200);
	}

	@Test(priority = 2)
	void checkResponseTime() {
		logger.info(" $$ Checking Response Time $$ ");

		long responseTime = response.getTime();
		logger.info("Response Time==> " + responseTime);
		if (responseTime > 2000)
			logger.warn("Response time is greater than 2000");
		Assert.assertTrue(responseTime < 2000);
	}

	@Test(priority = 3)
	void checkStatusline() {
		logger.info(" $$ Checking Status Line $$ ");

		String statusline = response.getStatusLine();
		logger.info("Status Line==> " + statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}

	@Test(priority = 4)
	void checkContentType() {
		logger.info(" $$ Checking Content Type $$ ");

		String contentType = response.getContentType();
		logger.info("Content Type==> " + contentType);
		Assert.assertEquals(contentType, "application/json");
	}

	@Test(priority = 5)
	void checkServerType() {
		logger.info(" $$ Checking Server Type $$ ");

		String serverType = response.header("Server");
		logger.info("Server Type==> " + serverType);
		Assert.assertEquals(serverType, "nginx/1.21.6");
	}

	@AfterClass
	void teardown() {
		logger.info(" $$ Finished TC001 Get All Employees API $$");
	}
}
