package com.ruoogle.mongodb.util;

import play.Logger;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
public class MongoDBManager {
	private static Mongo m;
	private static synchronized void newInstance(){
		if (m == null){
			m = initMongo();
		}
	}  
	 
	private static Mongo initMongo() { 
		boolean local = true;
		String  localHostName = "localhost";//play.Configuration.root().getString("mongo.local.hostname");
		Integer localPort = 27017;//play.Configuration.root().getInt("mongo.local.port");

		String 	remoteHostName = "localhost";//play.Configuration.root().getString("mongo.remote.hostname");
		Integer remotePort = 27017;//play.Configuration.root().getInt("mongo.remote.port");
		
		Mongo m=null;
		if (local) {
			String hostname = localHostName;
			int port = localPort;
			try {
				m = new Mongo(hostname, port);
			} catch (Exception e) {
				Logger.error("Exception while intiating Local MongoDB", e);
			}
		} else {
			String hostname = remoteHostName;
			int port = remotePort;
			try {
				m = new Mongo(hostname, port);
			} catch (Exception e) {
				Logger.error("Exception while intiating Local MongoDB", e);
			}
		}
		return m;
	}  
	
	
	public static DB getDb() {
		newInstance();
		
		String userName = "root";//play.Configuration.root().getString("mongo.remote.username");
		String password = "root";//play.Configuration.root().getString("mongo.remote.password");
		
		String dbname = "phmaily"; //play.Configuration.root().getString("mongo.local.dbname");
		
		DB db =m.getDB(dbname);
		boolean auth = db.authenticate(userName, password.toCharArray());
		return db;
	}
	
	
	public static  DBObject findOne(DBCollections collections,DBObject query){
		DB db=getDb();
		String coll_name=collections.getColl_name();
		DBCollection coll=db.getCollection(coll_name);
		return coll.findOne(query);
	}
	
	/**
	 * insertOrUpdate
	 * @param map
	 * @param collections
	 * @param update
	 * @return
	 */
	public static  DBObject findAndModify(DBCollections collections,DBObject query,DBObject update){
		DB db=getDb();
		return db.getCollection(collections.getColl_name()).findAndModify(query,null,null,false,update,true,true);
	}
	
	/**
	 * 更新数据
	 * @param q			search query for old object to update
	 * @param o			object with which to update q
	 * @param upsert	if the database should create the element if it does not exist
	 * @param multi		if the update should be applied to all objects matching (db version 1.1.3 and above). An object will not be inserted if it does not exist in the collection and upsert=true and multi=true. See http://www.mongodb.org/display/DOCS/Atomic+Operations
	 * @param concern	the write concern
	 * @return
	 */
	public static WriteResult update(DBCollections collections,DBObject q,DBObject o,boolean upsert,boolean multi){
		DB db=getDb();
		return db.getCollection(collections.getColl_name()).update(q,o,upsert,multi);
	}
	
	/**
	 * 保存数据
	 * @param collections
	 * @param doc
	 * @return
	 */
	public static  WriteResult insert(DBCollections collections,BasicDBObject doc){
		DB db=getDb();
		DBCollection coll = db.getCollection(collections.getColl_name());
		WriteResult wr=coll.insert(doc);
		return wr;
	}
	
}
