package test.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import base.Base;
import browser.FirefoxBrowser;
/*
 * Unvalidated Redirects and forwards
 */
public class Redirects extends Base {

	@Test
	public static void test() {
		WebDriver driver = FirefoxBrowser.createDriver();
		driver.get("http://127.0.0.1/mutillidae/index.php?page=credits.php");

		driver.findElement(By.xpath("/html/body/table/tbody/tr[4]/td[2]/blockquote/div[7]/a")).click();

		if (driver.getCurrentUrl().contains("owasp.org")) {
			System.out.println("redirected");
		}
		else
			System.out.println("Not redirected, run again");
		driver.quit();
	}	
}
