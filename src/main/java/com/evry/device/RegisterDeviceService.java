package com.evry.device;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.evry.process.CommandManager;

@Path("/registerIPCamera")
public class RegisterDeviceService {

	@POST
	@Path("/RegisterDevice")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerDevice(Device device) {
		device.print();
		CommandManager manager = CommandManager.getCommandManager();
		manager.addIPCamera(device.getUrl(), device.getTimeout(), device.getName());
		return Response.ok().build();
	}

	@POST
	@Path("/UnRegisterDevice")
	@Consumes(MediaType.APPLICATION_JSON)
	public void unregisterDevice(Device device) {
		// TODO Auto-generated method stub
		
	}

}
