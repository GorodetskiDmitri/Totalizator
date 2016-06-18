package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import by.epam.totalizator.dao.UserDAO;
import by.epam.totalizator.dao.connectionpool.ConnectionPool;
import by.epam.totalizator.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.User;

public class SqlUserDAO implements UserDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private static final String GET_USER = "SELECT * FROM users WHERE login=? AND password=? AND status='user'";
	private static final String GET_LOGIN = "SELECT login FROM users WHERE login=?";
	private static final String INSERT_USER = "INSERT INTO users(status, login, password, balance, name, sirname, "
			+ "email, address, phone, passport, date_of_birth, bet_allow) "
			+ "VALUES('user',?,?,?,?,?,?,?,?,?,?,'0')";
	
	@Override
	public User getUser(String login, String password) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		User user = null;
		int pass = password.hashCode();
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_USER);
			prepareStatement.setString(1, login);
			prepareStatement.setInt(2, pass);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setStatus(resultSet.getString(2));
				user.setLogin(resultSet.getString(3));
				user.setPassword(resultSet.getInt(4));
				user.setBalance(resultSet.getDouble(5));
				user.setName(resultSet.getString(6));
				user.setSurname(resultSet.getString(7));
				user.setEmail(resultSet.getString(8));
				user.setAddress(resultSet.getString(9));
				user.setPhone(resultSet.getString(10));
				user.setPassport(resultSet.getString(11));
				user.setDateOfBirth(resultSet.getDate(12));
				user.setBetAllow(resultSet.getString(13));
			}
		} catch (SQLException e)  {
			throw new DAOException("SQL query not correct", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement, resultSet);
		}
		return user;
	}

	@Override
	public boolean registerUser(String login, String password, Double balance, String name, String surname, 
			String email, String address, String phone, String passport, Date dateOfBirth) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int pass = password.hashCode();
		try {
			connection = connectionPool.takeConnection();			
			preparedStatement = connection.prepareStatement(INSERT_USER);
			preparedStatement.setString(1, login);
			preparedStatement.setInt(2, pass);
			preparedStatement.setDouble(3, balance);
			preparedStatement.setString(4, name);
			preparedStatement.setString(5, surname);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, address);
			preparedStatement.setString(8, phone);
			preparedStatement.setString(9, passport);
			preparedStatement.setDate(10, (java.sql.Date) dateOfBirth);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e)  {
			throw new DAOException("SQL query not correct", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}
		return true;
	}

	@Override
	public boolean hasLogin(String login) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_LOGIN);
			prepareStatement.setString(1, login);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			return false;
		} catch (SQLException e)  {
			throw new DAOException("SQL query not correct", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement, resultSet);
		}
	}

	
	
}
