package by.epam.totalizator.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.service.UserService;
import by.epam.totalizator.service.exception.ServiceException;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page;
		try {
			User user = UserService.checkLogin(request.getParameter(RequestParameterName.LOGIN).trim(), request.getParameter(RequestParameterName.PASSWORD).trim());
			if (user != null) {
				request.getSession().setAttribute(RequestParameterName.USER, user);
				
				if (user.getStatus().equals(RequestParameterName.USER)) {
					page = PageName.USER_PAGE;
				} else {
					page = PageName.ADMIN_PAGE;
				}
			} else {
				page = PageName.INDEX_PAGE;
				request.setAttribute(RequestParameterName.ACCESS_DENIED, true);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		try {
			request.getRequestDispatcher(page).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException("Could not forward to the page", e);
		}
	}

}
