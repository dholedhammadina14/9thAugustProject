package com.test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class OperatorTest {
	WebDriver driver = null;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(
				"file:///C:/Users/prajwal/Desktop/selenium/Offline%20Website/Offline%20Website/pages/examples/operators.html");
	}

	public static String getCellData(int row, int col) {
		FileInputStream fis = null;
		Workbook wb = null;
		try {
			fis = new FileInputStream("OperatorPageTable.xls");
			wb = Workbook.getWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheet("operator");
		Cell cell = sheet.getCell(col, row);
		return cell.getContents();
	}

	@Test
	public void verifyUserWithAndTimingContact() {
		HashMap<String, List<String>> actHm = new HashMap<String, List<String>>();
		List<WebElement> users = driver.findElements(By.xpath("//td[2]"));
		List<WebElement> contacts = driver.findElements(By.xpath("//td[5]"));
		List<WebElement> timings = driver.findElements(By.xpath("//td[6]"));
		for (int i = 0; i < users.size(); i++) {
			String key = users.get(i).getText();
			String contactNo = contacts.get(i).getText();
			String time = timings.get(i).getText();
			List<String> value = new ArrayList<String>();
			value.add(contactNo);
			value.add(time);
			actHm.put(key, value);
		}
		System.out.println("The Actual HashMap With Name,Contact,Time for available\n" + actHm);
		HashMap<String, List<String>> expHm = new HashMap<String, List<String>>();
		for (int i = 1; i < 6; i++) {
			String key1 = getCellData(i, 1);
			String ucontact = getCellData(i, 4);
			String utime = getCellData(i, 5);
			List<String> value1 = new ArrayList<String>();
			value1.add(ucontact);
			value1.add(utime);
			expHm.put(key1, value1);
		}
		System.out.println("The Expected HashMap With Name,Contact,Time for available\n" + expHm);
		Assert.assertEquals(actHm, expHm);

	}

	@Test
	public void verifyDetailDescOfRole() {
		List<WebElement> roles = driver.findElements(By.xpath("//td[3]"));
		List<String> roleList = new ArrayList<String>();
		for (WebElement role : roles) {
			String role1 = role.getText();
			if (role1.contains("(") && role1.contains(")")) {
				int firstIndex = role1.indexOf('(');
				int lastIndex = role1.lastIndexOf(')');
				String detailsRole = role1.substring(firstIndex + 1, lastIndex);
				// System.out.println(detailsRole+"\n");
				roleList.add(detailsRole);
			}
		}
		for (Iterator<String> iterator = roleList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}
	public double getWorkTime(String text) {
		double inTimeinInt = 0;
		double outTimeinInt = 0;
		double workingTime = 0;
		int firstIndex = text.indexOf(':');
		int lastIndex = text.lastIndexOf(':');

		String inTime = text.substring(firstIndex - 2, firstIndex + 6);
		String outTime = text.substring(lastIndex - 2, lastIndex + 6);

		System.out.println(inTime + "  " + outTime);

		if (inTime.contains("PM"))
			inTimeinInt = Integer.parseInt(inTime.substring(0, 2)) + 12;
		else
			inTimeinInt = Integer.parseInt(inTime.substring(0, 2));

		if (outTime.contains("PM"))
			outTimeinInt = Integer.parseInt(outTime.substring(0, 2)) + 12;
		else

			outTimeinInt = Integer.parseInt(outTime.substring(0, 2));

		if (!text.substring(firstIndex + 1, firstIndex + 3).equals("00")) {
			int min = Integer.parseInt(text.substring(firstIndex + 1, firstIndex + 3));
			double m = (double) min / 60;
			// System.inTimeinInt = inTimeinInt+ m;
		}
		if (!text.substring(lastIndex + 1, lastIndex + 3).equals("00")) {
			int min = Integer.parseInt(text.substring(lastIndex + 1, lastIndex + 3));
			outTimeinInt = outTimeinInt + min / 60;
		}
		workingTime = outTimeinInt - inTimeinInt;
		System.out.println("wroking hours: " + workingTime);
		return workingTime;
	}

	@Test
	public void maxTimeCount() {
		List<WebElement> timings = driver.findElements(By.xpath("//td[6]"));
		
		for (WebElement time : timings) {
			String text = time.getText();
			double workTime=getWorkTime(text);
			System.out.println(workTime);
			}
			

	}

}
