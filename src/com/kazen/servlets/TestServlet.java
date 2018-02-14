package com.kazen.servlets;

import com.kazen.rest.api.RestAPIContext;
import com.kazen.rest.api.RestAPIProcessor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(TestServlet.class.getName());

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RestAPIContext context = new RestAPIContext(request, response);
			PrintWriter writer = context.getResponse().getWriter();
			writer.println("<h1>Hello</h1>");
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Exception in servlet", e);
			throw new ServletException("Exception in Servlet", e);
		}
	}

}
