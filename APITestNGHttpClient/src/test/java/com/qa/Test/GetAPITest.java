package com.qa.Test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utillities.TestUtil;

public class GetAPITest extends TestBase
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


    @Test(priority=1)
    
    public void getAPITestwithoutHeader() throws ClientProtocolException, IOException
    {
    	restClient = new RestClient();
    	cloHttpResponse = restClient.get(url);
 	    
    	
 	       //a) Status Code
 	 		int statuscode = cloHttpResponse.getStatusLine().getStatusCode();
 	 		System.out.println("Status Code:  " +statuscode);
 	 		
 	 		Assert.assertEquals(statuscode, Expec_statusresponse_200, "Status code is not as expected that is 200");
 	 		 	 		
 	 		//b) JSON Response
 	 		String respString = EntityUtils.toString(cloHttpResponse.getEntity(), "UTF-8");
 	 		
 	 		//String conversion to JSON
 	 		JSONObject responseJSON = new JSONObject(respString);
 	 		
 	 		System.out.println("JSON Response:  " +responseJSON);
 	 		
 	 		
 	 	    //Single value Assertion
 	 		//Validating ID value from the response
 	 		String id = TestUtil.getValueByJpath(responseJSON, "/id");
 	 		System.out.println("Value of the id:  " +id);
 	 		Assert.assertEquals(Integer.parseInt(id),1907296);
 	 		
 	 	    //Validating Date value from the response
 	 		String dt = TestUtil.getValueByJpath(responseJSON, "/dt");
 	 		System.out.println("Date from the response is:  " +dt);
 	 		Assert.assertEquals(Integer.parseInt(dt),1485792967);
 	 		
 	 	   //Validating name from the response
 	 		String name = TestUtil.getValueByJpath(responseJSON, "/name");
 	 		System.out.println("Value of the id:  " +name);
 	 		Assert.assertEquals(name,"Tawarano");
 	 		
 	       //JSON Array value 
 	 		
 	 		//String weatherID = TestUtil.getValueByJpath(responseJSON, "/weather[0]/id");
 	 		//String main = TestUtil.getValueByJpath(responseJSON, "/weather[0]/main");
 	 		//String description = TestUtil.getValueByJpath(responseJSON, "weather[0]/description");
 	 		
 	 		//System.out.println((weatherID));
 	 		//System.out.println(main);
 	 		//System.out.println(description);
 	 		
 	 		//Saving the weather JSON value 	 
 	 		
 	 		JSONArray weather = responseJSON.getJSONArray("weather");
 			//Weather ID value
 	 		int weatherid = weather.getJSONObject(0).getInt("id");
 	 		System.out.println(weatherid);
 	 		
 	 		//Weather Main value
 	 	 	 		
 	 		String main = weather.getJSONObject(0).getString("main");
 	 		
 	 		System.out.println(main);
 	 		
 	 		//Weather description value
 	 		
 	 		String description = weather.getJSONObject(0).getString("description");
 	 		
 	 		System.out.println(description);
 	 				
 	 				 	 		
 	 		//c) All Headers 
 	 		Header[] headerArray = cloHttpResponse.getAllHeaders();
 	 		
 	 		HashMap<String, String> allHeaders= new HashMap<String, String>();
 	 		for(Header header: headerArray) {
 	 			allHeaders.put(header.getName(),header.getValue());
 	 			
 	 		}
 	 		
 	 		System.out.println("Headers array:  "+allHeaders);
 	 		
    }
    
    @Test(priority=2)
    
    public void getAPITestwithHeader() throws ClientProtocolException, IOException
    {
    	restClient = new RestClient();
    	
 	    
    	HashMap<String, String> headermap = new HashMap<String,String>();
    	
    	//Default Header
    	headermap.put("Content-Type","application/json");
    	
    	//Header with different inputs    	
    	//headerMap.put("UID","Test1");
    	//headerMap.put("PWD","12345");
    	//headerMap.put("Env","UAT");
    	   	
    	cloHttpResponse = restClient.get(url, headermap);
    	
 	       //a) Status Code
 	 		int statuscode = cloHttpResponse.getStatusLine().getStatusCode();
 	 		System.out.println("Status Code:  " +statuscode);
 	 		
 	 		Assert.assertEquals(statuscode, Expec_statusresponse_200, "Status code is not as expected that is 200");
 	 		 	 		
 	 		//b) JSON Response
 	 		String respString = EntityUtils.toString(cloHttpResponse.getEntity(), "UTF-8");
 	 		
 	 		//String conversion to JSON
 	 		JSONObject responseJSON = new JSONObject(respString);
 	 		
 	 		System.out.println("JSON Response:  " +responseJSON);
 	 		
 	 		
 	    	//Single value Assertion
 	 		//Validating ID value from the response
 	 		String id = TestUtil.getValueByJpath(responseJSON, "/id");
 	 		System.out.println("Value of the id:  " +id);
 	 		Assert.assertEquals(Integer.parseInt(id),1907296);
 	 		
 	 	    //Validating Date value from the response
 	 		String dt = TestUtil.getValueByJpath(responseJSON, "/dt");
 	 		System.out.println("Date from the response is:  " +dt);
 	 		Assert.assertEquals(Integer.parseInt(dt),1485792967);
 	 		
 	 	   //Validating name from the response
 	 		String name = TestUtil.getValueByJpath(responseJSON, "/name");
 	 		System.out.println("Value of the id:  " +name);
 	 		Assert.assertEquals(name,"Tawarano");
 	 		
 	       //JSON Array value 
 	 		
 	 		//String weatherID = TestUtil.getValueByJpath(responseJSON, "/weather[0]/id");
 	 		//String main = TestUtil.getValueByJpath(responseJSON, "/weather[0]/main");
 	 		//String description = TestUtil.getValueByJpath(responseJSON, "weather[0]/description");
 	 		
 	 		//System.out.println((weatherID));
 	 		//System.out.println(main);
 	 		//System.out.println(description);
 	 		
 	 		//Saving the weather JSON value 	 
 	 		
 	 		JSONArray weather = responseJSON.getJSONArray("weather");
 			//Weather ID value
 	 		int weatherid = weather.getJSONObject(0).getInt("id");
 	 		System.out.println(weatherid);
 	 		
 	 		//Weather Main value
 	 	 	 		
 	 		String main = weather.getJSONObject(0).getString("main");
 	 		
 	 		System.out.println(main);
 	 		
 	 		//Weather description value
 	 		
 	 		String description = weather.getJSONObject(0).getString("description");
 	 		
 	 		System.out.println(description);
 	 				
 	 				
 	 		
 	 		
 	 		//c) All Headers 
 	 		Header[] headerArray = cloHttpResponse.getAllHeaders();
 	 		
 	 		HashMap<String, String> allHeaders= new HashMap<String, String>();
 	 		for(Header header: headerArray) {
 	 			allHeaders.put(header.getName(),header.getValue());
 	 			
 	 		}
 	 		
 	 		System.out.println("Headers array:  "+allHeaders);
 	 		
    
    }
}
