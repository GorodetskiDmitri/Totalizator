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
import by.epam.totalizator.entity.Competition;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.Sport;
import by.epam.totalizator.entity.User;

public class SqlAdminDAO extends SqlUserDAO implements AdminDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private static final String USER_LIST = "SELECT id, status, login, password, balance, name, sirname, email, address, phone, passport, date_of_birth, bet_allow FROM users WHERE status='client' ORDER BY login";
	private static final String GET_RESULT_LIST_FOR_FIX = "SELECT a.*, s.name, c.name FROM line a INNER JOIN sport s ON a.id_sport=s.id INNER JOIN competition c ON a.id_competition=c.id WHERE a.fixed_result='0' AND a.start_date <= CURDATE() ORDER BY a.start_date, s.name, c.name, a.event_name";
	private static final String REMOVE_USER = "DELETE FROM users WHERE id=? AND status='client' AND balance=0.0";
	private static final String ALLOW_BET_FOR_USER = "UPDATE users SET bet_allow=? WHERE id=? AND status='client'";
	private static final String FIX_RESULT = "UPDATE line SET fixed_result='1', score1=?, score2=? WHERE id=?";
	private static final String DEFAULT_LOSE_BET = "UPDATE bet SET bet_status='1' WHERE id_line=?";
	private static final String FIRST_TEAM_WIN = "UPDATE bet SET bet_status='3' WHERE outcome='1' AND id_line=?";
	private static final String WIN_BETS_LIST = "SELECT b.id_user, Sum(b.amount), a.win_coeff FROM bet b, line a WHERE b.id_line=a.id AND b.outcome='1' AND b.bet_status='3' AND b.id_line=? GROUP BY b.id_user";
	
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
						+ "OR sirname LIKE '%" + findCriteria + "%')";
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
	public boolean removeUser(int userId) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(REMOVE_USER);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
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
	public boolean allowBetForUser(int userId, String allowBet) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(ALLOW_BET_FOR_USER);
			preparedStatement.setString(1, allowBet);
			preparedStatement.setInt(2, userId);
			preparedStatement.executeUpdate();			
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
	public List<Line> getResultListForFix() throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		Line line = null;
		Sport sport = null;
		Competition competition = null;
		List<Line> lineList = new ArrayList<Line>();
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_RESULT_LIST_FOR_FIX);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				sport = new Sport();
				sport.setId(resultSet.getInt(2));
				sport.setName(resultSet.getString(14));
				
				competition = new Competition();
				competition.setId(resultSet.getInt(3));
				competition.setName(resultSet.getString(15));
				
				line = new Line();
				line.setId(resultSet.getInt(1));
				line.setSport(sport);
				line.setCompetition(competition);
				line.setEventName(resultSet.getString(4));
				line.setStartDate(resultSet.getTimestamp(5));
				line.setWinCoeff(resultSet.getDouble(6));
				line.setDrawCoeff(resultSet.getDouble(7));
				line.setLoseCoeff(resultSet.getDouble(8));
				line.setMinBet(resultSet.getDouble(9));
				line.setMaxBet(resultSet.getDouble(10));
				line.setFixedResult(resultSet.getString(11));
				line.setScore1(resultSet.getInt(12));
				line.setScore2(resultSet.getInt(13));
				lineList.add(line);
			} 
		} catch (SQLException e)  {
			throw new DAOException("SQL query not correct", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement, resultSet);
		}
		return lineList;
	}
	
	@Override
	public boolean fixResult(int score1, int score2, int lineId) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		PreparedStatement insidePrepareStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			
			prepareStatement = connection.prepareStatement(FIX_RESULT);
			prepareStatement.setInt(1, score1);
			prepareStatement.setInt(2, score2);
			prepareStatement.setInt(3, lineId);
			prepareStatement.executeUpdate();
			prepareStatement.close();
			
			prepareStatement = connection.prepareStatement(DEFAULT_LOSE_BET);
			prepareStatement.setInt(1, lineId);
			prepareStatement.executeUpdate();
			prepareStatement.close();
			
			if (score1 > score2) { 
				prepareStatement = connection.prepareStatement(FIRST_TEAM_WIN);
				prepareStatement.setInt(1, lineId);
				prepareStatement.executeUpdate();
				prepareStatement.close();
				
				prepareStatement = connection.prepareStatement(WIN_BETS_LIST);
				prepareStatement.setInt(1, lineId);
				resultSet = prepareStatement.executeQuery();
				while (resultSet.next()) {
				
				}
			}
			
			connection.commit();
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DAOException("Exception rollback transaction");
			}
			throw new DAOException("Invalid sql query",e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement);
		}
		return true;
	}

}
