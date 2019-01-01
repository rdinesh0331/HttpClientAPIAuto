package com.qa.HTTPClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class RestClient {
	
	//1. get call
	public  CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		
		//create httpclient
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		//create object for http get request
		HttpGet httpget = new HttpGet(url);
		
		//execute the api get request
		CloseableHttpResponse response = httpclient.execute(httpget);
		return response;
	}
	
	//2. post call
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		
		//create httpclient
		CloseableHttpClient httpclient=HttpClients.createDefault();
		
		//create object for http post request
		HttpPost httppost = new HttpPost(url);
		
		//add payload using setEntity method (Entity indirectly called as Payload)
		httppost.setEntity(new StringEntity(entityString) );
		
		//add headers
		for (Entry<String, String> header : headerMap.entrySet()) {
			httppost.addHeader(header.getKey(), header.getValue());
			
		}
		//execute the api post request
		CloseableHttpResponse response = httpclient.execute(httppost);
		return response;
		
	}
}
