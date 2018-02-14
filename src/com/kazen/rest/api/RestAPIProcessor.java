//$Id$
package com.kazen.rest.api;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.kazen.rest.api.RestAPIResponseWriter;
public class RestAPIProcessor {
	private static final Logger LOGGER = Logger.getLogger(RestAPIProcessor.class.getName());

	private static final String PROCESS_NAME = "processRequest"; // NO i18N

	public static void processRequest(RestAPIContext context) throws Exception {

		String handler = RestAPIMapper.getHandler(context.getPath(), context.getRequestMethod());

		try {
			Class<?> handlerCls = Class.forName(handler);
			Object clsObj = handlerCls.newInstance();

			Object responseObj;
			try {
				Method method = handlerCls.getMethod(PROCESS_NAME, context.getClass());
				responseObj = method.invoke(clsObj, context);
			} catch (Exception ex) {
				LOGGER.log(Level.SEVERE, "unable to invoke processRequest", ex);
				responseObj = ex;
			}
			RestAPIResponseWriter.writeResponse(context, responseObj);

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Unable to create Instance Object for handler " + handler, e);
			throw e;
		}

	}

}
