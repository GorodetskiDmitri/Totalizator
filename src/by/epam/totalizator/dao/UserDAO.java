package by.epam.totalizator.dao;

import java.util.List;

import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.Bet;
import by.epam.totalizator.entity.Competition;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.Sport;
import by.epam.totalizator.entity.User;

public interface UserDAO {
	User getUser(String login, int password) throws DAOException;
	List<Bet> getAllUserBet(int idUser) throws DAOException;
	Line getLine(int idLine) throws DAOException;
	List<Line> getLineList() throws DAOException;
	Sport getSport(int idSport) throws DAOException;
	Competition getCompetition(int idCompetition) throws DAOException;
	boolean registerUser(User user) throws DAOException;
	boolean isLoginFree(String login) throws DAOException;
	boolean makeDeposit(String login, double summa) throws DAOException;
	double getUnresolvedMoney(int idUser) throws DAOException;
	List<Line> getResultList() throws DAOException;
	boolean makeBet(Bet bet) throws DAOException;
}
