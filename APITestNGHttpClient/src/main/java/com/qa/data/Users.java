package com.qa.data;
//JSON Payload Definition class
public class Users {
	
	String name;
	String job;
    String id;
    String Additionalinfo;
    
    //Default Constructor
	public Users()
	{
		
		
	}
	public Users(String name, String job)
	{
		this.name=name;
		this.job=job;		
	}
	//Getters and Setters Methods
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdditionalinfo() {
		return Additionalinfo;
	}
	public void setAdditionalinfo(String additionalinfo) {
		Additionalinfo = additionalinfo;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
}
