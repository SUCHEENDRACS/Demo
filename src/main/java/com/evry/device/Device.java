package com.evry.device;


import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class Device {
	@JsonPropertyDescription
    protected String url;
	
	@JsonPropertyDescription
	private String name;
	
	@JsonPropertyDescription
	int timeout;
	
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public Device() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Device(String ipCameraURL) {
		url = ipCameraURL;
	}
	
	public void print() {
		System.out.print("url : " + url);
		System.out.print("Name : " + name);
		
	}
	
	

}
