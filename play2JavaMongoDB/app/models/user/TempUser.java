package models.user;

import models.BaseModel;
import models.SeqenceID;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.ruoogle.mongodb.util.DBCollections;
import com.ruoogle.mongodb.util.MongoDBManager;

/**
 * 临时用户，是指没有补充信息或者绑定第三方帐号的用户
 * @author zhy
 */
public class TempUser extends BaseModel{
	private int 	id;				//临时用户ID
	private String  deviceid;  //设备code

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	private TempUser() {
		super();
	}
	private TempUser(int id, String deviceid) {
		super();
		this.id = id;
		this.deviceid = deviceid;
	}
	
	/**
	 * 保存临时用户
	 * @param tempUser
	 * @return 用户ID
	 */
	public static int insert(TempUser tempUser){
		BasicDBObject doc= new BasicDBObject();
		int id=SeqenceID.getAutoIncIDInt(DBCollections.TempUser);
		doc.put("id", id);
		doc.put("deviceid", tempUser.getDeviceid());
		MongoDBManager.insert(DBCollections.TempUser, doc);
		return id;
	}
	
	/**
	 * 根据设备号获取用户对象
	 * @param deviceid
	 * @return
	 */
	public static TempUser findByDeviceID(String deviceid){
		BasicDBObject query=new BasicDBObject();
		query.put("deviceid", deviceid);
		DBObject dbObject=MongoDBManager.findOne(DBCollections.TempUser,query);
		TempUser tempUser = null;
		Gson gson = new Gson();
		if(null!=dbObject){
			tempUser = gson.fromJson(dbObject.toString(), TempUser.class);
		}
		return tempUser;
	}
}
