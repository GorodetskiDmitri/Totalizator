package by.epam.totalizator.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.controller.ControllerUtil;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.service.UserService;
import by.epam.totalizator.service.exception.ServiceException;

public class ShowLineCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		StringBuffer url = ControllerUtil.getCurrentCommandUrl(request);
		Line line = (Line) request.getSession().getAttribute(RequestParameterName.LINE);
		request.getSession().setAttribute(RequestParameterName.CURRENT_COMMAND, url);
		
		List<Line> lineList;
		try {
			lineList = UserService.getLineList();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(RequestParameterName.LINE, lineList);
		try {
			request.getRequestDispatcher(PageName.LINE_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException("Couldn't forward to the page");
		}
	}

}
