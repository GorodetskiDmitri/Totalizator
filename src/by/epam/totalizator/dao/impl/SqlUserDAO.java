package by.epam.totalizator.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.totalizator.dao.UserDAO;
import by.epam.totalizator.dao.connectionpool.ConnectionPool;
import by.epam.totalizator.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.Bet;
import by.epam.totalizator.entity.Competition;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.Sport;
import by.epam.totalizator.entity.User;

public class SqlUserDAO implements UserDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private static final String GET_ALL_USER_BET = "SELECT * FROM bet WHERE id_user=? ORDER BY id DESC, bet_date DESC";
	private static final String GET_COMPETITION_BY_ID = "SELECT * FROM competition WHERE id=?";
	private static final String GET_LINE = "SELECT a.*, s.name, s.name_ru, c.name, c.name_ru FROM line a INNER JOIN sport s ON a.id_sport = s.id INNER JOIN competition c ON a.id_competition=c.id WHERE a.id=?";
	private static final String GET_LINE_LIST = "SELECT a.*, s.name, s.name_ru, c.name, c.name_ru FROM line a INNER JOIN sport s ON a.id_sport = s.id INNER JOIN competition c ON a.id_competition=c.id WHERE fixed_result='0' AND a.start_date >= NOW() ORDER BY s.name, c.name, a.start_date, a.event_name";
	private static final String GET_LOGIN = "SELECT login FROM users WHERE login=?";
	private static final String GET_RESULT_LIST = "SELECT a.*, s.name, s.name_ru, c.name, c.name_ru FROM line a INNER JOIN sport s ON a.id_sport = s.id INNER JOIN competition c ON a.id_competition=c.id WHERE a.fixed_result='1' ORDER BY DATE(a.start_date) DESC, s.name ASC, c.name ASC, a.event_name ASC";
	private static final String GET_SPORT_BY_ID = "SELECT * FROM sport WHERE id=?";
	private static final String GET_USER = "SELECT id, status, login, password, balance, name, sirname, email, address, phone, passport, date_of_birth, bet_allow FROM users WHERE login=? AND password=?";
	private static final String INCREASE_BOOKMAKER_BALANCE = "UPDATE users SET balance=(balance + (?)) WHERE status='bookmaker'";
	private static final String INSERT_BET = "INSERT INTO bet(id_user, id_line, bet_date, amount, outcome, bet_status) VALUES(?,?,CURDATE(),?,?,'0')";
	private static final String INSERT_USER = "INSERT INTO users(status, login, password, balance, name, sirname, email, address, phone, passport, date_of_birth, bet_allow) VALUES('client',?,?,?,?,?,?,?,?,?,?,'0')";
	private static final String MAKE_DEPOSIT = "UPDATE users SET balance=? WHERE login=?";
	private static final String REDUCE_USER_BALANCE = "UPDATE users SET balance=(balance - (?)) WHERE id=? AND status='client'";
	private static final String SHOW_UNRESOLVED_MONEY = "SELECT SUM(amount) FROM bet WHERE id_user=? AND bet_status='0';";
	
	protected static final String EXCEPTION_MESSAGE_SQL = "SQL query not correct";
	
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
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
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
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
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
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
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
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
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
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement, resultSet);
		}
		return money;
	}
	
	@Override
	public List<Bet> getAllUserBet(int userId) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Bet bet = null;
		Line line = null;
		List<Bet> betList = new ArrayList<Bet>();
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_ALL_USER_BET);
			prepareStatement.setInt(1, userId);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				bet = new Bet();
				bet.setId(resultSet.getInt(1));
				line = getLine(resultSet.getInt(3));
				bet.setLine(line);
				bet.setBetDate(resultSet.getDate(4));
				bet.setAmount(resultSet.getDouble(5));
				bet.setOutcome(resultSet.getString(6));
				bet.setBetStatus(resultSet.getString(7));
				betList.add(bet);
			}
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement, resultSet);
		}
		return betList;
	}
	
	@Override
	public Line getLine(int idLine) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		Line line = null;
		Sport sport = null;
		Competition competition = null;
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_LINE);
			prepareStatement.setInt(1, idLine);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				sport = new Sport();
				sport.setId(resultSet.getInt(2));
				sport.setName(resultSet.getString(14));
				sport.setNameRu(resultSet.getString(15));
				
				competition = new Competition();
				competition.setId(resultSet.getInt(3));
				competition.setName(resultSet.getString(16));
				competition.setNameRu(resultSet.getString(17));
				
				line = new Line();
				line.setId(resultSet.getInt(1));
				line.setSport(sport);
				line.setCompetition(competition);
				line.setEventName(resultSet.getString(4));
				line.setStartDate(resultSet.getDate(5));
				line.setWinCoeff(resultSet.getDouble(6));
				line.setDrawCoeff(resultSet.getDouble(7));
				line.setLoseCoeff(resultSet.getDouble(8));
				line.setMinBet(resultSet.getDouble(9));
				line.setMaxBet(resultSet.getDouble(10));
				line.setFixedResult(resultSet.getString(11));
				line.setScore1(resultSet.getInt(12));
				line.setScore2(resultSet.getInt(13));
			} 
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement, resultSet);
		}
		return line;
	}
	
	@Override
	public List<Line> getLineList() throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		Line line = null;
		Sport sport = null;
		Competition competition = null;
		List<Line> lineList = new ArrayList<Line>();
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_LINE_LIST);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				sport = new Sport();
				sport.setId(resultSet.getInt(2));
				sport.setName(resultSet.getString(14));
				sport.setNameRu(resultSet.getString(15));
				
				competition = new Competition();
				competition.setId(resultSet.getInt(3));
				competition.setName(resultSet.getString(16));
				competition.setNameRu(resultSet.getString(17));
				
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
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			if (prepareStatement == null)
				System.out.println("NULL");
			connectionPool.closeConnection(connection, prepareStatement, resultSet);
		}
		return lineList;
	}
	
	@Override
	public Sport getSport(int idSport) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		Sport sport = null;
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_SPORT_BY_ID);
			prepareStatement.setInt(1, idSport);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				sport = new Sport();
				sport.setId(resultSet.getInt(1));
				sport.setName(resultSet.getString(2));
				sport.setNameRu(resultSet.getString(3));
			} 
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement, resultSet);
		}
		return sport;
	}
	
	@Override
	public Competition getCompetition(int idCompetition) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		Competition competition = null;
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_COMPETITION_BY_ID);
			prepareStatement.setInt(1, idCompetition);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				competition = new Competition();
				competition.setId(resultSet.getInt(1));
				competition.setName(resultSet.getString(2));
				competition.setNameRu(resultSet.getString(3));
			} 
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement, resultSet);
		}
		return competition;
	}
	
	@Override
	public List<Line> getResultList() throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		Line line = null;
		Sport sport = null;
		Competition competition = null;
		List<Line> resultList = new ArrayList<Line>();
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_RESULT_LIST);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				sport = new Sport();
				sport.setId(resultSet.getInt(2));
				sport.setName(resultSet.getString(14));
				sport.setNameRu(resultSet.getString(15));
				
				competition = new Competition();
				competition.setId(resultSet.getInt(3));
				competition.setName(resultSet.getString(16));
				competition.setNameRu(resultSet.getString(17));
				
				line = new Line();
				line.setId(resultSet.getInt(1));
				line.setSport(sport);
				line.setCompetition(competition);
				line.setEventName(resultSet.getString(4));
				line.setStartDate(resultSet.getDate(5));
				line.setWinCoeff(resultSet.getDouble(6));
				line.setDrawCoeff(resultSet.getDouble(7));
				line.setLoseCoeff(resultSet.getDouble(8));
				line.setMinBet(resultSet.getDouble(9));
				line.setMaxBet(resultSet.getDouble(10));
				line.setFixedResult(resultSet.getString(11));
				line.setScore1(resultSet.getInt(12));
				line.setScore2(resultSet.getInt(13));
				resultList.add(line);
			} 
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(connection, prepareStatement, resultSet);
		}
		return resultList;
	}
	
	@Override
	public boolean makeBet(Bet bet) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			
			prepareStatement = connection.prepareStatement(REDUCE_USER_BALANCE);
			prepareStatement.setDouble(1, bet.getAmount());
			prepareStatement.setInt(2, bet.getUser().getId());
			prepareStatement.executeUpdate();
			prepareStatement.close();
			
			prepareStatement = connection.prepareStatement(INCREASE_BOOKMAKER_BALANCE);
			prepareStatement.setDouble(1, bet.getAmount());
			prepareStatement.executeUpdate();
			prepareStatement.close();

			prepareStatement = connection.prepareStatement(INSERT_BET);
			prepareStatement.setInt(1, bet.getUser().getId());
			prepareStatement.setInt(2, bet.getLine().getId());
			prepareStatement.setDouble(3, bet.getAmount());
			prepareStatement.setString(4, bet.getOutcome());
			prepareStatement.executeUpdate();
			
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
