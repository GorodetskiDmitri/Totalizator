package by.epam.totalizator.service;

import java.util.ArrayList;
import java.util.List;

import by.epam.totalizator.dao.AdminDAO;
import by.epam.totalizator.dao.DAOFactory;
import by.epam.totalizator.dao.UserDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.Bet;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.service.exception.ServiceException;

public final class UserService {
	
	public final static User checkLogin(String login, String password) throws ServiceException {
		if (!Validation.validateLogin(login, password)) {
			throw new ServiceException("Invalid login or password");
		}
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		User user = null;
		try {
			user = userDAO.getUser(login, password.hashCode());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}
	
	public final static User getUser(String login, int password) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		User user = null;
		try {
			user = userDAO.getUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e); 
		}
		return user;
	}
	
	public final static User registerUser(User user) throws ServiceException {
		if (!Validation.validateRegistrationData(user)) {
			throw new ServiceException("Invalid registration param");
		}
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		try {
			if (!userDAO.isLoginFree(user.getLogin())){
				return null;
			}
			userDAO.registerUser(user);
			user = userDAO.getUser(user.getLogin(), user.getPassword());			
		} catch (DAOException e) {
			throw new ServiceException(e); 
		}
		return user;
	}
	
	public final static boolean makeDeposit(String login, double summa) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		
		try {
			return userDAO.makeDeposit(login, summa);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public final static double getUnresolvedMoney(int idUser) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		double money = 0.0;
		try {
			money = userDAO.getUnresolvedMoney(idUser);
			List<Bet> betList = userDAO.getAllUserBet(2);
			System.out.println("BET = "+betList.size());
		} catch (DAOException e) {
			throw new ServiceException(e); 
		}
		return money;
	}
	
	public final static List<Bet> getAllUserBet(int idUser) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		List<Bet> betList = new ArrayList<Bet>();
		
		try {
			betList = userDAO.getAllUserBet(idUser);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return betList;
	}
	
	public final static List<Line> getLineList() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		List<Line> lineList = new ArrayList<Line>();
		
		try {
			lineList = userDAO.getLineList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return lineList;
	}
	
	static class Validation {
		
		public static boolean validateLogin(String login, String password) {
			if (login.isEmpty()) {
				return false;
			}
			if (password.isEmpty()) {
				return false;
			}
			return true;
		}
		
		public static boolean validateRegistrationData(User user) {
			if (user == null) {
				return false;
			} 
			if (user.getLogin().isEmpty()) {
				return false;
			}
			if (user.getPassword() == 0) {
				return false;
			}
			if (user.getName().isEmpty()) {
				return false;
			}
			if (user.getSurname().isEmpty()) {
				return false;
			}
			if (user.getEmail().isEmpty()) {
				return false;
			}
			if (user.getAddress().isEmpty()) {
				return false;
			}
			if (user.getPhone().isEmpty()) {
				return false;
			}
			if (user.getPassport().isEmpty()) {
				return false;
			}
			if (user.getDateOfBirth() == null) {
				return false;
			}
			return true;
		}
	}
}
