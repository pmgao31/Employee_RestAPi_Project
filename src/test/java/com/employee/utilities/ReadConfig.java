package com.employee.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;

	public ReadConfig() {
		File src=new File("./ConfigurationFiles/config.properties");
		try {
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		}catch (Exception e) {
			System.out.println("Exception is "+e.getMessage());
		}
		
	}
	public  String getBaseURI() {
		String baseURI =pro.getProperty("baseURI");
		return baseURI;
		
	}
	public  String getTestData() {
		String testData =pro.getProperty("testData");
		return testData;
		
	}	
	
}
