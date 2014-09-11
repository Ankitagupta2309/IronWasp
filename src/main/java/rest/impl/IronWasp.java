/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.IronWaspConfig;

/**
 *
 * @author ANKITA
 */
public class IronWasp extends IronWaspClient {

	static Response response = null;
	static IronWaspConfig config = null;
	public static int portNumber = 0;
	public static String ipAddress = null;
	static String className = null ;
	static HashMap<String, String> params = null;
	
	static {
		config = new IronWaspConfig();
		portNumber = config.getPortNumber();
		ipAddress = config.getIPAddress();
		params = new HashMap<String, String>();
	}

	public static void workflowStart() {
		workflowStart(params);
	}

	public static void workflowStart(HashMap<String, String> params) {
		className = new Exception().getStackTrace()[2].getClassName();
		params.put("name", className);
		try {
			URL url = new URL(config.getStartLogRangeUrl());
			response = doGetRequest(url, params);
		} catch (MalformedURLException ex) {
			Logger.getLogger(IronWaspClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void workflowEnd() {
		workflowEnd(null);
	}

	public static void workflowEnd(HashMap<String, String> params) {
		try {
			URL url = new URL(config.getEndLogRangeUrl());
			response = doGetRequest(url, params);
		} catch (MalformedURLException ex) {
			Logger.getLogger(IronWaspClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void showStartRequest() {
		showStartRequest(null);
	}

	public static void showStartRequest(HashMap<String, String> params) {
		try {
			URL url = new URL(config.getStartLogRangeUrl());
			showRequest(url, params);
		} catch (MalformedURLException ex) {
			Logger.getLogger(IronWasp.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void showEndRequest() {
		showEndRequest(null);
	}

	public static void showEndRequest(HashMap<String, String> params) {
		try {
			URL url = new URL(config.getEndLogRangeUrl());
			showRequest(url, params);
		} catch (MalformedURLException ex) {
			Logger.getLogger(IronWasp.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void showResposne() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n").append("======================= RESPONSE ==========================");
		sb.append("\n==> ").append("Status: ").append(response.status);
		sb.append("\n==> ").append("Body: ").append(response.body);
		System.out.println(sb.toString());
	}
}
