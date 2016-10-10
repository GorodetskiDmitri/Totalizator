package by.epam.totalizator.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.command.exception.ExceptionMessage;
import by.epam.totalizator.controller.ControllerUtil;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.service.UserService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.impl.UserServiceImpl;

public class ShowResultCommand implements Command {

	private static final Logger logger = Logger.getLogger(ShowResultCommand.class);
	private static final int LINES_ON_PAGE = 6;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		StringBuffer url = ControllerUtil.getCurrentCommandUrl(request);
		request.getSession().setAttribute(RequestParameterName.CURRENT_COMMAND, url);
		
		int currentPage = Integer.parseInt(request.getParameter(RequestParameterName.CURRENT_PAGE));
		
		List<Line> resultList;
		try {
			UserService service = UserServiceImpl.getInstance();
			resultList = service.getResultList();
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new CommandException(e.getMessage(), e);
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
			throw new CommandException(ExceptionMessage.FORWARD_TO_PAGE, e);
		}
	}
	
	private static List<Line> getResListInPage(List<Line> totalResList,int currentPage, int linesInPage) {
		List<Line> resList = new ArrayList<Line>();
		int i = 1;
		for (Line line : totalResList) {
			if (i > (currentPage-1)*linesInPage && i <= (currentPage)*linesInPage) {
				resList.add(line);
			}
			if (i > (currentPage+1)*linesInPage) {
				break;
			}
			i++;
		}
		return resList;
	}

}
