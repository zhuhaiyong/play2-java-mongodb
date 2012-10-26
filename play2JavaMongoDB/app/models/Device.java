package models;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.ruoogle.mongodb.util.DBCollections;
import com.ruoogle.mongodb.util.MongoDBManager;
import models.base.BaseModel;
/**
 * 设备表
 * @author zhy
 */
public class Device extends BaseModel{
	private long   user_id;				//设备的使用者ID
	private String deviceid;			//设备的ID
	private String pushid;				//设备的pushid
	private int device_type;			//设备类型
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getPushid() {
		return pushid;
	}
	public void setPushid(String pushid) {
		this.pushid = pushid;
	}
	public int getDevice_type() {
		return device_type;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}
	public Device() {
		super();
	}
	public Device(long user_id, String deviceid, String pushid, int device_type) {
		super();
		this.user_id = user_id;
		this.deviceid = deviceid;
		this.pushid = pushid;
		this.device_type = device_type;
	}
	
	public static BasicDBObject convertDBObject(Device device){
		BasicDBObject doc= new BasicDBObject();
		doc.put("user_id", device.getUser_id());
		doc.put("deviceid", device.getDeviceid());
		doc.put("pushid", device.getPushid());
		doc.put("device_type", device.getDevice_type());
		return doc;
	}
	
	public static void insert(Device device){
		MongoDBManager.insert(DBCollections.Device, convertDBObject(device));
	}
	
	public static void update(Device device){
		BasicDBObject query=new BasicDBObject();
		query.put("deviceid", device.getDeviceid());
		query.put("device_type", device.getDevice_type());
		
		BasicDBObject update= convertDBObject(device);
		MongoDBManager.update(DBCollections.Device, query, update, false, false);
	}
	
	public static Device find(BasicDBObject query){
		DBObject dbObject=MongoDBManager.findOne(DBCollections.Device,query);
		Device device = null;
		Gson gson = new Gson();
		if(null!=dbObject){
			device = gson.fromJson(dbObject.toString(), Device.class);
		}
		return device;
	}
	
	public static Device findOne(String deviceid,String device_type){
		BasicDBObject query=new BasicDBObject();
		query.put("deviceid", deviceid);
		query.put("device_type", device_type);
		return find(query);
	}
	
	public static int getDeviceTypeByApp(String app){
		int device_type=0;
		return device_type;
	}
}
