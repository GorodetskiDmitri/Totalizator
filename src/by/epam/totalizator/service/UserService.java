package by.epam.totalizator.service;

import by.epam.totalizator.dao.DAOFactory;
import by.epam.totalizator.dao.UserDAO;
import by.epam.totalizator.dao.exception.DAOException;
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
			user = userDAO.getUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
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
	}
}
