package by.epam.totalizator.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.service.AdminService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.impl.AdminServiceImpl;

public class RemoveUserCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		int userId = Integer.parseInt(request.getParameter(RequestParameterName.USER_ID));
		try {
			AdminService service = AdminServiceImpl.getInstance();
			if (service.removeUser(userId)) {
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
