package test.normal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import rest.impl.IronWasp;
import browser.FirefoxBrowser;
/*
 * DOM XSS - HTML 5 Storage
 */
public class DomXSS {

	public static void main(String args[]) {
		IronWasp.workflowStart();
		WebDriver driver = FirefoxBrowser.createDriver();
		driver.get("http://127.0.0.1/mutillidae/index.php?page=html5-storage.php");
		
		driver.findElement(By.id("idDOMStorageKeyInput")).sendKeys("IronWaspKey");
		driver.findElement(By.id("idDOMStorageItemInput")).sendKeys("IronWaspItem");
		driver.findElement(By.xpath("//*[@id=\"idForm\"]/table[2]/tbody[3]/tr[2]/td[4]/input")).click();;
		
		String source = driver.getPageSource();
		if (source.contains("Added key IronWaspKey to Session storage")) {
			System.out.println("Key added");
		}
		else
			System.out.println("key not added, run again");
		driver.quit();
		IronWasp.workflowEnd();
	}	
}
