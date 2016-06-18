package by.epam.totalizator.dao;

import by.epam.totalizator.dao.impl.SqlAdminDAO;
import by.epam.totalizator.dao.impl.SqlUserDAO;

public class DAOFactory {

	private AdminDAO adminDAO = new SqlAdminDAO();
	private UserDAO userDAO = new SqlUserDAO();
	
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
