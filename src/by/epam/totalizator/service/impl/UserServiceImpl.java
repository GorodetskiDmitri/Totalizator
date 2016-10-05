package by.epam.totalizator.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.totalizator.dao.DAOFactory;
import by.epam.totalizator.dao.UserDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.Bet;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.service.UserService;
import by.epam.totalizator.service.exception.ServiceException;

public final class UserServiceImpl implements UserService {
	
	private static final UserServiceImpl userService = new UserServiceImpl();
	private static final DAOFactory factory = DAOFactory.getInstance();
	private static final UserDAO userDAO = factory.getUserDAO();
	
	private UserServiceImpl() {
	}
	
	public static UserServiceImpl getInstance() {
		return userService;
	}
	
	@Override
	public User checkLogin(String login, String password) throws ServiceException {
		if (!Validation.validateLogin(login, password)) {
			throw new ServiceException("Invalid login or password");
		}
		User user = null;
		try {
			user = userDAO.getUser(login, password.hashCode());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public User getUser(String login, int password) throws ServiceException {
		User user = null;
		try {
			user = userDAO.getUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e); 
		}
		return user;
	}

	@Override
	public User registerUser(User user) throws ServiceException {
		if (!Validation.validateRegistrationData(user)) {
			throw new ServiceException("Invalid registration param");
		}
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

	@Override
	public boolean makeDeposit(String login, double summa) throws ServiceException {
		try {
			return userDAO.makeDeposit(login, summa);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public double getUnresolvedMoney(int idUser) throws ServiceException {
		double money = 0.0;
		try {
			money = userDAO.getUnresolvedMoney(idUser);
		} catch (DAOException e) {
			throw new ServiceException(e); 
		}
		return money;
	}

	@Override
	public List<Bet> getAllUserBet(int idUser) throws ServiceException {
		List<Bet> betList = new ArrayList<Bet>();
		
		try {
			betList = userDAO.getAllUserBet(idUser);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return betList;
	}

	@Override
	public List<Line> getLineList() throws ServiceException {
		List<Line> lineList = new ArrayList<Line>();
		
		try {
			lineList = userDAO.getLineList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return lineList;
	}

	@Override
	public List<Line> getResultList() throws ServiceException {
		List<Line> resultList = new ArrayList<Line>();
		
		try {
			resultList = userDAO.getResultList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return resultList;
	}

	@Override
	public boolean makeBet(Bet bet) throws ServiceException {
		try {
			return userDAO.makeBet(bet);			
		} catch (DAOException e) {
			throw new ServiceException(e); 
		}
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
