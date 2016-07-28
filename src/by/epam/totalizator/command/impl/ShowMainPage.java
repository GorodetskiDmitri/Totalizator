package by.epam.totalizator.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.controller.ControllerUtil;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.entity.User;

public class ShowMainPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		StringBuffer url = ControllerUtil.getCurrentCommandUrl(request);
		request.getSession(true).setAttribute(RequestParameterName.CURRENT_COMMAND, url);
		
		String page = null;
		User user = (User)request.getSession().getAttribute(RequestParameterName.USER);
		if (user != null) {
			String status = user.getStatus();
			if (status.equals(RequestParameterName.USER)) {
				page = PageName.USER_PAGE;
			}
			if (status.equals(RequestParameterName.ADMIN)) {
				page = PageName.ADMIN_PAGE;
			}
		} else {
			page = PageName.INDEX_PAGE;
		}
		
		try {
			request.getRequestDispatcher(page).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException("Could not forward to the page", e);
		}
	}

}
