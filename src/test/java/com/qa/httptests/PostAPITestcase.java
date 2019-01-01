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
import com.qa.data.Users;

public class PostAPITestcase {
	
	String url = "https://reqres.in";
	String apiUrl;
	RestClient restclient;
	
	@BeforeMethod
	public void setup() {
		apiUrl = url+"/api/users";
	}
	
	@Test
	public void postAPItest() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		
		//hashmap preparation for header
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//prepare json pay load using jackson api - core and data-bind  APIs	
		//ObjectMapper provides functionality for reading and writing JSON,either to and from basic POJOs (Plain Old Java Objects),
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("Test", "Manager");
		
		//convert java object to JSON string - Serialization or Marshelling
		String userJsonString = mapper.writeValueAsString(users);
		System.out.println(userJsonString);
		
		CloseableHttpResponse response = restclient.post(apiUrl, userJsonString, headerMap);
		
		//get the status code
		int statuscode = response.getStatusLine().getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 201);
		
		//get the json payload in the response
		HttpEntity httpentity = response.getEntity();
		String responseString = EntityUtils.toString(httpentity);
		System.out.println(responseString);
		
		//Convert a normal string to json string using JSONObject class
		JSONObject jsonresponse = new JSONObject(responseString);
		System.out.println(jsonresponse);
		
		//Convert json string to java object - Deserialization or unmarshelling
		Users usersobj = mapper.readValue(responseString,Users.class);
		System.out.println(usersobj.getName());
		System.out.println(usersobj.getJob());
		String responseID = usersobj.getId();
		if (responseID.isEmpty()){
			Assert.assertEquals(true, false, "response ID is blank, POST call failed");
		}
		

		
	}

}
