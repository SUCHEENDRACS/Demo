package com.evry.device;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Util {
	private static String filePath = "D:\\images";
	public static String getFilePath() {
		return filePath;
	}
	public static String getMongourl() {
		return mongourl;
	}
	public static String getDbName() {
		return dbName;
	}
	public static String getFileDBName() {
		return fileDBName;
	}
	public static String getMqttURL() {
		return mqttURL;
	}
	public static String getQueueName() {
		return queueName;
	}
	private static String mongourl = "localhost";
	private static String dbName = "vision";
	private static String fileDBName = "visionfiles";
	private static String mqttURL = "172.18.0.26";
	private static String queueName = "iotpub";
	private static String brokerUserName = "iotadmin";
	public static String getBrokerUserName() {
		return brokerUserName;
	}
	
	private static String brokerPassword = "iotadmin";
	public static String getBrokerPassword() {
		return brokerPassword;
	}
/*	private static HashMap<String, String> configParams = new HashMap<String, String>();
	
	public static String getConfigParam(String key) {
		return configParams.get(key);
	}
	public static void addConfigParam(String key, String value){
		configParams.put(key, value);
	}
	
	public  static void initializePropertyFile() {
		try {

			String propertiesFileName = "media-streamer.xml";
			if (System.getProperty("MSPropertyFileName") != null) {
				propertiesFileName = System.getProperty("MSPropertyFileName");
			}

			if (System.getProperty("catalina.home") == null) {
				System.out.println("Failed to read property -> catalina.home");
				return;
			}

			String path = "file:///" + System.getProperty("catalina.home") + "/conf/" + propertiesFileName;
			System.out.println("Reading configuration file from Path -> " + path);

			try {
				URL url = new URL(path);
				if (new File(url.toURI()).exists()) {
					DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
					Document doc = dBuilder.parse(url.openStream());
					if (doc.hasChildNodes()) {
						insertConfigParams(doc.getChildNodes());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void insertConfigParams(NodeList nodeList) {
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				if (tempNode.getChildNodes().getLength() == 1) {
					Util.addConfigParam(tempNode.getNodeName(), tempNode.getTextContent().trim());
				}
				if (tempNode.hasChildNodes()) {
					insertConfigParams(tempNode.getChildNodes());
				}
			}
		}
	}*/
}