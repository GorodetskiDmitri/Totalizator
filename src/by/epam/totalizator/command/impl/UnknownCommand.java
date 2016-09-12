package by.epam.totalizator.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.controller.PageName;

public class UnknownCommand implements Command {

	private static final String CURRENT_COMMAND = "current_command";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String currentCommand = (String)request.getSession().getAttribute(CURRENT_COMMAND);
		if (currentCommand != null) {
			try {
				response.sendRedirect(currentCommand);
			} catch (IOException e) {
				throw new CommandException("Send Redirect Exception", e);
			}
		} else {
			try {
				request.getRequestDispatcher(PageName.INDEX_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				throw new CommandException("Could not forward to the page");
			}
		}
	}
}
