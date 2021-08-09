package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class ActionClassDemo {
	WebDriver driver=null;
	@BeforeSuite
	public void setUp() { 
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
	}
	@Test
	public void actionClassMethods() {
		driver.get("file:///C:/Users/prajwal/Desktop/selenium/Offline%20Website/Offline%20Website/index.html");
		driver.findElement(By.partialLinkText("Register")).click();;
		WebElement name=driver.findElement(By.id("name"));
		Actions action=new Actions(driver); 
		//action.keyDown(name,Keys.SHIFT).sendKeys("dhamma").keyUp(name, Keys.SHIFT).sendKeys("Dhamma").build().perform();;	
		action.sendKeys(name,"dhamma").perform();
		
	}
	@Test
	public void dragAndDropSMethod() {
		driver.get("http://demo.guru99.com/test/drag_drop.html");
		WebElement source=driver.findElement(By.xpath("//a[text()=' 5000']"));
		WebElement target=driver.findElement(By.xpath("//h3[contains(text(),'Amount')]//parent::div//child::li[@class='placeholder']"));
		Actions action=new Actions(driver);
		action.dragAndDrop(source, target).build().perform();;
	}
	@Test
	public void site() {
		driver.get("https://www.makemytrip.com/flights/");
		WebElement ele=driver.findElement(By.xpath("//span[text()='Flights']"));
//		WebElement ele1=driver.findElement(By.xpath(""));
//		WebElement ele2=driver.findElement(By.linkText("Hot Meals "));
//		Actions action=new Actions(driver) ;
//		action.moveToElement(ele).moveToElement(ele1).moveToElement(ele2).build().perform();
	}
}
