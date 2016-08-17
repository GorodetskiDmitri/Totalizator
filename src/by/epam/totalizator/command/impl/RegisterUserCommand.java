package by.epam.totalizator.command.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.service.UserService;
import by.epam.totalizator.service.exception.ServiceException;

public class RegisterUserCommand implements Command {
	
	private static final String DATE_FORMAT_DAY_MONTH_YEAR = "dd.MM.yyyy";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		User user = new User();
		user.setLogin(request.getParameter(RequestParameterName.REG_LOGIN).trim());
		if (request.getParameter(RequestParameterName.REG_PASSWORD) != null) {
			user.setPassword(request.getParameter(RequestParameterName.REG_PASSWORD).trim().hashCode());
		}
		user.setName(request.getParameter(RequestParameterName.REG_NAME).trim());
		user.setSurname(request.getParameter(RequestParameterName.REG_SURNAME).trim());
		user.setEmail(request.getParameter(RequestParameterName.REG_EMAIL).trim());
		user.setAddress(request.getParameter(RequestParameterName.REG_ADDRESS).trim());
		user.setPhone(request.getParameter(RequestParameterName.REG_PHONE).trim());
		user.setPassport(request.getParameter(RequestParameterName.REG_PASSPORT).trim());
		String dateOfBirthStr = request.getParameter(RequestParameterName.REG_DATE_OF_BIRTH).trim(); 
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_DAY_MONTH_YEAR);
		try {
			Date dateOfBirth = dateFormat.parse(dateOfBirthStr);
			user.setDateOfBirth(dateOfBirth);
		} catch (ParseException e) {
			throw new CommandException("Invalid date format", e);
		}

		String page = null;
		try {
			user = UserService.registerUser(user);
		} catch (ServiceException e) {
			System.out.println("catch");
			throw new CommandException(e);
		}
		
		if (user != null) {
			request.getSession().setAttribute(RequestParameterName.USER, user);
			page = PageName.USER_PAGE;
		} else {
			request.setAttribute(RequestParameterName.CONCURENSE_LOGIN, true);
			page = PageName.REGISTRATION_PAGE;
		}
		try {
			request.getRequestDispatcher(page).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException("Could not forward to the page",e);
		}
	}
}
