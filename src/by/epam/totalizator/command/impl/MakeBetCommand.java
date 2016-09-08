package by.epam.totalizator.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.entity.Bet;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.service.UserService;
import by.epam.totalizator.service.exception.ServiceException;

public class MakeBetCommand implements Command {

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
			
			if (UserService.makeBet(bet)) {
				StringBuffer currentCommand = (StringBuffer) request.getSession().getAttribute(RequestParameterName.CURRENT_COMMAND);
				try {
					response.sendRedirect(currentCommand.toString());
				} catch (IOException e) {
					throw new CommandException("Could not sent redirect", e);
				}
			} else {
				try {
					request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
				} catch (ServletException | IOException e) {
					throw new CommandException("Could not forward to the page", e);
				}
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
	}
}
