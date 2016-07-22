package by.epam.totalizator.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class ControllerUtil {
	
	public static StringBuffer getCurrentCommandUrl(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		url.append("?");
		Enumeration<String> parameterName = request.getParameterNames();
		while (parameterName.hasMoreElements()) {
			String parameter = parameterName.nextElement();
			url.append(parameter);
			url.append("=");
			url.append(request.getParameter(parameter));
			url.append("&");
		}
		url.substring(0, url.length()-1);
		return url;
	}
}
