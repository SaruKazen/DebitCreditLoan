//$Id$
package com.kazen.rest.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestAPIContext {

	private Map<String, Object> map = new HashMap<String, Object>();

	public RestAPIContext(HttpServletRequest request, HttpServletResponse response) {
		set("Request", request); // No I18N
		set("Response", response); // No I18N
		set("RequestType", request.getMethod());
	}

	public void set(String key, Object object) {
		map.put(key, object);
	}

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) map.get("Request");
	}

	public HttpServletResponse getResponse() {
		return (HttpServletResponse) map.get("Response");
	}

	public String getPath() {
		return (String) getRequest().getRequestURI();
	}
	public String getRequestMethod() {
		return (String) map.get("RequestType");
	}
	public PrintWriter getResponseWriter() throws IOException {
		return this.getResponse().getWriter();
	}
	
	public void setHttpResponseCode(int statusCode) {
	    this.getResponse().setStatus(statusCode);
	}
}
