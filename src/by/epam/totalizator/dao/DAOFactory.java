package by.epam.totalizator.dao;

import by.epam.totalizator.dao.impl.MySqlAdminDAO;
import by.epam.totalizator.dao.impl.MySqlUserDAO;

public class DAOFactory {

	private AdminDAO adminDAO = new MySqlAdminDAO();
	private UserDAO userDAO = new MySqlUserDAO();
	
	private DAOFactory() {
		
	}
	
	private static class Holder {
		private static final DAOFactory INSTANCE = new DAOFactory();
	}
	
	public static DAOFactory getInstance() {
		return Holder.INSTANCE;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public AdminDAO getAdminDAO() {
		return adminDAO;
	}
}
