package com.test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;

public class DemoTest1 {
	WebDriver driver = null;

	@Test
	public void test01() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Users/prajwal/Desktop/selenium/Offline%20Website/Offline%20Website/index.html");
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
	}

	@Test
	public void verifyExcleData() throws Exception {
		FileInputStream fis = new FileInputStream("ExcleData.xls");
		Workbook workbook = Workbook.getWorkbook(fis);
		Sheet sheet = workbook.getSheet("data");
		int rows = sheet.getRows();
		int cols = sheet.getColumns();
		HashMap<Integer, List<String>> expData = new HashMap<Integer, List<String>>();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < rows; i++) {

			for (int j = 0; j < cols; j++) {
				list.add(sheet.getCell(j, i).getContents());
				expData.put(i, list);

			}
		}
		Iterator<Entry<Integer, List<String>>> it = expData.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, List<String>> mp = it.next();

			List<String> val = mp.getValue();

			System.out.println(mp.getKey() + " : " + val);
		}
		
		HashMap<Integer, List<String>> actdata=new HashMap<Integer, List<String>>();
		List<String> ng=new ArrayList<String>();
		ng.add("Kiran");
		ng.add("Male");
		actdata.put(1, ng);
		ng.add("Sagar");
		ng.add("Male");
		actdata.put(2, ng);
		ng.add("Monica");
		ng.add("Female");
		actdata.put(3, ng);
		ng.add("Kimaya");
		ng.add("Female");
		actdata.put(4, ng);
	}
}
