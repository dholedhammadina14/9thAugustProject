package com.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportWithScreenShotTest {
	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;
	
	@BeforeSuite
	public void startReport(){
		
		extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/ExtentReportWithScreenshot.html", true);
		extent.addSystemInfo("Host Name", "JBK_Automation")
              .addSystemInfo("Environment", "Automation testing")
              .addSystemInfo("User Name", "JBK");
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Users/prajwal/Desktop/selenium/Offline%20Website/Offline%20Website/index.html");
		driver.manage().window().maximize();
	}
		
	@Test(priority=1)
	public void passtest(){
		test = extent.startTest("passtest");
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	@Test(priority=2)
	public void failtest(){
		test = extent.startTest("failtest");
		String title = driver.getTitle();
		Assert.assertEquals(title, "NoTitle");
	}
	
	@Test(priority=3, groups="smoke")
	public void skiptest(){
		test = extent.startTest("skiptest");
		throw new SkipException("Skipping - This is not ready for testing ");
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(LogStatus.FAIL, "test Case Failed is "+result.getName());
			test.log(LogStatus.FAIL, "test Case Failed is "+result.getThrowable());			
            String screenshotPath = getScreenhot(driver, result.getName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		}else if(result.getStatus() == ITestResult.SKIP){
			test.log(LogStatus.SKIP, "test Case Skipped is "+result.getName());
		}else 
			test.log(LogStatus.PASS, "test Case passed is "+result.getName());

		
		extent.endTest(test);
	}
	private String getScreenhot(WebDriver driver2, String screenshotName) throws IOException {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/"+screenshotName+dateName+".jpg";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
		
	}

	@AfterTest
	public void endReport(){
				driver.close();
                extent.flush();
                extent.close();
    }
}
