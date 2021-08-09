package com.test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DemoTest {
	WebDriver driver;

	@Test
	public void Test01() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Users/prajwal/Desktop/selenium/Offline%20Website/Offline%20Website/index.html");
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		Assert.assertEquals(driver.getTitle(), "JavaByKiran | Dashboard");
	}

	
	public static String getCellData(int row, int col) {
		FileInputStream fis = null;
		Workbook wb = null;
		try {
			fis = new FileInputStream("UserTable.xls");
			wb = Workbook.getWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheet("user");
		Cell cell = sheet.getCell(col, row);
		return cell.getContents();
	}

	@Test
	public void verifyTable() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(
				"file:///C:/Users/prajwal/Desktop/selenium/Offline%20Website/Offline%20Website/pages/examples/users.html");
		HashMap<String, List<String>> actData = new HashMap<String, List<String>>();
		String key = null;
		List<String> value = new ArrayList<String>();
		List<WebElement> headers = driver.findElements(By.tagName("th"));
		for (WebElement e : headers) {
			if (headers.indexOf(e) == 0)
				key = e.getText();
			else
				value.add(e.getText());
		}
		actData.put(key, value);
		key = null;
		value = null;
		int rows = driver.findElements(By.tagName("tr")).size();
		for (int i = 2; i <= rows; i++) {
			value = new ArrayList<String>();
			List<WebElement> rowData = driver.findElements(By.xpath("//tr[" + i + "]/td"));
			for (WebElement row : rowData) {

				if (rowData.indexOf(row) == 0)
					key = row.getText();
				else
					value.add(row.getText());
			}
			actData.put(key, value);

		}
		System.out.println(actData);

		HashMap<String, List<String>> expData = new HashMap<String, List<String>>();
		String expKey = null;
		for (int i = 0; i < 5; i++) {
			ArrayList<String> expAl = new ArrayList<String>();
			expKey = getCellData(i, 0);
			for (int j = 1; j < 8; j++) {
				expAl.add(getCellData(i, j));
			}
			expData.put(expKey, expAl);
		}

		System.out.println(expData);

	}

}
