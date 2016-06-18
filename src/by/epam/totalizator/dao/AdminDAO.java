package by.epam.totalizator.dao;

import java.util.List;

import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.User;

public interface AdminDAO extends UserDAO {
	List<User> getUserList() throws DAOException;
	boolean removeUser(int userId) throws DAOException;
}
