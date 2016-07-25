package by.epam.totalizator.command.impl;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.entity.User;

public class ChangeLocaleCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String locale = request.getParameter(RequestParameterName.LOCALE);
		request.getSession().setAttribute("locale", locale);
		//Locale.setDefault(new Locale(locale, locale.toUpperCase()));
		StringBuffer currentCommand = (StringBuffer) request.getSession().getAttribute(RequestParameterName.CURRENT_COMMAND);
		
		if (currentCommand != null) {
			try {
				currentCommand.append(RequestParameterName.CHANGED_LANGUAGE);
				currentCommand.append("=true");
				response.sendRedirect(currentCommand.toString());
			} catch (IOException e) {
				throw new CommandException("Could not sent redirect", e);
			}
		} else {
			User user = (User) request.getSession().getAttribute(RequestParameterName.USER);
			String page = null;
			if (user != null) {
				String status = user.getStatus();
				if (status.equals(RequestParameterName.ADMIN)) {
					page = PageName.ADMIN_PAGE;
				}
				if (status.equals(RequestParameterName.USER)) {
					page = PageName.USER_PAGE;
				}
			} else {
				page = PageName.INDEX_PAGE;
			}
			
			try {
				request.getRequestDispatcher(page).forward(request, response);
			} catch (ServletException | IOException e) {
				throw new CommandException("Could not forward to the page");
			}
		}
	}

}
