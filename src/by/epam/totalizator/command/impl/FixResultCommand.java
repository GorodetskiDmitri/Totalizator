package by.epam.totalizator.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.command.exception.ExceptionMessage;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.service.AdminService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.impl.AdminServiceImpl;

public class FixResultCommand implements Command {

	private static final Logger logger = Logger.getLogger(FixResultCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		int lineId = Integer.parseInt(request.getParameter(RequestParameterName.LINE_ID));
		int score1 = Integer.parseInt(request.getParameter(RequestParameterName.SCORE1));
		int score2 = Integer.parseInt(request.getParameter(RequestParameterName.SCORE2));
		
		try {
			AdminService service = AdminServiceImpl.getInstance();
			if (service.fixResult(score1, score2, lineId)) {
				StringBuffer currentCommand = (StringBuffer) request.getSession().getAttribute(RequestParameterName.CURRENT_COMMAND);
				try {
					response.sendRedirect(currentCommand.toString());
				} catch (IOException e) {
					throw new CommandException(ExceptionMessage.SEND_REDIRECT, e);
				}
			} else {
				try {
					request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
				} catch (ServletException | IOException e) {
					throw new CommandException(ExceptionMessage.FORWARD_TO_PAGE, e);
				}
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new CommandException(e.getMessage(), e);
		}
	}
}
