package com.evry.process;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.environment.EnvironmentUtils;

//To-Do - Handle exception during the error cases

public class CommandExecutor implements ICommand {
	
	private String url;
	private Executor exec;
	private DefaultExecuteResultHandler resultHandler;
	private ExecuteWatchdog watchDog;
	private int timeout;
	private String name;
	
	
		public CommandExecutor(String serverUrl, int tOut, String cameraName) {
		resultHandler = new DefaultExecuteResultHandler();
		watchDog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
		Executor executor = new DefaultExecutor();
		executor.setExitValue(1);
		//executor.setWatchdog(watchDog);
		//url = "\""+serverUrl+"\"";
		url = serverUrl;
		System.out.println("Printing server url" +url);
		timeout = tOut;
		exec = new DefaultExecutor();
		name = cameraName;
	}
	
	@Override
	public boolean createProcess() {
		// TODO Auto-generated method stub
		
		try {
			exec.execute(getCommandLine(url), EnvironmentUtils.getProcEnvironment(), resultHandler);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void stopProcess() {
		if(watchDog == null) {
			System.out.println("Watch dog null, cannot stop process");
			return;
		}
		exec.getWatchdog().destroyProcess();
		
	}

	@Override
	public void exitProcess() {
		if(watchDog == null) {
			System.out.println("Watch dog null, cannot stop process");
			return;
		}
		watchDog.destroyProcess();
	}
	
	public void waitForProcess() {
		try {
			resultHandler.waitFor();
			System.out.println("Process exited with exit value : " + resultHandler.getExitValue());
			CommandManager manager = CommandManager.getCommandManager();
			manager.removeIPCamera(url);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public CommandLine getCommandLine(String url) {
		String ip = "192.168.1.210";//fetchIP(url);
		System.out.println(url);
		String dateTime = getDateTime();
		//CommandLine cmdLine = CommandLine.parse("ffmpeg -i " +  url + " " + "-f image2 -r " + Integer.toString(timeout) + " D:\\images\\" + ip +"_" +name +"_"+ "img%01d.jpg");
		//String line = "ffmpeg -i " +  "\""+url+"\"" + " " + "-f image2 -r 1"+ " -stimeout 12000000 " + "-timeout 12000000" + " D:\\images\\" +getDateTime()+"_" +ip +"_" + "img%01d.jpg";
		String line = "ffmpeg -i " +  "\""+url+"\"" + " " + "-vf fps=10/60"+  " D:\\images\\" +getDateTime()+"_" +ip +"_" + "img%01d.jpg";
		//CommandLine cmdLine = CommandLine.parse("ffmpeg -i " +  "\""+url+"\"" + " " + "-f image2 -r 1"+ " -stimeout 12000000 " + "-timeout 12000000" + " D:\\images\\" +ip +"_" + "img%01d.jpg");
		CommandLine cmdLine = CommandLine.parse(line);
		//CommandLine cmdLine = CommandLine.parse("ffmpeg -i " +  url + " " + "-f image2 -r 1"+ " -stimeout 12000000 " + "-timeout 12000000" + " D:\\images\\" +getDateTime()+"_" +ip +"_" + "img%01d.jpg");
		System.out.println("Command line is " + cmdLine.toString());
		return cmdLine;
	}

	public String fetchIP(String rtspURL) {
		rtspURL = rtspURL.trim();
		int startIndex = rtspURL.indexOf("@");
		if(startIndex == -1) {
			startIndex = rtspURL.indexOf("rtsp://");
			startIndex = startIndex+7;
			System.out.println(startIndex);
		} else {
			startIndex = startIndex + 1;
		}
		
		String rtspSubString = rtspURL.substring(startIndex, rtspURL.length());
		if(rtspSubString.contains(":")) {
			return rtspSubString.substring(0, rtspSubString.indexOf(":"));
		}
		return rtspSubString;
	}
	
	private String getDateTime() {
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
		String dateTime = sdf.format(date);
		//dateTime = dateTime.trim();
		dateTime = dateTime.replace(" ", "");
		dateTime = dateTime.replace("/", "");
		dateTime = dateTime.replace(":", "");
		return dateTime;
	}
	
	
	public static void main(String []args) {
		CommandExecutor exec = new CommandExecutor("\"rtsp://admin:admin@10.10.201.208:554/cam/realmonitor?channel=1&subtype=0\"", 60, "sample");
		//System.out.println(exec.getDateTime());
		exec.createProcess();
		exec.waitForProcess();
		
	}

	
	

}
