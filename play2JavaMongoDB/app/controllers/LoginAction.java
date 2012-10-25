package controllers;

import play.mvc.Result;

public class LoginAction extends BaseControler{
	public static Result index() {
		if(null!=params){
			String encodeStr=params.findPath("encodeStr").getTextValue();
			result.put("encodeStr", encodeStr);
		}
		result.put("deviceID", deviceID);
		result.put("version", version);
		return ok(result);
	}
}
