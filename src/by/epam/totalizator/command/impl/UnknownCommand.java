package by.epam.totalizator.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.command.exception.ExceptionMessage;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;

public class UnknownCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		StringBuffer currentCommand = (StringBuffer) request.getSession().getAttribute(RequestParameterName.CURRENT_COMMAND);
		if (currentCommand != null) {
			try {
				response.sendRedirect(currentCommand.toString());
			} catch (IOException e) {
				throw new CommandException(ExceptionMessage.SEND_REDIRECT, e);
			}
		} else {
			try {
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				throw new CommandException(ExceptionMessage.FORWARD_TO_PAGE, e);
			}
		}
	}
}
