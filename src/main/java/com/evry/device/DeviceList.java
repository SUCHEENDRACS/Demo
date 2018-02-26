package com.evry.device;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;



public class DeviceList {
    
	@JsonPropertyDescription
    protected List<Device> device;
	
	public DeviceList(@JsonPropertyDescription("Device") Device[] devList) {
		device = Arrays.asList(devList);
	}
	
	public List<Device> getDeviceList() {
		if (device == null) {
            device = new ArrayList<Device>();
        }
        return this.device;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.device = deviceList;
	}
	
	public int getSize() {
		return device.size();
	}
	
	public void print() {
		for(int i = 0; i < device.size(); i++) {
			device.get(i).print();
		}
	}

	
	
	
	

}
