package controllers;

import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;
import play.mvc.Result;
import views.html.index;

public class LoginAction extends BaseControler{
	public static Result index(String deviceCode,String encodeStr) {
		ObjectNode result = Json.newObject();
		
	    return ok(index.render("Your new application is ready."));
	}
}
