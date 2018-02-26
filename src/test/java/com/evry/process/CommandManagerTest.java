package com.evry.process;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

public class CommandManagerTest {

	@Test
	public void createCommandManagerTest() {
		CommandManager manager = CommandManager.getCommandManager();
		assertNotNull(manager);
	}
	
	@Test
	public void addIPCameraTest() {
		String url = "rtsp://127.0.0.1:554/live.sdp";
		CommandManager manager = CommandManager.getCommandManager();
		assertNotNull(manager);
		manager.addIPCamera(url, 1);
		CommandExecutor exec;
		exec = manager.getIPCamera(url);
		assertNotNull(exec);
		manager.removeIPCamera(url); 
	}
	
	

}
