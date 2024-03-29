package models.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.rg.comm.util.DoNumberUtil;
import com.ruoogle.mongodb.util.DBCollections;
import com.ruoogle.mongodb.util.MongoDBManager;

/**
 * 记录数据表中 表对应的自动增长的ID
 * @author zhy
 */
public class ModelsID extends BaseModel{
	/*private String  coll_name;			//表名
	private long 	auto_increment;		//自动增长ID
	
	public long getAuto_increment() {
		return auto_increment;
	}
	public void setAuto_increment(long auto_increment) {
		this.auto_increment = auto_increment;
	}
	public String getColl_name() {
		return coll_name;
	}
	public void setColl_name(String coll_name) {
		this.coll_name = coll_name;
	}
	
	public static int getAutoIncIDInt(DBCollections collectios){
		return DoNumberUtil.intNullDowith(getAutoIncID(collectios));
	}
	
	public static long getAutoIncID(DBCollections collectios){
		String tb_name=collectios.getColl_name();
		long id=1l;
		
		ModelsID seqenceID=ModelsID.findByCollectionName(collectios);
		if(null==seqenceID){
			BasicDBObject doc=new BasicDBObject();
			doc.put("auto_increment", 1);
			doc.put("coll_name", collectios.getColl_name());
			MongoDBManager.insert(DBCollections.SeqenceID, doc);
		}else{
			DBObject query = new BasicDBObject();
	        query.put("coll_name", tb_name);
	        
			DBObject update =new BasicDBObject();
			update.put("$inc", new BasicDBObject().append("auto_increment", 1));
		    DBObject dbObject = MongoDBManager.findAndModify(DBCollections.SeqenceID,query, update);
		    if(null!=dbObject){
		    	Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		    	ModelsID oldSeqenceID = gson.fromJson(dbObject.toString(), ModelsID.class);
		    	id=oldSeqenceID.getAuto_increment();
		    }
		}
		return id;
	}
	
	
	public static ModelsID findByCollectionName(DBCollections collectios){
		BasicDBObject query=new BasicDBObject();
		query.put("coll_name", collectios.getColl_name());
		DBObject dbObject=MongoDBManager.findOne(DBCollections.SeqenceID,query);
		ModelsID seqenceID = null;
		if(null!=dbObject){
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			seqenceID = gson.fromJson(dbObject.toString(), ModelsID.class);
		}
		return seqenceID;
	}*/
}
