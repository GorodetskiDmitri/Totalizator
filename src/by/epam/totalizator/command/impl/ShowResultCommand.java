package by.epam.totalizator.command.impl;

import java.io.IOException;
import java.util.ArrayList;
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

public class ShowResultCommand implements Command {

	private static final int LINES_ON_PAGE = 10;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		StringBuffer url = ControllerUtil.getCurrentCommandUrl(request);
		Line line = (Line) request.getSession().getAttribute(RequestParameterName.LINE);
		request.getSession().setAttribute(RequestParameterName.CURRENT_COMMAND, url);
		
		int currentPage = Integer.parseInt(request.getParameter(RequestParameterName.CURRENT_PAGE));
		
		List<Line> resultList;
		try {
			resultList = UserService.getResultList();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		int totalPage = resultList.size() / LINES_ON_PAGE;
		if ((resultList.size() % LINES_ON_PAGE) > 0) {
			totalPage = totalPage + 1;
		}
		
		List<String> pageList = new ArrayList<String>();
		for (int i = 1; i <= totalPage; i++) {
			pageList.add(Integer.toString(i));
		}
		
		List<Line> resList = getResListInPage(resultList, currentPage, LINES_ON_PAGE);
		request.setAttribute(RequestParameterName.TOTAL_PAGE, totalPage);
		request.setAttribute(RequestParameterName.LINE, resList);
		request.setAttribute(RequestParameterName.PAGE_LIST, pageList);
		
		try {
			request.getRequestDispatcher(PageName.RESULT_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException("Couldn't forward to the page");
		}
	}
	
	private static List<Line> getResListInPage(List<Line> totalResList,int currentPage, int linesInPage){
		List<Line> resList = new ArrayList<Line>();
		int i = 1;
		for (Line line : totalResList) {
			if (i > (currentPage-1)*linesInPage && i<=(currentPage)*linesInPage) {
				resList.add(line);
			}
			if(i>(currentPage+1)*linesInPage){
				break;
			}
			i++;
		}
		return resList;
	}

}
