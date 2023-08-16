import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class SampleTest {
	
	 WebDriver driver;
	
	@Before
	public void setUp() {
        System.setProperty("webdriver.chrome.driver","./exe/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
	}

	@Test
    public void アクセス可能か() {

        driver.get("http://localhost:8080/RaiseHand/login.html");

//        System.out.println(driver.getTitle()); 
//        System.out.println(driver.getCurrentUrl()); 
//        System.out.println(driver.getPageSource());
        


        WebElement message = driver.findElement(By.id("form"));
        assertEquals("post",message.getAttribute("method"));
        assertTrue(message.getAttribute("action").contains("LoginServlet"));

    }
	
	@After
	public void teardown() {
        driver.quit();
	}

}
