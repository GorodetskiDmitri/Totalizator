package by.epam.totalizator.dao;

import java.sql.Connection;
import java.util.List;

import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.Competition;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.Sport;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.entity.Winner;

public interface AdminDAO extends UserDAO {
	List<User> getUserList(String findCriteria) throws DAOException;
	boolean removeUser(int userId) throws DAOException;
	boolean allowBetForUser(int userId, String allowBet) throws DAOException;
	List<Line> getResultListForFix() throws DAOException;
	void fixResult(Connection connection, int score1, int score2, int lineId) throws DAOException;
	void defaultLose(Connection connection, int lineId) throws DAOException;
	void checkWinBet(Connection connection, int lineId, int winOutcome) throws DAOException;
	List<Winner> getWinners(Connection connection, int lineId, int winOutcome) throws DAOException;
	void payout (Connection connection, List<Winner> winners) throws DAOException;
	void payoutBookmaker (Connection connection, List<Winner> winners) throws DAOException;
	List<Sport> getSportList() throws DAOException;
	List<Competition> getCompetitionList() throws DAOException;
	boolean addEvent(Line line) throws DAOException;
	List<Line> getLast5Lines() throws DAOException;
	
}
