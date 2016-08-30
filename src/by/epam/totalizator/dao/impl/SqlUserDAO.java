package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.totalizator.dao.UserDAO;
import by.epam.totalizator.dao.connectionpool.ConnectionPool;
import by.epam.totalizator.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.User;

public class SqlUserDAO implements UserDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private static final String GET_USER = "SELECT id, status, login, password, balance, name, sirname,"
			+ "email, address, phone, passport, date_of_birth, bet_allow FROM users WHERE login=? AND password=?";
	private static final String GET_LOGIN = "SELECT login FROM users WHERE login=?";
	private static final String INSERT_USER = "INSERT INTO users(status, login, password, balance, name, sirname, "
			+ "email, address, phone, passport, date_of_birth, bet_allow) "
			+ "VALUES('client',?,?,?,?,?,?,?,?,?,?,'0')";
	private static final String MAKE_DEPOSIT = "UPDATE users SET balance=? WHERE login=?";
	private static final String SHOW_UNRESOLVED_MONEY = "SELECT SUM(amount) FROM bet WHERE id_user=? AND bet_status='0';";
	
	@Override
	public User getUser(String login, int password) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_USER);
			prepareStatement.setString(1, login);
			prepareStatement.setInt(2, password);
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
	public boolean registerUser(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = connectionPool.takeConnection();			
			prepareStatement = connection.prepareStatement(INSERT_USER);
			prepareStatement.setString(1, user.getLogin());
			prepareStatement.setInt(2, user.getPassword());
			prepareStatement.setDouble(3, 0.0);
			prepareStatement.setString(4, user.getName());
			prepareStatement.setString(5, user.getSurname());
			prepareStatement.setString(6, user.getEmail());
			prepareStatement.setString(7, user.getAddress());
			prepareStatement.setString(8, user.getPhone());
			prepareStatement.setString(9, user.getPassport());
			prepareStatement.setDate(10, new java.sql.Date(user.getDateOfBirth().getTime()));
			prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e)  {
			throw new DAOException("SQL query not correct", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement);
		}
		return true;
	}

	@Override
	public boolean isLoginFree(String login) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_LOGIN);
			prepareStatement.setString(1, login);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				return false;
			}
			return true;
		} catch (SQLException e)  {
			throw new DAOException("SQL query not correct", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement, resultSet);
		}
	}

	@Override
	public boolean makeDeposit(String login, double summa) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(MAKE_DEPOSIT);
			prepareStatement.setDouble(1, summa);
			prepareStatement.setString(2, login);
			prepareStatement.executeUpdate();
			return true;
		} catch (SQLException e)  {
			throw new DAOException("SQL query not correct", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement);
		}
	}
	
	@Override
	public double getUnresolvedMoney(int userId) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		double money = 0.0;
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(SHOW_UNRESOLVED_MONEY);
			prepareStatement.setInt(1, userId);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				money = resultSet.getDouble(1);
			}
		} catch (SQLException e)  {
			throw new DAOException("SQL query not correct", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement);
		}
		return money;
	}
	
}
