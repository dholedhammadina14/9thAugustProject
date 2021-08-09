package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FrameDemo {
	WebDriver driver=null;
	@Test
	public void frameEx() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.automationtesting.in/Frames.html");
		WebElement frameele=driver.findElement(By.xpath("//iframe[@id='singleframe']"));
		driver.switchTo().frame(frameele);
		WebElement textbox=driver.findElement(By.xpath("//input[@type='text']"));
		textbox.sendKeys("dhamma");
	}
	
}
