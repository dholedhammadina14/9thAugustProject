package com.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class UserPageTest {
	WebDriver driver = null;
	FileInputStream fis = null;
	FileOutputStream fos = null;
	Workbook wb = null;

	@Test
	public void verifyHeader() {
		int row=1;
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(
				"file:///C:/Users/prajwal/Desktop/selenium/Offline%20Website/Offline%20Website/pages/examples/users.html");
		if(row==1) {
		List<WebElement> headers = driver.findElements(By.tagName("th"));
		for (WebElement header : headers) {
			String text=header.getText();
			ExcelUtils.writeExcel("UserTable.xlsx", "user", text, 0, headers.indexOf(header));
		}
		row++;
		}
		while(row<=5){
			List<WebElement> data = driver.findElements(By.xpath("//tr["+row+"]/td"));//8
			for(WebElement val : data) {
				String text =val.getText();
				ExcelUtils.writeExcel("UserTable.xlsx", "user", text, row-1, data.indexOf(val));			
			}
			row++;
		}
	}
}
