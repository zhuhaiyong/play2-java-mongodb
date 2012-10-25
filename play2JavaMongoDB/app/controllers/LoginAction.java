package controllers;

import org.codehaus.jackson.node.ObjectNode;
import models.ResultModel;
import models.user.TempUser;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

public class LoginAction extends BaseControler{
	public static Result index() {
		if(null!=params){
			String encodeStr=params.findPath("encodeStr").getTextValue();
		}
		
		int userid=0;
		
		Logger.error(deviceID);
		
		TempUser tempUser=TempUser.findByDeviceID(deviceID);
		if(null!=tempUser){
			userid=tempUser.getId();
		}else{
			tempUser=new TempUser(0,deviceID);
			userid=TempUser.insert(tempUser);
		}
		
		ObjectNode json=Json.newObject();
		json.put("id", userid);
		
		ResultModel resultModel=new ResultModel(100,"login",json,Json.newObject());
		return ok(Json.toJson(resultModel));
	}
}
