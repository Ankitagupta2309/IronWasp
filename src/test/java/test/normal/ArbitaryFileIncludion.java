package test.normal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
/*
 * Arbitrary File Inclusion
 */
public class ArbitaryFileIncludion {

	@Test
	public void test() throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://127.0.0.1/mutillidae/index.php?page=arbitrary-file-inclusion.php");
		String source = driver.getPageSource();
		if (source.contains("Arbitrary File Inclusion")) {
			System.out.println("Page loaded");
		}
		else
			System.out.println("Page not loaded, run again");
		System.out.println(driver.getTitle());
		driver.quit();
	}	
}
