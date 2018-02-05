//$Id$
package app.kazen.rest.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestApiContext {

	private Map map = new HashMap();
	
	public RestApiContext(HttpServletRequest request, HttpServletResponse response) {
		set("Request", request); // No I18N
		set("Response", response); // No I18N
		set("RequestType", request.getMethod());
	}
	
	public void set(String key, Object object) {
		map.put(key, object);
	}
}
