package controllers.base;
import models.base.ResultModel;

import org.codehaus.jackson.JsonNode;

import com.rg.comm.util.BaseUtil;

import play.Logger;
import play.libs.Json;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Http.Request;
import play.mvc.Result;

public class BaseAction extends Action.Simple {
	public static String 	token;
	public static String 	version;
	public static String 	app;
	public static String 	name;
	public static JsonNode 	params;
	

	public Result call(Http.Context ctx) throws Throwable {
		Logger.info("Calling action for " + ctx);
		
		Request request = ctx.request();
		
		Logger.info("request " + request);
		Logger.info("request.body() " + request.body());
		
		JsonNode params = request.body().asJson();
		String name=request.path();
		if(!BaseUtil.isSpace(name)){
			BaseActionNoToken.name=name.substring(1, name.length());
		}
		
		Logger.info("params " + params);
		
		if (null != params) {
			String token = params.findPath("token").getTextValue();
			String version = params.findPath("version").getTextValue();
			String app=params.findPath("app").getTextValue();
			BaseAction.params=params;
			BaseAction.app=app;
			BaseAction.token=token;
			BaseAction.version=version;
		}else{
			return needParamError(BaseAction.name);
		}
		return delegate.call(ctx);
	}
	
	public Result needParamError(String name){
		ResultModel resultModel=new ResultModel(-100,name,Json.newObject(),Json.newObject());
		return ok(Json.toJson(resultModel));
	}
	public static String getToken() {
		return token;
	}
	public static void setToken(String token) {
		BaseAction.token = token;
	}
	public static String getVersion() {
		return version;
	}
	public static void setVersion(String version) {
		BaseAction.version = version;
	}
	public static String getApp() {
		return app;
	}
	public static void setApp(String app) {
		BaseAction.app = app;
	}
	public static JsonNode getParams() {
		return params;
	}
	public static void setParams(JsonNode params) {
		BaseAction.params = params;
	}
}
