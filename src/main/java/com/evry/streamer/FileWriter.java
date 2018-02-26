package com.evry.streamer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileWriter {
	//private MongoDatabase db;
	//private MongoClient mongoClient;
	//private GridFSBucket gridFSBucket;
	private static FileWriter instance;
	
	private FileWriter(String hostName, String databaseName, String fileDBName) {
		//mongoClient = new MongoClient(hostName);
		
		//db = mongoClient.getDatabase(databaseName);
		//gridFSBucket = GridFSBuckets.create(db, fileDBName);
	}
	
	public static FileWriter getInstance(String hostName, String dbName, String fileDBName) {
		if (instance == null) {
			instance = new FileWriter(hostName, dbName, fileDBName);
		}
		return instance;
	}
	
	
	public String writeData(File file) {
		try {
			System.out.println("File received for write data is " +  file.getName());
		    InputStream streamToUploadFrom = new FileInputStream(file);
		    // Create some custom options
		   // GridFSUploadOptions options = new GridFSUploadOptions()
		                                       // .metadata(new Document("type", "image"));
		    System.out.println("file name is " + file.getName());
		    //gridFSBucket.uploadFromStream(file.getName(), streamToUploadFrom, options);
		    streamToUploadFrom.close();
		    return file.getName();
		    
		} catch (FileNotFoundException e){
		   // handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void readData(String fileName) {
		//GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStreamByName(fileName);
		//int fileLength = (int) downloadStream.getGridFSFile().getLength();
		//byte[] bytesToWriteTo = new byte[fileLength];
		//downloadStream.read(bytesToWriteTo);
		//downloadStream.close();
		
		
		
	}
	
	public static void main(String []args) {
		FileWriter instance = FileWriter.getInstance("localhost", "vision", "visionfiles");
		File file = new File("D://images//bola.jpg");
		String fileName = instance.writeData(file);
		instance.readData(fileName);
		return;
		
	}
	
	
}
