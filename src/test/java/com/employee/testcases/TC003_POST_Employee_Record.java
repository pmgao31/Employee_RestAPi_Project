package com.employee.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.employee.base.TestBaseClass;

import io.restassured.http.Method;
import com.employee.utilities.*;

public class TC003_POST_Employee_Record extends TestBaseClass {
	ReadConfig readConfig = new ReadConfig();
	
	@SuppressWarnings("unchecked")
	@Test(dataProvider = "empdataprovider", priority = -1)
	void postemployee(String ename, String Esalary, String eAGE) {
		logger.info("$$ Started TC003 Post Employee Record $$");

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

	@DataProvider(name = "empdataprovider")
	String[][] getEmpData() throws Exception {

		String path =readConfig.getTestData() ;

		int rowcount = XLUtils.getRowCount(path, "Sheet1");
		int cellcount = XLUtils.getCellCount(path, "Sheet1", 1);
		String empdata[][] = new String[rowcount][cellcount];

		for (int i = 1; i <= rowcount; i++) {
			for (int j = 0; j < cellcount; j++) {
				empdata[i - 1][j] =XLUtils.getCellData(path, "Sheet1", i, j);

			}
		}
		return empdata;

	}

	@AfterClass
	void teardown() {
		logger.info("$$ Finished TC003 POST Employee Record $$");
	}
}
