//$Id$
package com.kazen.handlers;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.kazen.rest.api.RestAPIContext;

public abstract class APIHandler {
	private static final Logger LOGGER = Logger.getLogger(APIHandler.class.getName());

	protected Object handleGet(RestAPIContext context) throws Exception {
		throw getUnhandledException(context);
	}

	protected Object handlePut(RestAPIContext context) throws Exception {
		throw getUnhandledException(context);
	}

	protected Object handlePost(RestAPIContext context) throws Exception {
		throw getUnhandledException(context);
	}

	protected Object handleDelete(RestAPIContext context) throws Exception {
		throw getUnhandledException(context);
	}

	private Exception getUnhandledException(RestAPIContext context) {
		LOGGER.log(Level.WARNING, "unhandled request received");
		return new Exception("Unhandled........");
	}

	public Object processRequest(RestAPIContext context) throws Exception {
		String method = context.getRequestMethod();

		switch (method) {
			case "POST" :
				return handlePost(context);
			case "GET" :
				return  handleGet(context);
			case "PUT" :
				return 	handlePut(context);
			case "DELETE" :
				return handleDelete(context);
		}
		return null;
	}

}
