package by.epam.totalizator.dao;

import java.sql.Connection;
import java.util.List;

import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.User;

public interface AdminDAO extends UserDAO {
	List<User> getUserList(String findCriteria) throws DAOException;
	boolean removeUser(int userId) throws DAOException;
	boolean allowBetForUser(int userId, String allowBet) throws DAOException;
	List<Line> getResultListForFix() throws DAOException;
	void fixResult(Connection connection, int score1, int score2, int lineId) throws DAOException;
}
