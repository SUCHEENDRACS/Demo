package com.evry.process;

import java.util.concurrent.ConcurrentHashMap;

import com.evry.device.Util;
import com.evry.streamer.FileWatch;

public class CommandManager {
	private static CommandManager instance;
	private ConcurrentHashMap<String, CommandExecutor> processList;
	private Thread watcher;
	
	
	private CommandManager() {
		processList = new ConcurrentHashMap<>();
		watcher = new Thread(new FileWatch(Util.getFilePath()));
		watcher.start();
		
		
	}
	
	public int addIPCamera(String url, int timeout, String name) {
		if (processList.get(url) != null) {
			System.out.println("The process already has been added, please verify " + url);
			return -1;
		}
		CommandExecutor executor = new CommandExecutor(url, timeout, name);
		if (executor.createProcess()) {
			System.out.println("Create process is successfull");
			processList.put(url, executor);
			return 0;
		}
		return -1;
	}
	
	public CommandExecutor getIPCamera(String url) {
		return processList.get(url);
	}
	
	public int removeIPCamera(String url) {
		CommandExecutor exec = processList.get(url);
		if (exec != null) {
			exec.stopProcess();
			processList.remove(url);
			return 0;
		}
		return -1;
	}
	
	public  int restartCapturing(String url) {
		CommandExecutor exec = processList.get(url);
		if (exec != null) {
			exec.stopProcess();
			processList.remove(url);
		}
		if (exec.createProcess() == true) {
			processList.put(url, exec);
		}
		return 0;
	}
	
	public static CommandManager getCommandManager() {
		if (instance == null) {
			instance = new CommandManager();
		}
		return instance;
		
	}
	

}
