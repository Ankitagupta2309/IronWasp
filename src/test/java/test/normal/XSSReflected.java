package test.normal;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import browser.FirefoxBrowser;
/*
 * CSRF- Add to tour blog
 */
public class XSSReflected{

	@Test
	public static void test() {
		WebDriver driver = FirefoxBrowser.createDriver();
		driver.get("http://127.0.0.1/mutillidae/index.php?page=add-to-your-blog.php");
		String source = driver.getPageSource();
		if (source.contains("Arbitrary File Inclusion")) {
			System.out.println("Page loaded");
		}
		else
			System.out.println("Page not loaded, run again");
		driver.quit();
	}	
}
