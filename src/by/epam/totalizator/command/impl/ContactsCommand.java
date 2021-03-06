package by.epam.totalizator.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.command.exception.ExceptionMessage;
import by.epam.totalizator.controller.ControllerUtil;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;

public class ContactsCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		StringBuffer url = ControllerUtil.getCurrentCommandUrl(request);
		request.getSession().setAttribute(RequestParameterName.CURRENT_COMMAND, url);
		
		try {
			request.getRequestDispatcher(PageName.CONTACTS_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException(ExceptionMessage.FORWARD_TO_PAGE, e);
		}
	}

}
