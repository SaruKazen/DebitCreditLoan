//$Id$
package app.kazen.rest.api;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestAPIProcessor {
	private static final Logger LOGGER = Logger.getLogger(RestAPIProcessor.class.getName());

	private static final String PROCESS_NAME = "processRequest"; // NO i18N

	public static void processRequest(RestAPIContext context) throws Exception {

		String handler = RestAPIMapper.getHandler(context.getPath(), context.getRequestMethod());

		try {
			Class<?> handlerCls = Class.forName(handler);
			Object clsObj = handlerCls.newInstance();

			Object obj;
			Method method = handlerCls.getMethod(PROCESS_NAME, context.getClass());
			method.invoke(clsObj, context);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Unable to create Instance Object for handler " + handler, e);
			throw e;
		}

	}

}
