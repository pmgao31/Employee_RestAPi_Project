package com.employee.testcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.employee.base.TestBaseClass;

import io.restassured.http.Method;
public class TC004_POST_Employee_Record_with_Random_data extends TestBaseClass {

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "empdataprovider", priority = -1)
	void postemployee(String ename, String Esalary, String eAGE) {
		logger.info("$$ Started TC004 Post Employee Record $$");

		JSONObject requestparams = new JSONObject();
		requestparams.put("name", ename);
		requestparams.put("Salary", Esalary);
		requestparams.put("age", eAGE);

		httprequest.header("Content-Type", "application/json");

		httprequest.body(requestparams.toJSONString());
		response = httprequest.request(Method.POST, "/create");

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
		logger.info("Content Length==> " + contentlength);
		Assert.assertTrue(Integer.parseInt(contentlength) < 1500);
	}

	@BeforeClass

	@DataProvider(name = "empdataprovider")
	String[][] getEmpData() throws Exception {

		String enam = "John " + RandomStringUtils.randomAlphabetic(5);
		String esalary = RandomStringUtils.randomNumeric(5);
		String eage = RandomStringUtils.randomNumeric(2);
		String empdata[][] = { { enam, esalary, eage } };
		return empdata;

	}

	@AfterClass
	void teardown() {
		logger.info("$$ Finished TC004 POST Employee Record $$");
	}
}
