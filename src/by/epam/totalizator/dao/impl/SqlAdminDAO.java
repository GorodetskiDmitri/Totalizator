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
import by.epam.totalizator.entity.Winner;

public class SqlAdminDAO extends SqlUserDAO implements AdminDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private static final String ALLOW_BET_FOR_USER = "UPDATE users SET bet_allow=? WHERE id=? AND status='client'";
	private static final String CHECK_WIN_BET = "UPDATE bet SET bet_status='3' WHERE outcome=? AND id_line=?";
	private static final String DEFAULT_LOSE_BET = "UPDATE bet SET bet_status='1' WHERE id_line=?";
	private static final String GET_COMPETITION_LIST = "SELECT * FROM competition ORDER BY name";
	private static final String GET_LAST_5_LINES = "SELECT a.*, s.name, s.name_ru, c.name, c.name_ru FROM line a INNER JOIN sport s ON a.id_sport = s.id INNER JOIN competition c ON a.id_competition=c.id ORDER BY a.id DESC LIMIT 5";
	private static final String GET_RESULT_LIST_FOR_FIX = "SELECT a.*, s.name, s.name_ru, c.name, c.name_ru FROM line a INNER JOIN sport s ON a.id_sport=s.id INNER JOIN competition c ON a.id_competition=c.id WHERE a.fixed_result='0' AND a.start_date <= NOW() ORDER BY a.start_date, s.name, c.name, a.event_name";
	private static final String GET_SPORT_LIST = "SELECT * FROM sport ORDER BY name";
	private static final String FIX_RESULT = "UPDATE line SET fixed_result='1', score1=?, score2=? WHERE id=?";
	private static final String INSERT_LINE = "INSERT INTO line(id_sport, id_competition, event_name, start_date, win_coeff, draw_coeff, lose_coeff, min_bet, max_bet, fixed_result, score1, score2) VALUES(?,?,?,?,?,?,?,?,?,'0',null,null)";
	private static final String PAYOUT = "UPDATE users SET balance = (balance + ((?)*(?))) WHERE id=?";
	private static final String REDUCE_BOOKMAKER_BALANCE = "UPDATE users SET balance = (balance - ((?)*(?))) WHERE status='bookmaker'";
	private static final String REMOVE_USER = "DELETE FROM users WHERE id=? AND status='client' AND balance=0.0";
	private static final String USER_LIST = "SELECT id, status, login, password, balance, name, sirname, email, address, phone, passport, date_of_birth, bet_allow FROM users WHERE status='client' ";
	private static final String WIN_BETS_LIST = "SELECT b.id_user, Sum(b.amount), a.win_coeff FROM bet b, line a WHERE b.id_line=a.id AND b.outcome=? AND b.bet_status='3' AND b.id_line=? GROUP BY b.id_user";
	
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
			StringBuilder query = new StringBuilder(); 
			query.append(USER_LIST);
			if (findCriteria != null && !findCriteria.equals("")) {
				query.append(" AND (login LIKE '%");
				query.append(findCriteria);
				query.append("%' OR name LIKE '%");
				query.append(findCriteria);
				query.append("%' OR sirname LIKE '%");
				query.append(findCriteria);
				query.append("%')");
			}
			query.append(" ORDER BY id DESC");
			resultSet = statement.executeQuery(query.toString());
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
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CloseConnection.close(connection, statement, resultSet);
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
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CloseConnection.close(connection, preparedStatement);
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
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CloseConnection.close(connection, preparedStatement);
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
			CloseConnection.close(connection, prepareStatement, resultSet);
		}
		return lineList;
	}
	
	@Override
	public void fixResult(Connection connection, int score1, int score2, int lineId) throws DAOException {
		PreparedStatement prepareStatement = null;
		
		try {
			prepareStatement = connection.prepareStatement(FIX_RESULT);
			prepareStatement.setInt(1, score1);
			prepareStatement.setInt(2, score2);
			prepareStatement.setInt(3, lineId);
			prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} 
	}
	
	@Override
	public void defaultLose(Connection connection, int lineId) throws DAOException {
		PreparedStatement prepareStatement = null;
		
		try {
			prepareStatement = connection.prepareStatement(DEFAULT_LOSE_BET);
			prepareStatement.setInt(1, lineId);
			prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} 
	}
	
	@Override
	public void checkWinBet(Connection connection, int lineId, int winOutcome) throws DAOException {
		PreparedStatement prepareStatement = null;
		
		try {
			prepareStatement = connection.prepareStatement(CHECK_WIN_BET);
			prepareStatement.setInt(1, winOutcome);
			prepareStatement.setInt(2, lineId);
			prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} 
	}
	
	@Override
	public List<Winner> getWinners(Connection connection, int lineId, int winOutcome) throws DAOException {
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		Winner winner = null;
		List<Winner> winnersList = new ArrayList<Winner>();
		try {
			prepareStatement = connection.prepareStatement(WIN_BETS_LIST);
			prepareStatement.setInt(1, winOutcome);
			prepareStatement.setInt(2, lineId);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("wery good");
				winner = new Winner();
				winner.setIdUser(resultSet.getInt(1));
				winner.setAmount(resultSet.getDouble(2));
				winner.setCoefficient(resultSet.getDouble(3));
				winnersList.add(winner);
			} 
			prepareStatement.close();
			resultSet.close();
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} 
		return winnersList;
	}
	
	@Override
	public void payout(Connection connection, List<Winner> winners) throws DAOException {
		PreparedStatement prepareStatement = null;
		
		try {
			prepareStatement = connection.prepareStatement(PAYOUT);
			for (Winner winner : winners) {
				prepareStatement.setDouble(1, winner.getAmount());
				prepareStatement.setDouble(2, winner.getCoefficient());
				prepareStatement.setInt(3, winner.getIdUser());
				prepareStatement.executeUpdate();
			}
			prepareStatement.close();
			
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} 
	}
	
	@Override
	public void payoutBookmaker(Connection connection, List<Winner> winners) throws DAOException {
		PreparedStatement prepareStatement = null;
		
		try {
			prepareStatement = connection.prepareStatement(REDUCE_BOOKMAKER_BALANCE);
			for (Winner winner : winners) {
				prepareStatement.setDouble(1, winner.getAmount());
				prepareStatement.setDouble(2, winner.getCoefficient());
				prepareStatement.executeUpdate();
			}
			prepareStatement.close();
			
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} 
	}
	
	@Override
	public List<Sport> getSportList() throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		Sport sport = null;
		List<Sport> sportList = new ArrayList<Sport>();
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_SPORT_LIST);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				sport = new Sport();
				sport.setId(resultSet.getInt(1));
				sport.setName(resultSet.getString(2));
				sport.setNameRu(resultSet.getString(3));
				sportList.add(sport);
			} 
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CloseConnection.close(connection, prepareStatement, resultSet);
		}
		return sportList;
	}
	
	@Override
	public List<Competition> getCompetitionList() throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		Competition competition = null;
		List<Competition> competitionList = new ArrayList<Competition>();
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_COMPETITION_LIST);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				competition = new Competition();
				competition.setId(resultSet.getInt(1));
				competition.setName(resultSet.getString(2));
				competition.setNameRu(resultSet.getString(3));
				competitionList.add(competition);
			} 
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CloseConnection.close(connection, prepareStatement, resultSet);
		}
		return competitionList;
	}
	
	@Override
	public boolean addEvent(Line line) throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(INSERT_LINE);
			prepareStatement.setInt(1, line.getSport().getId());
			prepareStatement.setInt(2, line.getCompetition().getId());
			prepareStatement.setString(3, line.getEventName());
			prepareStatement.setTimestamp(4, new java.sql.Timestamp(line.getStartDate().getTime()));
			prepareStatement.setDouble(5, line.getWinCoeff());
			prepareStatement.setDouble(6, line.getDrawCoeff());
			prepareStatement.setDouble(7, line.getLoseCoeff());
			prepareStatement.setDouble(8, line.getMinBet());
			prepareStatement.setDouble(9, line.getMaxBet());
			prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e)  {
			throw new DAOException(EXCEPTION_MESSAGE_SQL, e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			CloseConnection.close(connection, prepareStatement);
		}
		return true;
	}
	
	@Override
	public List<Line> getLast5Lines() throws DAOException {
		Connection connection = null;
		PreparedStatement prepareStatement = null; 
		ResultSet resultSet = null;
		Line line = null;
		Sport sport = null;
		Competition competition = null;
		List<Line> lineList = new ArrayList<Line>();
		try {
			connection = connectionPool.takeConnection();
			prepareStatement = connection.prepareStatement(GET_LAST_5_LINES);
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
			CloseConnection.close(connection, prepareStatement, resultSet);
		}
		return lineList;
	}
}
