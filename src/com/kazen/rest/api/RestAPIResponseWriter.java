package com.kazen.rest.api;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestAPIResponseWriter{

private static final Logger LOGGER = Logger.getLogger(RestAPIResponseWriter.class.getName());

	public static void writeResponse(RestAPIContext context, Object responseObj) throws Exception {
        if(responseObj instanceof Exception) {
            writeExceptionResponse((Exception)responseObj, context);
        } else {
        	/**
    		 * Handlers that has called getOutStream return null.
    		 * getOutputStream and getWriter are mutually exclusive.
    		 */
        	if(responseObj != null) {
		        PrintWriter writer = context.getResponseWriter();
		        writer.print(responseObj); //NO OUTPUTENCODING
        	}
        }
	}
	/**
     * This function writes the correct message to the Client
     * @param ex
     * @return Object .The response Object with the reason and the error Code for the failure of the request.
     */
    private static void writeExceptionResponse(Exception exception, RestAPIContext context) {
        try {
            PrintWriter writer = context.getResponseWriter();
            int statusCode = 500;
            context.setHttpResponseCode(statusCode);
        } catch(Exception e) {
            LOGGER.log(Level.WARNING, "", e);
        }
    }
}