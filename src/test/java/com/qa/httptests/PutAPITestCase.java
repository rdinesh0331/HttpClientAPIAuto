package com.qa.httptests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.HTTPClient.RestClient;
import com.qa.data.UsersUpdate;

public class PutAPITestCase {
	
	String url = "https://reqres.in";
	String apiUrl;
	RestClient restclient;
	
	@BeforeMethod
	public void setup() {
		apiUrl = url+"/api/users/2";
	}
	
	@Test
	public void putAPItest() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		
		//hashmap preparation for header
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//prepare json pay load using jackson api - core and data-bind  APIs	
		//ObjectMapper provides functionality for reading and writing JSON,either to and from basic POJOs (Plain Old Java Objects),
		ObjectMapper mapper = new ObjectMapper();
		UsersUpdate users = new UsersUpdate("Test", "Senior Manager");
		
		//convert java object to JSON string - Serialization or Marshelling
		String userJsonString = mapper.writeValueAsString(users);
		System.out.println(userJsonString);
		
		CloseableHttpResponse response = restclient.put(apiUrl, userJsonString, headerMap);
		
		//get the status code
		int statuscode = response.getStatusLine().getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 200);
		
		//get the json payload in the response
		HttpEntity httpentity = response.getEntity();
		String responseString = EntityUtils.toString(httpentity);
		System.out.println(responseString);
		
		//Convert a normal string to json string using JSONObject class
		JSONObject jsonresponse = new JSONObject(responseString);
		System.out.println(jsonresponse);
		
		//Convert json string to java object - Deserialization or unmarshelling
		UsersUpdate usersobj = mapper.readValue(responseString,UsersUpdate.class);
		System.out.println(usersobj.getName());
		System.out.println(usersobj.getJob());
		Assert.assertEquals(usersobj.getName(), users.getName());

		
	}

}
