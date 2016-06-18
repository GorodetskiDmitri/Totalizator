package by.epam.totalizator.dao;

import java.util.Date;

import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.User;

public interface UserDAO {
	User getUser(String login, String password) throws DAOException;
	boolean registerUser(String login, String password, Double balance, String name, String surname, String email, String address, String phone, String passport, Date dateOfBirth) throws DAOException;
	boolean hasLogin(String login) throws DAOException;
}
