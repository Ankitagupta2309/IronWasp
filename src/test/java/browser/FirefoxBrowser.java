package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import rest.impl.IronWasp;

public class FirefoxBrowser {
	protected static WebDriver driver = null;

	public static WebDriver createDriver() {
		FirefoxProfile profile = new FirefoxProfile(); 
		profile.setPreference("network.proxy.type", 1);
		profile.setPreference("network.proxy.http", IronWasp.ipAddress);
		profile.setPreference("network.proxy.http_port", IronWasp.portNumber);
		profile.setPreference("network.proxy.ssl", IronWasp.ipAddress);
		profile.setPreference("network.proxy.ssl_port", IronWasp.portNumber);
		profile.setPreference("network.proxy.no_proxies_on","");
		return driver = new FirefoxDriver(profile);
	}
}
