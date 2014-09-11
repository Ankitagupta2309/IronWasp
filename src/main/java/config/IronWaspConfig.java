package config;

/**
 *
 * @author ANKITA
 */
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class IronWaspConfig {

	private String configFile="resources/IronWaspConfigProperties.xml";
	private String portNumber = null;
	private String startLogRangeUrl = null;
	private String endLogRangeUrl = null;
	private String ipAddress  = null;
	
	public IronWaspConfig() {

		File file = new File(configFile);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		Document document = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException ex) {
			Logger.getLogger(IronWaspConfig.class.getName()).log(Level.SEVERE, null, ex);
			System.exit(1);
		}
		try {
			document = documentBuilder.parse(file);
		} catch (SAXException ex) {
			Logger.getLogger(IronWaspConfig.class.getName()).log(Level.SEVERE, null, ex);
			System.exit(1);
		} catch (IOException ex) {
			Logger.getLogger(IronWaspConfig.class.getName()).log(Level.SEVERE, null, ex);
			System.exit(1);
		}
		if (document.getElementsByTagName("portNumber").getLength() != 0) {
			portNumber = document.getElementsByTagName("portNumber").item(0).getTextContent();
		} else {
			Logger.getLogger(IronWaspConfig.class.getName()).log(Level.SEVERE, "Cannot find PortNumber in IronWaspConfigProperties.xml");
			System.exit(1);
		}
		if (document.getElementsByTagName("ipAddress").getLength() != 0) {
			ipAddress = document.getElementsByTagName("ipAddress").item(0).getTextContent();
		} else {
			Logger.getLogger(IronWaspConfig.class.getName()).log(Level.SEVERE, "Cannot find ipAddress in IronWaspConfigProperties.xml");
			System.exit(1);
		}
		
		startLogRangeUrl = "http://" + ipAddress + ":" + portNumber + "/ironwasp/api/core/workflow/start";
		endLogRangeUrl = "http://" + ipAddress + ":" + portNumber + "/ironwasp/api/core/workflow/end";
	}
	//Get Port Number

	public int getPortNumber() {
		return Integer.parseInt(portNumber);
	}

	//Get Start Log URL
	public String getStartLogRangeUrl() {
		return startLogRangeUrl;
	}

	//Get End Log URL
	public String getEndLogRangeUrl() {
		return endLogRangeUrl;
	}

	//Get the IP address
	public String getIPAddress() {
		return ipAddress;
	}
}