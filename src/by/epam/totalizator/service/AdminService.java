package by.epam.totalizator.service;

import by.epam.totalizator.dao.DAOFactory;

import java.util.ArrayList;
import java.util.List;

import by.epam.totalizator.dao.AdminDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.User;
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
}
