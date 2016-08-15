package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.totalizator.dao.AdminDAO;
import by.epam.totalizator.dao.connectionpool.ConnectionPool;
import by.epam.totalizator.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.User;

public class SqlAdminDAO extends SqlUserDAO implements AdminDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private static final String USER_LIST = "SELECT id, status, login, password, balance, name, sirname,"
			+ "email, address, phone, passport, date_of_birth, bet_allow FROM users WHERE status='client'";
	private static final String ALLOW_BET_FOR_USER = "UPDATE users SET bet_allow='1' WHERE id=? AND status='client'";
	
	@Override
	public List<User> getUserList(String findCriteria) throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		User user = null;
		List<User> userList = new ArrayList<User>();
		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
			String query = USER_LIST;
			if (findCriteria != null && !findCriteria.equals("")) {
				query += " AND (login LIKE '%" + findCriteria + "%' OR name LIKE '%" + findCriteria + "%' "
						+ "OR surname LIKE '%" + findCriteria + "%')";
			}
			resultSet = statement.executeQuery(query);
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
				userList.add(user);
			}
		} catch (SQLException e)  {
			throw new DAOException("SQL query not correct", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, statement, resultSet);
		}
		return userList;
	}

	@Override
	public boolean allowBetForUser(int userId) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(ALLOW_BET_FOR_USER);
			prepareStatement.setInt(1, userId);
			prepareStatement.executeUpdate();			
		} catch (SQLException e)  {
			throw new DAOException("SQL query not correct", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement);
		}
		return true;
	}

}
