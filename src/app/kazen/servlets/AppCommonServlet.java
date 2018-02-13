//$Id$
package app.kazen.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.kazen.rest.api.RestAPIContext;
import app.kazen.rest.api.RestAPIProcessor;

public class AppCommonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(AppCommonServlet.class.getName());

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RestAPIContext context = new RestAPIContext(request, response);
			RestAPIProcessor.processRequest(context);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Exception in servlet", e);
			throw new ServletException("Exception in Servlet", e);
		}
	}

}
