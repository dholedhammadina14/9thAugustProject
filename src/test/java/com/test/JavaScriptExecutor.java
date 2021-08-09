package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class JavaScriptExecutor {
	WebDriver driver=null;
	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
	}
	@Test
	public void javaScriptClickTest() {
		driver.get("file:///C:/Users/prajwal/Desktop/selenium/Offline%20Website/Offline%20Website/index.html");
		WebElement register=driver.findElement(By.partialLinkText("Register"));
		javaScriptClick(driver, register);
		WebElement name=driver.findElement(By.id("name"));
		javaScriptSendKeys(driver, name, "Dhamma");
		javaScriptHighlighElement(driver, name);
		
	}
	@Test
	public void scrollBar() {
		driver.get("http://demo.guru99.com/test/guru99home/");
		WebElement Element = driver.findElement(By.linkText("Linux"));
		javaScriptScrollBar(driver, Element);
	}
	private void javaScriptClick(WebDriver driver, WebElement webElem) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", webElem);
	}
	
	private void javaScriptSendKeys(WebDriver driver, WebElement webElem,String text) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+text+"';", webElem);
	}
	
	private void javaScriptHighlighElement(WebDriver driver, WebElement webElem) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border='6px solid pink'", webElem);
	}
	private void javaScriptScrollBar(WebDriver driver,WebElement webele) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("arguments[0].scrollIntoView();", webele);
		//js.executeScript("window.scrollBy(0,10000)");
	}

}
