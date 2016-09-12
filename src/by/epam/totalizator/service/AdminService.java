package by.epam.totalizator.service;

import by.epam.totalizator.dao.DAOFactory;
import by.epam.totalizator.dao.connectionpool.ConnectionPool;
import by.epam.totalizator.dao.connectionpool.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.totalizator.dao.AdminDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.Competition;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.Sport;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.entity.Winner;
import by.epam.totalizator.service.exception.ServiceException;


public class AdminService {
	
	public static List<User> getUserList(String findCriteria) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getAdminDAO();
		List<User> userList = new ArrayList<User>();
		
		try {
			userList = adminDAO.getUserList(findCriteria);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return userList;
	}
	
	public static boolean removeUser(int userId) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getAdminDAO();
		
		try {
			return adminDAO.removeUser(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public static boolean allowBetForUser(int userId, String allowBet) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getAdminDAO();
		
		try {
			return adminDAO.allowBetForUser(userId, allowBet);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public final static List<Line> getResultListForFix() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getAdminDAO();
		List<Line> lineList = new ArrayList<Line>();
		
		try {
			lineList = adminDAO.getResultListForFix();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return lineList;
	}
	
	public static boolean fixResult(int score1, int score2, int lineId) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getAdminDAO();
		
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		List<Winner> winBets = null;
		int outcome;
		
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
		
			adminDAO.fixResult(connection, score1, score2, lineId);
			adminDAO.defaultLose(connection, lineId);
			
			if (score1 > score2) {
				outcome = 1;
			} else if (score1 == score2) {
				outcome = 2;
			} else {
				outcome = 3;
			}
			
			adminDAO.checkWinBet(connection, lineId, outcome);
			winBets = adminDAO.getWinners(connection, lineId, outcome);
			
			if (winBets != null) {
				adminDAO.payout(connection, winBets);
			}
			connection.commit();
		} catch (ConnectionPoolException | DAOException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new ServiceException("Exception rollback transaction", e1);
			}
			throw new ServiceException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ServiceException(e);
			}
		}
		return true;
	}
	
	public final static List<Sport> getSportList() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getAdminDAO();
		List<Sport> sportList = new ArrayList<Sport>();
		
		try {
			sportList = adminDAO.getSportList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return sportList;
	}
	
	public final static List<Competition> getCompetitionList() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO = factory.getAdminDAO();
		List<Competition> competitionList = new ArrayList<Competition>();
		
		try {
			competitionList = adminDAO.getCompetitionList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return competitionList;
	}
}
