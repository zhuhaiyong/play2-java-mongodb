package controllers;

import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;

public class BaseControler extends Controller{
	public static String deviceID;
	public static String version;
	public static String app;
	public static JsonNode params;
	static{
		 params = request().body().asJson();
		 if(null!=params){
			 deviceID = params.findPath("deviceID").getTextValue();
		     version = params.findPath("version").getTextValue();
		     app=params.findPath("app").getTextValue();
		 }else{
			//Bad request 
		 }
	}
	
}
