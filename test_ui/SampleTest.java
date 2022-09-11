

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import _data.Const;
import _old.model.IpAddress;
import model.Pc;

@Ignore
public class SampleTest {


	@Before
	public void setUp() {
	}

	@Test
	public void sample() throws Exception {
		System.setProperty("webdriver.chrome.driver","./exe/chromedriver104.0.5112.79.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/RaiseHand/index.html");
		Thread.sleep(5000);
		WebElement body = driver.findElement(By.tagName("Body"));
		assertNotNull(body);
	}

}
