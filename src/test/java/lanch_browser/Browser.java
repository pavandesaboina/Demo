package lanch_browser;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {
	WebDriver driver;
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.amazon.in/?&ext_vrnc=hi&tag=googhydrabk1-21&ref=pd_sl_7hz2t19t5c_e&adgrpid=58355126069&hvpone=&hvptwo=&hvadid=486458706470&hvpos=&hvnetw=g&hvrand=7743395804458084424&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9301056&hvtargid=kwd-10573980&hydadcr=14453_2154373");

		
	}
  @Test
  public void f() throws Exception {
	  Select shtyp = new Select(driver.findElement(By.xpath("//select[@id='searchDropdownBox']")));
	  shtyp.selectByVisibleText("Amazon Fashion");
	  driver.findElement(By.id("nav-search-submit-button")).click();
	  driver.findElement(By.xpath("(//span[@class='sl-sobe-carousel-sub-card-title'])[2]")).click();
	  driver.findElement(By.xpath("(//span[@class='sl-sobe-carousel-sub-card-title'])[8]")).click();
	  
  
	  
	  String firstwindow = driver.getWindowHandle();
	  driver.findElement(By.xpath("(//div[@class='a-section aok-relative s-image-tall-aspect'])[1]")).click();
	  
	 Set<String> windows = driver.getWindowHandles();
	  
	 
	 Iterator<String> itr = windows.iterator();
	 
	 while(itr.hasNext()) {
		String window = itr.next();
		
		driver.switchTo().window(window);
		
		String st=driver.getTitle();  
		System.out.println(st);   //to know the title of page
		//switch to child window
		if(driver.getTitle().equals("Buy CHKOKKO Men's Regular Fit V Neck Half Sleeves Plain Sports and Gym T-Shirt Mustard Size S at Amazon.in")) {
			 driver.findElement(By.id("add-to-cart-button")).click();
			 driver.findElement(By.name("proceedToRetailCheckout")).click();
			 driver.close();
		}	
		}
	 	//switch back to main window
			Thread.sleep(3000);
			driver.switchTo().window(firstwindow);
			driver.close();
	 }
  @AfterTest
  public void close() throws Exception {
	  
  }
  
}
