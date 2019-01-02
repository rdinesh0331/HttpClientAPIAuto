package com.qa.httptests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.HTTPClient.RestClient;

public class GetAPITestCase {
	
	 RestClient restclient;
	 String url = "http://restapi.demoqa.com/utilities/weather/city";
	 String apiurl;
	
	@BeforeMethod
	public  void setup() {
		apiurl = url+"/pune";
		}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		CloseableHttpResponse response = restclient.get(apiurl);
		
		//get status code
		int statuscode = response.getStatusLine().getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 200);
	
		//convert json response object to string using getEntity method
		HttpEntity httpentity =response.getEntity();
		String bodyresponse = EntityUtils.toString(httpentity);
		System.out.println(bodyresponse);
		
		//get header contents
		Header headers[] = response.getAllHeaders();
		HashMap<String, String> hmap = new HashMap<String, String>();
		for (Header header: headers) {
			hmap.put(header.getName(), header.getValue());
		}
		
		System.out.println(hmap);
		System.out.println(hmap.get("Content-Type"));
		
	}

}
