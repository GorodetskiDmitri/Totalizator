package by.epam.totalizator.command.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.controller.ControllerUtil;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.service.AdminService;
import by.epam.totalizator.service.exception.ServiceException;


public class ShowUserListCommand implements Command{
	
	private static final int LINES_ON_PAGE = 7;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		StringBuffer url = ControllerUtil.getCurrentCommandUrl(request);
		request.getSession(true).setAttribute(RequestParameterName.CURRENT_COMMAND, url);
		String searchText = request.getParameter(RequestParameterName.SEARCH_TEXT);
		if (searchText != null) {
			searchText = searchText.trim();
		}
		int currentPage = Integer.parseInt(request.getParameter(RequestParameterName.CURRENT_PAGE));
		List<User> totalUserList;
		try {
			totalUserList = AdminService.getUserList(searchText);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		int totalPage = totalUserList.size() / LINES_ON_PAGE;
		if ((totalUserList.size() % LINES_ON_PAGE) > 0) {
			totalPage = totalPage + 1;
		}
		
		List<String> pageList = new ArrayList<String>();
		for (int i = 1; i <= totalPage; i++) {
			pageList.add(Integer.toString(i));
		}
		
		List<User> userList = getUserListInPage(totalUserList, currentPage, LINES_ON_PAGE);
		request.setAttribute(RequestParameterName.TOTAL_PAGE, totalPage);
		request.setAttribute(RequestParameterName.USER_LIST, userList);
		request.setAttribute(RequestParameterName.PAGE_LIST, pageList);
		try {
			request.getRequestDispatcher(PageName.USER_LIST_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException("Couldn't forward to the page");
		}
	}
	
	private static List<User> getUserListInPage(List<User> totalUserList,int currentPage, int linesInPage){
		List<User> userList = new ArrayList<User>();
		int i = 1;
		for (User user : totalUserList) {
			if(i > (currentPage-1)*linesInPage && i<=(currentPage)*linesInPage){
				userList.add(user);
			}
			if(i>(currentPage+1)*linesInPage){
				break;
			}
			i++;
		}
		return userList;
	}
}
