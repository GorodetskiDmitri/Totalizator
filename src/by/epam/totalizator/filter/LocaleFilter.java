package by.epam.totalizator.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.controller.RequestParameterName;


public class LocaleFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		if (httpRequest.getSession().getAttribute(RequestParameterName.LOCALE) == null) {
			Locale locale = Locale.getDefault();
			String localeStr = "" + locale;
			localeStr = localeStr.substring(0, 2);
			if (localeStr.equalsIgnoreCase("ru")) {
				httpRequest.getSession().setAttribute(RequestParameterName.LOCALE, "ru");
			} else {
				httpRequest.getSession().setAttribute(RequestParameterName.LOCALE, "en");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}
}
