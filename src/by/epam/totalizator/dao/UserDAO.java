package by.epam.totalizator.dao;

import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.User;

public interface UserDAO {
	User getUser(String login, int password) throws DAOException;
	boolean registerUser(User user) throws DAOException;
	boolean isLoginFree(String login) throws DAOException;
	boolean makeDeposit(String login, double summa) throws DAOException;
	double getUnresolvedMoney(int idUser) throws DAOException;
}
