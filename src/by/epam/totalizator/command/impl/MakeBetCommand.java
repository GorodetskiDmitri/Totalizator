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
import by.epam.totalizator.entity.Bet;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.service.UserService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.impl.UserServiceImpl;

public class MakeBetCommand implements Command {

	private static final Logger logger = Logger.getLogger(MakeBetCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		int userId = Integer.parseInt(request.getParameter(RequestParameterName.USER_ID));
		int lineId = Integer.parseInt(request.getParameter(RequestParameterName.LINE_ID));
		String outcome = request.getParameter(RequestParameterName.OUTCOME);
		double summa = Double.parseDouble(request.getParameter(RequestParameterName.SUMMA));
		
		User user = null;
		Line line = null;
		Bet bet = null;
		
		try {
			user = new User();
			user.setId(userId);
			line = new Line();
			line.setId(lineId);
			bet = new Bet();
			bet.setUser(user);
			bet.setLine(line);
			bet.setAmount(summa);
			bet.setOutcome(outcome);
			
			UserService service = UserServiceImpl.getInstance();
			if (service.makeBet(bet)) {
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
