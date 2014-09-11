package test.normal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import rest.impl.IronWasp;
import browser.FirefoxBrowser;
/*
 * Login
 */
public class Login {

	public static void main(String args[]) {
		IronWasp.workflowStart();
		WebDriver driver = FirefoxBrowser.createDriver();
		driver.get("http://127.0.0.1/mutillidae/index.php?page=login.php");
		
		driver.findElement(By.name("username")).sendKeys("ironWaspTest");
		driver.findElement(By.name("password")).sendKeys("test");
		driver.findElement(By.name("login-php-submit-button")).click();;
		
		if (driver.getCurrentUrl().contains("http://127.0.0.1/mutillidae/index.php?popUpNotificationCode=AU1")) {
			System.out.println("Logged in");
		}
		else
			System.out.println("Not Logged in, run again");
		driver.quit();
		IronWasp.workflowEnd();
	}	
}
