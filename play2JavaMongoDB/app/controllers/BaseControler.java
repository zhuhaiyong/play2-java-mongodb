package controllers;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;
import play.mvc.Controller;

public class BaseControler extends Controller{
	public static String deviceID;
	public static String version;
	public static JsonNode params;
	public static ObjectNode result=Json.newObject();
	static{
		 params = request().body().asJson();
	     deviceID = params.findPath("deviceID").getTextValue();
	     version = params.findPath("version").getTextValue();
	}
}
