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
import by.epam.totalizator.entity.Competition;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.Sport;
import by.epam.totalizator.service.AdminService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.impl.AdminServiceImpl;

public class ShowEventCreation implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		StringBuffer url = ControllerUtil.getCurrentCommandUrl(request);
		request.getSession().setAttribute(RequestParameterName.CURRENT_COMMAND, url);
		
		List<Sport> sportList;
		List<Competition> competitionList;
		List<Line> lineList;
		try {
			AdminService service = AdminServiceImpl.getInstance();
			sportList = service.getSportList();
			competitionList = service.getCompetitionList();
			lineList = service.getLast5Lines();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(RequestParameterName.SPORT, sportList);
		request.setAttribute(RequestParameterName.COMPETITION, competitionList);
		request.setAttribute(RequestParameterName.LINE, lineList);
		
		try {
			request.getRequestDispatcher(PageName.ADD_EVENT_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException("Couldn't forward to the page");
		}
	}

}

