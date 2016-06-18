package by.epam.totalizator.dao.impl;

import java.util.List;

import by.epam.totalizator.dao.AdminDAO;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.User;

public class SqlAdminDAO extends SqlUserDAO implements AdminDAO {

	@Override
	public List<User> getUserList() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeUser(int userId) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

}
