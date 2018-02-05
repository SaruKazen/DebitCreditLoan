//$Id$
package app.kazen.rest.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RestApiMapper {

	private static Map<String, Map<String, String>> restUrlMap = Collections.emptyMap();

	static {
		restUrlMap = new HashMap<String, Map<String, String>>();
		
		Map<String,String> handlers = new HashMap<String, String>();
		handlers.put("GET", "app.kazen.handlers.getUserHandler");
		handlers.put("POST", "app.kazen.handlers.addUserHandler");
		restUrlMap.put("/api/users", handlers);
		
		
	}

	public static String getHandler(String path, String method) throws Exception {
		String handler = restUrlMap.get(path).get(method);
		return handler;

	}
}
