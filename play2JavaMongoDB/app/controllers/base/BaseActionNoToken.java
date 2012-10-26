package controllers.base;
import org.codehaus.jackson.JsonNode;

import com.rg.comm.util.BaseUtil;

import play.mvc.Http;
import play.mvc.Http.Request;
import play.mvc.Result;

public class BaseActionNoToken extends BaseAction{
	public Result call(Http.Context ctx) throws Throwable {
		Request request = ctx.request();
		
		String name=request.path();
		if(!BaseUtil.isSpace(name)){
			BaseActionNoToken.name=name.substring(1, name.length());
		}
		
		JsonNode params = request.body().asJson();
		if (null != params) {
			String version = params.findPath("version").getTextValue();
			String app=params.findPath("app").getTextValue();
			BaseActionNoToken.params=params;
			BaseActionNoToken.app=app;
			BaseActionNoToken.version=version;
		}else{
			return needParamError(request.path());
		}
		return delegate.call(ctx);
	}
}
