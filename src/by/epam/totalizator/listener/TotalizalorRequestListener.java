package by.epam.totalizator.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.controller.RequestParameterName;

public class TotalizalorRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent requestEvent) {
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) {
		HttpServletRequest request = (HttpServletRequest)requestEvent.getServletRequest();
		System.out.println("LISTENER: " + request.getRequestURL());
		
		if (request.getRequestURL().toString().matches(".*Totalizator/$")) {
			System.out.println("проверка успешна");
			if (request.getSession(false) != null) {
				request.getSession().removeAttribute(RequestParameterName.CURRENT_COMMAND);
			}
		}
		
	}

}
