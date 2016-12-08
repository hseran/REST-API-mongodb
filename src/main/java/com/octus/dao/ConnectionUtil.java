/**
 * 
 */
package com.octus.dao;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * @author naresh
 *
 */
public class ConnectionUtil {

	public static DB getDBConnection() throws UnknownHostException{
		
		String host = System.getProperty("DBHOST");
		String db = System.getProperty("DBNAME");
		String user = System.getProperty("DBUSER");
		String port = System.getProperty("DBPORT");
		String password = System.getProperty("PASSWORD");
		password = password == null? "" : password;
		user = user == null? "" : user;
		MongoClient mongoClient = null;
		
		if (user.isEmpty() || password.isEmpty()){
			mongoClient = new MongoClient(new ServerAddress(host + ":" +port));
		}
		else{
			MongoCredential credential = MongoCredential.createCredential(user, db, password.toCharArray());
			mongoClient = new MongoClient(new ServerAddress(host + ":" +port), Arrays.asList(credential));
		}
		
        final DB octusDB = mongoClient.getDB(db);
        return octusDB;
	}
}
