package com.evry.process;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class CommandExecutorTest {

	@Test
	public void CommandExecutorTestForProcess() {
		CommandExecutor executor = new CommandExecutor("rtsp://admin:admin@192.168.1.210:554/cam/realmonitor?channel=1&subtype=0", 1);
		String ip = executor.fetchIP("rtsp://admin:admin@192.168.1.210:554/cam/realmonitor?channel=1&subtype=0");
		String expectedIP = "192.168.1.210";
		assertEquals("IP should be " +ip, expectedIP, ip);
	}
	
	@Test
	public void CommandExecuteTest() {
		CommandExecutor executor = new CommandExecutor("rtsp://admin:admin@192.168.1.210:554/cam/realmonitor?channel=1&subtype=0", 1);
		boolean success = executor.createProcess();
		executor.waitForProcess();
		assertEquals("the test is a success", new Boolean(true), new Boolean(success));
	}
	
	@Test
	public void CommandExecuteStopProcess() {
		CommandExecutor executor = new CommandExecutor("rtsp://127.0.0.1:554/live.sdp", 1);
		executor.createProcess();
		executor.stopProcess();
		executor.waitForProcess();
	}

}
