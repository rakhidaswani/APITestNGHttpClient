package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	
	// a) Creating Get Method without header
	
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	
	{
		//Abstract Class
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		
		//Hitting the get URL
		CloseableHttpResponse cloHttpResponse = httpClient.execute(httpget);
		
		return cloHttpResponse;
		
		
	}
	
	//b) Creating Get Method with header
	
		public CloseableHttpResponse get(String url, HashMap<String, String> headermap) throws ClientProtocolException, IOException
		
		{
			//Abstract Class
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);
			
			for(Map.Entry<String, String> entry: headermap.entrySet()) 
			{
				httpget.addHeader(entry.getKey(),entry.getValue());
			
			}
			
			//Hitting the get URL
			CloseableHttpResponse cloHttpResponse = httpClient.execute(httpget);
			
			return cloHttpResponse;
						
		}
	
		//c) Post Method
		
		public CloseableHttpResponse postAPI(String url, String entityString, HashMap<String, String> headermap) throws ClientProtocolException, IOException		
		{

			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			//HTTP Post creation
			HttpPost httppost = new HttpPost();
			
			//Defining pay-load
			httppost.setEntity(new StringEntity(entityString));
			
			//for Headers
			for(Map.Entry<String, String> entry: headermap.entrySet()) 
			{
				httppost.addHeader(entry.getKey(),entry.getValue());
			
			}
			
			CloseableHttpResponse closeablehttpResponse = httpClient.execute(httppost);
			return closeablehttpResponse;
			
				
		}
}
