package rest.impl;

/**
 *
 * @author ANKITA
 */
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IronWaspClient {

	private static Scanner scanner;
	private static IronWaspClient client;
	
	static {
		client = new IronWaspClient();
	}

	public static Response doGetRequest(URL url) {
		GET get = client.new GET(url, null);
		return sendRequest(get);
	}

	public static Response doGetRequest(URL url, HashMap<String, String> params) {
		GET get = client.new GET(url, params);
		return sendRequest(get);
	}

	private static Response sendRequest(Request request) {
		HttpURLConnection conn = null;
		Response response = null;
		try {
			conn = (HttpURLConnection) request.getUrl().openConnection();

			int status = conn.getResponseCode();

			if (status != HttpURLConnection.HTTP_OK) {
				response = new Response(status, conn.getResponseMessage());
			} else {
				response = new Response(status, readInputStream(conn.getInputStream()));
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return response;
	}

	private static String readInputStream(InputStream is) {
		scanner = new Scanner(is, "UTF-8");
		Scanner s = scanner.useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	/** 
	 * Convenience method to output everything about the request 
	 */
	 public static void showRequest(URL url) {
		 showRequest(url, null);
	 }

	 public static void showRequest(URL url, HashMap<String, String> params) {
		 GET req = client.new GET(url, params);
		 StringBuilder sb = new StringBuilder();
		 sb.append("\n").append("======================= REQUEST ==========================");
		 try {
			 sb.append("\n==> ").append("URL: ").append(req.getUrl());
		 } catch (MalformedURLException ex) {
			 Logger.getLogger(IronWaspClient.class.getName()).log(Level.SEVERE, null, ex);
		 }

		 if (req instanceof GET && ((GET) req).params != null) {
			 for (String param : ((GET) req).params.keySet()) {
				 sb.append("\n===> ").append("Param: ").append(param).append("=").append(((GET) req).params.get(param));
			 }
		 }
		 System.out.println(sb.toString());
	 }

	 private class Request {

		 public URL baseUrl;

		 public Request(URL baseUrl) {
			 this.baseUrl = baseUrl;
		 }

		 public URL getUrl() throws MalformedURLException {
			 return baseUrl;
		 }
	 }

	 private class GET extends Request {

		 public HashMap<String, String> params;

		 public GET(URL baseUrl, HashMap<String, String> param) {
			 super(baseUrl);
			 params = param;
		 }

		 @Override
		 public URL getUrl() throws MalformedURLException {
			 StringBuilder sb = new StringBuilder(baseUrl.toString());
			 if (params != null && params.size() > 0) {
				 sb.append(createParamString());
			 }
			 return new URL(sb.toString());
		 }

		 private String createParamString() {
			 StringBuilder sb = new StringBuilder();
			 if (params != null && params.size() > 0) {
				 sb.append("?");
				 //TODO: Need to encode the paramater values 
				 for (String param : params.keySet()) {
					 sb.append(param).append("=").append(params.get(param)).append("&");
				 }
				 sb.deleteCharAt(sb.length() - 1);
			 }
			 return sb.toString();
		 }
	 }

	 public static class Response {

		 public int status;
		 public String body;

		 protected Response(int status, String body) {
			 this.status = status;
			 this.body = body;
		 }
	 }
} 