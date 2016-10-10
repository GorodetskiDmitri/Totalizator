package by.epam.totalizator.command.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.exception.CommandException;
import by.epam.totalizator.command.exception.ExceptionMessage;
import by.epam.totalizator.controller.PageName;
import by.epam.totalizator.controller.RequestParameterName;
import by.epam.totalizator.entity.Competition;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.Sport;
import by.epam.totalizator.service.AdminService;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.impl.AdminServiceImpl;

public class AddEventCommand implements Command {
	
	private static final Logger logger = Logger.getLogger(AddEventCommand.class);
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		try {
			int sportId = Integer.parseInt(request.getParameter(RequestParameterName.SPORT_ID));
			int competitionId = Integer.parseInt(request.getParameter(RequestParameterName.COMPETITION_ID));
			
			Line line = new Line();
			Sport sport = new Sport();
			Competition competition = new Competition();
				
			sport.setId(sportId);
			competition.setId(competitionId);
			line.setSport(sport);
			line.setCompetition(competition);
			line.setEventName(request.getParameter(RequestParameterName.EVENT_NAME));
			try {
				line.setWinCoeff(Double.parseDouble(request.getParameter(RequestParameterName.EVENT_WIN_COEFF)));
			} catch (NumberFormatException e) {
				line.setWinCoeff(0.0);
			}
			try {
				line.setDrawCoeff(Double.parseDouble(request.getParameter(RequestParameterName.EVENT_DRAW_COEFF)));
			} catch (NumberFormatException e) {
				line.setDrawCoeff(0.0);
			}
			try {
				line.setLoseCoeff(Double.parseDouble(request.getParameter(RequestParameterName.EVENT_LOSE_COEFF)));
			} catch (NumberFormatException e) {
				line.setLoseCoeff(0.0);
			}
			try {
				line.setMinBet(Double.parseDouble(request.getParameter(RequestParameterName.EVENT_MIN_BET)));
			} catch (NumberFormatException e) {
				line.setMinBet(0.1);
			}
			try {
				line.setMaxBet(Double.parseDouble(request.getParameter(RequestParameterName.EVENT_MAX_BET)));
			} catch (NumberFormatException e) {
				line.setMaxBet(100.0);
			}
			
			String startDateStr = request.getParameter(RequestParameterName.EVENT_DATE).trim(); 
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			try {
				Date startDate = dateFormat.parse(startDateStr);
				line.setStartDate(startDate);
			} catch (ParseException e) {
				logger.info(ExceptionMessage.DATE_FORMAT);
				throw new CommandException(ExceptionMessage.DATE_FORMAT, e);
			}
			
			AdminService service = AdminServiceImpl.getInstance();
			if (service.addEvent(line)) {
				StringBuffer currentCommand = (StringBuffer) request.getSession().getAttribute(RequestParameterName.CURRENT_COMMAND);
				try {
					response.sendRedirect(currentCommand.toString());
				} catch (IOException e) {
					throw new CommandException(ExceptionMessage.SEND_REDIRECT, e);
				}
			} else {
				try {
					request.getRequestDispatcher(PageName.ERROR_PAGE).forward(request, response);
				} catch (ServletException | IOException e) {
					throw new CommandException(ExceptionMessage.FORWARD_TO_PAGE, e);
				}
			}
		} catch (ServiceException | NumberFormatException e) {
			logger.error(e.getMessage());
			throw new CommandException(e.getMessage(), e);
		}
	}
}
