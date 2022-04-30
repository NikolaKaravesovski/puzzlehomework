package tests;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.Duration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tests {
    public WebDriver driver = new ChromeDriver();
	
  @Before 
     public void testSetUp() {
	  System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	  driver.get("http://uitestingplayground.com/");
     }
  
  @After
    public void testTearDown()  throws InterruptedException {
	  Thread.sleep(5000);
	  driver.quit();
  }
   
  @Test
   
    public void dynamicId() {
	  driver.findElement(By.xpath("//*[@href=\"/dynamicid\"]")).click();
	  driver.findElement(By.cssSelector(".btn-primary")).click();
	}
  
  @Test
    
    public void classAttribute() {
	  driver.findElement(By.xpath("//*[@href=\"/classattr\"]")).click();
	  driver.findElement(By.className("btn-warning")).click();
	  driver.findElement(By.cssSelector(".btn-primary")).click();
	}
  
  @Test
     
    public void hiddenLayers() throws InterruptedException {
      driver.findElement(By.xpath("//a[normalize-space()='Hidden Layers']")).click();
      driver.findElement(By.id("greenButton")).click();
      driver.findElement(By.id("blueButton")).click();
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript ("document.getElementById('greenButton').value='blueButton';");
      String s = (String) js.executeScript("return document.getElementById('greenButton').value");
      System.out.print("Value entered in hidden field: " +s);
      }
  
   @Test
     
	   public void loadDelay() {
		driver.findElement(By.xpath("//*[@href=\"/loaddelay\"]")).click();
		driver.findElement(By.cssSelector(".btn-primary")).isDisplayed();
	}
	
	@Test
	  
	   public void ajaxData()   {
	   driver.findElement(By.xpath("//*[@href=\"/ajax\"]")).click();
	   driver.findElement(By.cssSelector(".btn-primary")).click();
	   WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(35));
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bg-success")));
	   driver.findElement(By.cssSelector(".bg-success")).isDisplayed();	  
	}
	
	@Test
	  
	   public void clientSideDelay() {
		driver.findElement(By.xpath("//*[@href=\"/clientdelay\"]")).click();
		driver.findElement(By.cssSelector(".btn-primary")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(35));
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bg-success")));
		   driver.findElement(By.cssSelector(".bg-success")).isDisplayed();	
		}
	
	@Test
	  
	   public void click() {
		driver.findElement(By.xpath("//*[@href=\"/click\"]")).click();
		driver.findElement(By.cssSelector(".btn-primary")).click();
	}
	
	@Test
	 
	   public void textInput() {
		driver.findElement(By.xpath("//*[@href=\"/textinput\"]")).click();
		driver.findElement(By.id("newButtonName")).sendKeys("some text");
		driver.findElement(By.id("updatingButton")).click();
	}
	
	@Test
	 
	public void scrollBars() throws InterruptedException {
		driver.findElement(By.xpath("//*[@href=\"/scrollbars\"]")).click();
		WebElement element = driver.findElement(By.id("hidingButton"));
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	@Test
	 
	public void dynamicTable() {
		driver.findElement(By.xpath("//*[@href=\"/dynamictable\"]")).click();
		String actualResult = driver.findElement(By.xpath("//span[text()='Chrome']/parent::*/span[contains(text(),'%')]")).getText();
		String expectedResult = driver.findElement(By.cssSelector(".bg-warning")).getText();
		System.out.println("actualResult: " + actualResult);
		System.out.println("expectedResult: " + expectedResult);
		assertTrue(expectedResult.contains(actualResult));
	}
	
	
	@Test
	
	public void progressbar() {
        driver.navigate().to("http://uitestingplayground.com/progressbar");
        driver.findElement(By.id("startButton")).click();
        Wait<WebDriver>wait = new FluentWait<WebDriver>(driver)
            .pollingEvery(Duration.ofNanos(100))
            .withTimeout(Duration.ofSeconds(30));
        WebElement stopButton = driver.findElement(By.id("stopButton"));   
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style='width: 75%']")));
        WebElement progressBar = driver.findElement(By.xpath("//div[@style='width: 75%']"));   
        if(progressBar.isDisplayed()) {
            stopButton.click();
           }
        assertTrue(progressBar.isDisplayed());
	}
	
	@Test
	  
	  public void visibility() {
		driver.findElement(By.xpath("//*[@href=\"/visibility\"]")).click();
		driver.findElement(By.cssSelector("#removedButton")).isDisplayed();
		driver.findElement(By.cssSelector("#zeroWidthButton")).isDisplayed();
		driver.findElement(By.cssSelector("#overlappedButton")).isDisplayed();
		driver.findElement(By.cssSelector("#transparentButton")).isDisplayed();
		driver.findElement(By.cssSelector("#invisibleButton")).isDisplayed();
		driver.findElement(By.cssSelector("#notdisplayedButton")).isDisplayed();
		driver.findElement(By.cssSelector("#offscreenButton")).isDisplayed();
	    driver.findElement(By.id("hideButton")).click();
		 Boolean exists = driver.findElements( By.id("removedButton") ).size() != 0;
		 assertFalse(exists);
	}

	@Test
	 
	  public void sampleApp() {
		driver.findElement(By.xpath("//*[@href=\"/sampleapp\"]")).click();
		driver.findElement(By.xpath("//*[@name=\"UserName\"]")).sendKeys("test111");
		driver.findElement(By.xpath("//*[@name=\"Password\"]")).sendKeys("pwd");
		driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
		String verifyUserName = driver.findElement(By.xpath("//*[@id=\"loginstatus\"]")).getText();
	    assertEquals(verifyUserName, "Welcome, test111!");
	}
	
	@Test
	 
	   public void mouseOver() {
		driver.findElement(By.xpath("//*[@href=\"/mouseover\"]")).click();
		Actions act = new Actions(driver);
	    WebElement btnElement = driver.findElement(By.xpath("/html/body/section/div/div[1]/a"));
        act.doubleClick(btnElement).perform();
       
	}
	
	@Test
	 
	public void nonBreakingSpace(){
		driver.findElement(By.xpath("//*[@href=\"/nbsp\"]")).click();
		driver.findElement(By.cssSelector("body > section > div > button")).click();
	}
	
	@Test
	 
	  public void overlappedElement() {
		driver.findElement(By.xpath("//*[@href=\"/overlapped\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"id\"]")).sendKeys("test111");
	    driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("Nikola");
	   }
	}
