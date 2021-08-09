package com.test;

import java.io.FileInputStream;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class AddUserTest {
	WebDriver driver = null;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(
				"file:///C:/Users/prajwal/Desktop/selenium/Offline%20Website/Offline%20Website/pages/examples/users.html");
		driver.findElement(By.xpath("//button[text()='Add User']")).click();
	}

	public static String getCellData(int row, int col, String fileName, String sheetName) {
		FileInputStream fis = null;
		Workbook wb = null;
		try {
			fis = new FileInputStream(fileName);
			wb = Workbook.getWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheet(sheetName);
		Cell cell = sheet.getCell(col, row);
		return cell.getContents();
	}

	@Test
	public void verifyAddUser() {

		String fileName = "addUserSheet.xls";
		String sheetName = "add";
		for (int i = 1; i < 5; i++) {

			driver.findElement(By.id("username")).sendKeys(getCellData(i, 0, fileName, sheetName));
			driver.findElement(By.id("mobile")).sendKeys(getCellData(i, 1, fileName, sheetName));
			driver.findElement(By.id("email")).sendKeys(getCellData(i, 2, fileName, sheetName));
			driver.findElement(By.id("course")).sendKeys(getCellData(i, 3, fileName, sheetName));

			if (getCellData(i, 4, fileName, sheetName).equals("Male"))
				driver.findElement(By.id("Male")).click();
			else
				driver.findElement(By.id("Female")).click();

			Select sel = new Select(driver.findElement(By.tagName("select")));
			sel.selectByVisibleText(getCellData(i, 5, fileName, sheetName));

			driver.findElement(By.id("password")).sendKeys(getCellData(i, 6, fileName, sheetName));

			driver.findElement(By.id("submit")).click();

			Alert al = driver.switchTo().alert();
			String msg = al.getText();

			al.accept();

			Assert.assertEquals(msg, "User Added Successfully. You can not see added user.");
		}
	}

	@Test
	public void mobileTest() {
		String fileName = "mobileTest.xls";
		String sheetName = "mobile";

		driver.findElement(By.id("mobile")).sendKeys(getCellData(4, 0, fileName, sheetName));
		String value = driver.findElement(By.id("mobile")).getAttribute("value");
		Assert.assertEquals(value, getCellData(4, 1, fileName, sheetName));

	}
	@Test
	public void emailTest() {
		String fileName = "emailTest.xls";
		String sheetName = "email";

		driver.findElement(By.id("email")).sendKeys(getCellData(4, 0, fileName, sheetName));
		String value = driver.findElement(By.id("email")).getAttribute("value");
		Assert.assertEquals(value, getCellData(4, 1, fileName, sheetName));

	}
}
