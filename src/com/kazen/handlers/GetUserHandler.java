//$Id$
package com.kazen.handlers;

import com.kazen.rest.api.RestAPIContext;
import java.io.PrintWriter;

public class GetUserHandler extends APIHandler {

	protected Object handleGet(RestAPIContext context) throws Exception {
		String data = "<h1>In userhandler:::</h1>" + context.getRequestMethod();
		return data;
	}

}
