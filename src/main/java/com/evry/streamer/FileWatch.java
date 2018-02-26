package com.evry.streamer;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;

import com.evry.device.Util;

public class FileWatch implements Runnable {
	private String path;
	private Path myDir;


	public FileWatch(String imagePath) {
		path = imagePath;
		myDir = Paths.get(path);

	}

	@Override
	public void run() {
		WatchService watcher;
		try {
			watcher = myDir.getFileSystem().newWatchService();
			myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
			WatchKey watckKey = watcher.take();
			//To-Do : Change it to read from database
			FileWriter instance = FileWriter.getInstance(Util.getMongourl(), Util.getDbName(), Util.getFileDBName());
			while(true) {
				List<WatchEvent<?>> events = watckKey.pollEvents();
				for (WatchEvent event : events) {
					if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
						File file = new File("D:\\images\\" +event.context().toString());
							System.out.println("Created: " + event.context().toString());
							if (file !=  null) {
								System.out.println("Sending files to mongo for writing");
								System.out.println("Returned from  mongoDB" + FileWriter.getInstance(Util.getMongourl(), Util.getDbName(), Util.getFileDBName())
								.writeData(file));
								sendPayload(file.getName());
							
									
							} 
						
					
					}
						
						//file.delete();
						
					}
	
				}
			

		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}

	}

	private void sendPayload(String name) {
		String[] fields = name.split("_");
		int  i=0;
		JSONObject payload = new JSONObject();
		try {
			String time = fields[i++];
			String id = fields[i++].replace(".", "");
			String fileName = name;
			//payload.put("id", id);
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			payload.put("time", Long.toString(ts.getTime()));
			payload.put("fileName", fileName);
			payload.put("id", id);
			System.out.println("Payload to output + " +payload.toString());
			//payload.append("time", fields[i++]);
			//payload.append("id", fields[i++].replace(".", ""));
			//payload.append("fileName", name);
			//StreamIoT.getInstnace().sendData(payload);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}

	/*private void writeFileToDB(String fileName) {
		// TODO Auto-generated method stub
		// Split the file name in to different parts
		// write detaiils of url, name, date time
		// write file to mongodb
		// send mqtt message to iOT consumer with the details(url, name,
		// dateTime, FileLocation)
		String dbFileName="vision";
		MongoDatabase db = getMongoDB();
		File imageFile = new File(fileName);
	    GridFS gfsPhoto = new GridFS(db, "photo");
	    GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
	    gfsFile.setFilename(dbFileName);
	    gfsFile.save();

	}
	
	private MongoDatabase getMongoDB() {
		MongoClient mongo = new MongoClient(DB_IP , DB_PORT);
		return mongo.getDatabase(DB_NAME);
		
	}
*/

	public static void main(String []args) {
		FileWatch watch =  new FileWatch("D:\\images");
		Thread fileWatcher =  new Thread(watch);
		
		fileWatcher.start();
		
		try {
			fileWatcher.join();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
