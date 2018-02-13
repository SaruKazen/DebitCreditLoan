//$Id$
package app.kazen.handlers;

import java.util.logging.Level;
import java.util.logging.Logger;

import app.kazen.rest.api.RestAPIContext;

public abstract class APIHandler {
    private static final Logger LOGGER = Logger.getLogger(APIHandler.class.getName());
    
    
	protected void handleGet(RestAPIContext context) throws Exception {
        throw getUnhandledException(context);
	}

	protected void handlePut(RestAPIContext context) throws Exception {
        throw getUnhandledException(context);
	}

	protected void handlePost(RestAPIContext context) throws Exception{
        throw getUnhandledException(context);
	}

	protected void handleDelete(RestAPIContext context) throws Exception{
        throw getUnhandledException(context);
	}

	private Exception getUnhandledException(RestAPIContext context) {
		LOGGER.log(Level.WARNING,"unhandled request received");
		return new Exception("Unhandled........");
	}

	public void processRequest(RestAPIContext context) throws Exception {
		String method = context.getRequestMethod();

		switch(method) {
		case "POST" :
			handlePost(context);
			break;
		case "GET" :
			handleGet(context);
			break;
		case "PUT" :
			handlePut(context);
			break;
		case "DELETE" :
			handleDelete(context);
			break;
		}
	}
	
}
