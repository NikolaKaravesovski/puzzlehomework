package tests;

import java.time.Duration;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test1 {
	 public WebDriver driver = new ChromeDriver();
		
	  @Before 
	     public void testSetUp() {
		  System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		  driver.get("https://dineshvelhal.github.io/testautomation-playground");
	    }
	  
	  @After
	    public void testTearDown()  throws InterruptedException {
		  Thread.sleep(5000);
		  driver.quit();
	    }
	  
	  @Test
	  
	    public void samplePages() throws InterruptedException {
		  driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/div/div[2]/a")).click();
		  driver.findElement(By.id("user")).sendKeys("admin");
		  driver.findElement(By.id("password")).sendKeys("admin");
		  driver.findElement(By.id("login")).click();
		  driver.findElement(By.id("rad_medium")).click();
		  driver.findElement(By.xpath("//*[@id=\"select_flavor\"]/option[2]")).click();
		  driver.findElement(By.id("green_olive")).click();
		  driver.findElement(By.id("tomoto")).click();
		  driver.findElement(By.id("quantity")).sendKeys("3");
		  driver.findElement(By.id("submit_button")).click();
		   WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(35));
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#added_message")));
		   driver.findElement(By.cssSelector("#added_message")).isDisplayed();	
	   }
	  }