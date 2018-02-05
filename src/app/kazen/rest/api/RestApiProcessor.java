//$Id$
package app.kazen.rest.api;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestApiProcessor {
	private static final Logger LOGGER = Logger.getLogger(RestApiProcessor.class.getName());

	private static final String PROCESS_NAME = "processRequest"; // NO i18N

	public static Object processRequest(RestApiContext context) throws Exception {

		String handler = RestApiMapper.getHandler(context.getPath(), context.getRequestMethod());

		try {
			Class<?> handlerCls = Class.forName(handler);
			Object clsObj = handlerCls.newInstance();

			Object obj;
			Method method = handlerCls.getMethod(PROCESS_NAME, context.getClass());
			obj = method.invoke(clsObj, context);
			return obj;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Unable to create Instance Object for handler " + handler, e);
			return e;
		}

	}

}
