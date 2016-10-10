package by.epam.totalizator.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.command.exception.ExceptionMessage;
import by.epam.totalizator.controller.ControllerUtil;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.service.UserService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.impl.UserServiceImpl;


public class ShowDepositCommand implements Command{

	private static final Logger logger = Logger.getLogger(ShowDepositCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		StringBuffer url = ControllerUtil.getCurrentCommandUrl(request);
		User user = (User) request.getSession().getAttribute(RequestParameterName.USER);
		String login = user.getLogin();
		int password = user.getPassword();
		request.getSession().setAttribute(RequestParameterName.CURRENT_COMMAND, url);
		
		User currentUser;
		double unresolvedMoney = 0.0;
		try {
			UserService service = UserServiceImpl.getInstance();
			currentUser = service.getUser(login, password);
			unresolvedMoney = service.getUnresolvedMoney(user.getId());
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new CommandException(e.getMessage(), e);
		}
		
		request.getSession().removeAttribute(RequestParameterName.USER);
		request.getSession().setAttribute(RequestParameterName.USER, currentUser);
		request.getSession().setAttribute(RequestParameterName.UNRESOLVED_MONEY, unresolvedMoney);
		
		try {
			request.getRequestDispatcher(PageName.DEPOSIT_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException(ExceptionMessage.FORWARD_TO_PAGE, e);
		}
	}

}
