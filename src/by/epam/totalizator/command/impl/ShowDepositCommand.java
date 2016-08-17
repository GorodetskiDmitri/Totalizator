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


public class ShowDepositCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		StringBuffer url = ControllerUtil.getCurrentCommandUrl(request);
		request.getSession().setAttribute(RequestParameterName.CURRENT_COMMAND, url);
		
		try {
			request.getRequestDispatcher(PageName.DEPOSIT_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException("Couldn't forward to the page");
		}
	}

}
