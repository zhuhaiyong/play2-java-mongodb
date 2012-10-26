package controllers;
import org.codehaus.jackson.node.ObjectNode;
import com.rg.comm.util.BaseUtil;
import com.ruoogle.comm.enums.UserStatus;
import controllers.base.BaseAction;
import controllers.base.BaseActionNoToken;
import models.Device;
import models.User;
import models.base.ResultModel;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

public class TempUserLoginAction extends Controller{
	/**
	 * 临时用户登录
	 * 一个设备可以有多个帐号登录，
	 * 如果这个设备上有尚未补充信息的临时帐号，那么会返回这个临时帐号;
	 * else会新生成一个帐号
	 * @return
	 */
	@With(BaseActionNoToken.class)
	public static  Result index() {
		long userid=0;
		
		Logger.info("params " + BaseAction.getParams());
		
		String deviceid=BaseAction.getParams().findPath("deviceid").getTextValue();
		String encodestr=BaseAction.getParams().findPath("encodestr").getTextValue();
		
		String token=User.getNewToken();
		if(!BaseUtil.isSpace(deviceid)){
			//查看这个设备上有没有尚未补充信息的临时帐号
			User user=User.findByDeviceIDAndStatus(deviceid, UserStatus.Temp_User);
			if(null==user){
				User tempUser=new User(0,deviceid,0,token,null);
				userid=User.insert(tempUser);
				
				//查看这个设备是不是已经注册过了
				Device device=Device.findOne(deviceid, BaseAction.getApp());
				if(null!=device){//这个设备注册过
					device.setUser_id(userid);//更新这个设备的使用者是当前用户
					Device.update(device);
				}else{
					Device.insert(new Device(userid,deviceid,"",Device.getDeviceTypeByApp(BaseAction.getApp())));
				}
			}else{
				userid=user.getUserid();
				user.setToken(token);
				User.update(user);
			}
		}
		
		ObjectNode json=Json.newObject();
		json.put("userid", userid);
		json.put("token", token);
		ResultModel resultModel=new ResultModel(100,BaseAction.name,json,Json.newObject());
		return ok(Json.toJson(resultModel));
	}
}
