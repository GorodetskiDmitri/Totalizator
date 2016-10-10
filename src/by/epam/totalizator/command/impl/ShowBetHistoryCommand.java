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
import by.epam.totalizator.entity.Bet;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.service.UserService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.impl.UserServiceImpl;

public class ShowBetHistoryCommand implements Command {
	
	private static final Logger logger = Logger.getLogger(ShowBetHistoryCommand.class);
	private static final int LINES_ON_PAGE = 10;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		StringBuffer url = ControllerUtil.getCurrentCommandUrl(request);
		User user = (User) request.getSession().getAttribute(RequestParameterName.USER);
		int idUser = user.getId();
		request.getSession(true).setAttribute(RequestParameterName.CURRENT_COMMAND, url);
		
		int currentPage = Integer.parseInt(request.getParameter(RequestParameterName.CURRENT_PAGE));
		List<Bet> totalBetList;
		try {
			UserService service = UserServiceImpl.getInstance();
			totalBetList = service.getAllUserBet(idUser);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new CommandException(e.getMessage(), e);
		}
		int totalPage = totalBetList.size() / LINES_ON_PAGE;
		if ((totalBetList.size() % LINES_ON_PAGE) > 0) {
			totalPage = totalPage + 1;
		}
		
		List<String> pageList = new ArrayList<String>();
		for (int i = 1; i <= totalPage; i++) {
			pageList.add(Integer.toString(i));
		}
		
		List<Bet> betList = getBetListInPage(totalBetList, currentPage, LINES_ON_PAGE);
		request.setAttribute(RequestParameterName.TOTAL_PAGE, totalPage);
		request.setAttribute(RequestParameterName.BET_LIST, betList);
		request.setAttribute(RequestParameterName.PAGE_LIST, pageList);
		try {
			request.getRequestDispatcher(PageName.BET_HISTORY_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException(ExceptionMessage.FORWARD_TO_PAGE, e);
		}
	}
	
	private static List<Bet> getBetListInPage(List<Bet> totalBetList,int currentPage, int linesInPage) {
		List<Bet> betList = new ArrayList<Bet>();
		int i = 1;
		for (Bet bet : totalBetList) {
			if (i > (currentPage-1)*linesInPage && i <= (currentPage)*linesInPage) {
				betList.add(bet);
			}
			if (i > (currentPage+1)*linesInPage) {
				break;
			}
			i++;
		}
		return betList;
	}
}
