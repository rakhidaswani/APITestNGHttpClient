package com.qa.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase

{
	TestBase testBase;
	String serviceurl;
	String apiUrl;
	String url;
	RestClient restClient;	
	CloseableHttpResponse cloHttpResponse;

    @BeforeMethod

    public void setUP() throws ClientProtocolException, IOException

    {
	   testBase = new TestBase();
	   serviceurl = prop.getProperty("URL");
	   apiUrl = prop.getProperty("Service URL");
	   
	   //https://reqres.in//api/users
	   
	   url = serviceurl + apiUrl;
	   
	   RestClient restClient = new RestClient();
	   restClient.get(url);
	   	   
    }

    
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException
	
	{
		restClient = new RestClient();
        HashMap<String, String> headermap = new HashMap<String,String>();
       	//Default Header
    	headermap.put("Content-Type","application/json");
    	
    	//Jackson API for Marshaling(conversion of Java Object to JSON Object)
    	
    	ObjectMapper mapper = new ObjectMapper();
    	//Expected user Object
    	Users user = new Users("Rakhi", "Test Coordinator"); 
    	
    	//Converting Java Object (user) to JSON 
    	
    	mapper.writeValue(new File("/C:/Users/468396/eclipse-workspace/APITestNGHttpClient/src/main/java/com/qa/data/users.json"), user);
    	
    	//Object to JSON String
    	
    	String userJson2String = mapper.writeValueAsString(user);
    	
    	System.out.println(userJson2String);
    	
    	//Hitting the API
    	cloHttpResponse = restClient.postAPI(url, userJson2String, headermap);
    	
    	
        //Validating Response from API	
    	// Validating Status code
    	
    	int statuscode = cloHttpResponse.getStatusLine().getStatusCode();
    	Assert.assertEquals(statuscode,Expec_statusresponse_201);
    	
    	//Validating JSON String
    	String responseString = EntityUtils.toString(cloHttpResponse.getEntity(),"UTF-8");
    	
    	JSONObject responseJSON = new JSONObject(responseString);
    	
    	System.out.println("Response from API:   " +responseJSON);
    	
    	//JSON to Java object
    	//Actual User Object
    	Users userResobj = mapper.readValue(responseString, Users.class);
    	System.out.println(userResobj);
    	
    	//Assertion
    	
    	Assert.assertTrue(user.getName().equals(userResobj.getName()));
    	Assert.assertTrue(user.getJob().equals(userResobj.getJob()));
    	
    	System.out.println(userResobj.getId());
    	System.out.println(userResobj.getAdditionalinfo());    	
    	
	}
}
