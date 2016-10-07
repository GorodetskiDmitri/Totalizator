package by.epam.totalizator.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.service.UserService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.impl.UserServiceImpl;

public class LoginCommand implements Command {

	private static final Logger logger = Logger.getLogger(LoginCommand.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page;
		String login = request.getParameter(RequestParameterName.LOGIN).trim();
		String password = request.getParameter(RequestParameterName.PASSWORD).trim();
		try {
			UserService service = UserServiceImpl.getInstance();
			User user = service.checkLogin(login, password);
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
			logger.error(e.getMessage());
			throw new CommandException(e.getMessage(), e);
		}
		
		try {
			request.getRequestDispatcher(page).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException("Could not forward to the page", e);
		}
	}

}
