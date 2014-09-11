package test.normal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import rest.impl.IronWasp;
import browser.FirefoxBrowser;
/*
 * XSS Reflected - Pen Test Tool Lookup
 */
public class CSRF {

	public static void main(String args[]) {
		IronWasp.workflowStart();
		WebDriver driver = FirefoxBrowser.createDriver();
		driver.get("http://127.0.0.1/mutillidae/index.php?page=pen-test-tool-lookup.php");
		
		Select select = new Select(driver.findElement(By.id("idToolSelect")));
		select.selectByValue("1");
		driver.findElement(By.name("pen-test-tool-lookup-php-submit-button")).click();;
		
		String source = driver.getPageSource();
		if (source.contains("WebSecurify")) {
			System.out.println("Tool loaded");
		}
		else
			System.out.println("Tool not loaded, run again");
		driver.quit();
		IronWasp.workflowEnd();
	}	
}
