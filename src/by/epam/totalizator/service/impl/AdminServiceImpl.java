package by.epam.totalizator.service.impl;

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
import by.epam.totalizator.service.AdminService;
import by.epam.totalizator.service.exception.ServiceException;


public class AdminServiceImpl implements AdminService {
	
	private static final AdminServiceImpl adminService = new AdminServiceImpl();
	private static final DAOFactory factory = DAOFactory.getInstance();
	private static final AdminDAO adminDAO = factory.getAdminDAO(); 
	
	private AdminServiceImpl() {
	}
	
	public static AdminServiceImpl getInstance() {
		return adminService;
	}

	@Override
	public List<User> getUserList(String findCriteria) throws ServiceException {
		List<User> userList = new ArrayList<User>();
		
		try {
			userList = adminDAO.getUserList(findCriteria);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return userList;
	}

	@Override
	public boolean removeUser(int userId) throws ServiceException {
		try {
			return adminDAO.removeUser(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean allowBetForUser(int userId, String allowBet) throws ServiceException {
		try {
			return adminDAO.allowBetForUser(userId, allowBet);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Line> getResultListForFix() throws ServiceException {
		List<Line> lineList = new ArrayList<Line>();
		
		try {
			lineList = adminDAO.getResultListForFix();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return lineList;
	}

	@Override
	public boolean fixResult(int score1, int score2, int lineId) throws ServiceException {
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
				adminDAO.payoutBookmaker(connection, winBets);
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

	@Override
	public List<Sport> getSportList() throws ServiceException {
		List<Sport> sportList = new ArrayList<Sport>();
		
		try {
			sportList = adminDAO.getSportList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return sportList;
	}

	@Override
	public List<Competition> getCompetitionList() throws ServiceException {
		List<Competition> competitionList = new ArrayList<Competition>();
		
		try {
			competitionList = adminDAO.getCompetitionList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return competitionList;
	}

	@Override
	public boolean addEvent(Line line) throws ServiceException {
		try {
			return adminDAO.addEvent(line);			
		} catch (DAOException e) {
			throw new ServiceException(e); 
		}
	}

	@Override
	public List<Line> getLast5Lines() throws ServiceException {
		List<Line> lineList = new ArrayList<Line>();
		
		try {
			lineList = adminDAO.getLast5Lines();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return lineList;
	}
}
