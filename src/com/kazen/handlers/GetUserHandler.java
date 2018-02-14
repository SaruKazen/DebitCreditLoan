//$Id$
package com.kazen.handlers;

import com.kazen.rest.api.RestAPIContext;
import java.io.PrintWriter;

public class GetUserHandler extends APIHandler {

	protected void handleGet(RestAPIContext context) throws Exception {
			PrintWriter writer = context.getResponse().getWriter();
			writer.println("<h1>In userhandler:::</h1>" + context.getRequestMethod());
	}

}
