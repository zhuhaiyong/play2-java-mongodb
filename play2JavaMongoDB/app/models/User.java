package models;

import models.base.BaseModel;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.rg.comm.util.MD5Util;
import com.ruoogle.comm.enums.UserStatus;
import com.ruoogle.comm.util.UniqueIDUtil;
import com.ruoogle.mongodb.util.DBCollections;
import com.ruoogle.mongodb.util.MongoDBManager;

/**
 * 临时用户，是指没有补充信息或者绑定第三方帐号的用户
 * @author zhy
 */
public class User extends BaseModel{
	private long     userid;
	private String   deviceid;  		//用户最近一次登录的设备的ID
	private int  	 status;			//用户状态
	private String 	 token;
	private UserInfo info;				//用户信息
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public UserInfo getInfo() {
		return info;
	}
	public void setInfo(UserInfo info) {
		this.info = info;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User() {
		super();
	}
	public User(long userid, String deviceid, int status,String token, UserInfo info) {
		super();
		this.userid = userid;
		this.deviceid = deviceid;
		this.status = status;
		this.info = info;
		this.token = token;
	}
	/**
	 * 保存用户
	 * @param tempUser
	 * @return 用户ID
	 */
	public static long insert(User user){
		BasicDBObject doc= new BasicDBObject();
		
		long userid=UniqueIDUtil.getUniqueID();
		doc.put("userid", userid);
		doc.put("deviceid", user.getDeviceid());
		doc.put("status", user.getStatus());
		doc.put("info", user.getInfo());
		MongoDBManager.insert(DBCollections.User, doc);
		return userid;
	}
	
	/**
	 * 更新用户
	 * @param tempUser
	 * @return 用户ID
	 */
	public static void update(User user){
		BasicDBObject query=new BasicDBObject();
		query.put("userid", user.getUserid());
		
		BasicDBObject update= new BasicDBObject();
		update.put("userid", user.getUserid());
		update.put("deviceid", user.getDeviceid());
		update.put("status", user.getStatus());
		update.put("info", user.getInfo());
		MongoDBManager.update(DBCollections.Device, query, update, false, false);
	}
	
	/**
	 * 根据条件查找用户
	 * @param query
	 * @return
	 */
	private static User find(BasicDBObject query){
		DBObject dbObject=MongoDBManager.findOne(DBCollections.User,query);
		User user = null;
		Gson gson = new Gson();
		if(null!=dbObject){
			user = gson.fromJson(dbObject.toString(), User.class);
		}
		return user;
	}
	
	/**
	 * 根据设备号获取用户对象
	 * @param deviceid
	 * @return
	 */
	public static User findByDeviceID(String deviceid){
		BasicDBObject query=new BasicDBObject();
		query.put("deviceid", deviceid);
		return find(query);
	}
	
	/**
	 * 根据设备号和用户状态获取用户对象
	 * @param deviceid
	 * @return
	 */
	public static User findByDeviceIDAndStatus(String deviceid,UserStatus userStatus){
		BasicDBObject query=new BasicDBObject();
		query.put("deviceid", deviceid);
		query.put("status", userStatus.getStatus());
		return find(query);
	}
	
	/**
	 * 根据用户ID获取用户对象
	 * @param deviceid
	 * @return
	 */
	public static User findByID(long userid){
		BasicDBObject query=new BasicDBObject();
		query.put("userid", userid);
		return find(query);
	}
	
	/**
	 * 根据用户ID获取用户对象
	 * @param deviceid
	 * @return
	 */
	public static User findByToken(String token){
		BasicDBObject query=new BasicDBObject();
		query.put("token", token);
		return find(query);
	}
	
	public static String getNewToken(){
		return MD5Util.encode(UniqueIDUtil.getUniqueID()+"");
	}
}
