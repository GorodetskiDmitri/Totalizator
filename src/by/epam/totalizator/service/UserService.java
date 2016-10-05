package by.epam.totalizator.service;

import java.util.List;

import by.epam.totalizator.entity.Bet;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.service.exception.ServiceException;

public interface UserService {

	User checkLogin(String login, String password) throws ServiceException;
	User getUser(String login, int password) throws ServiceException;
	User registerUser(User user) throws ServiceException;
	boolean makeDeposit(String login, double summa) throws ServiceException;
	double getUnresolvedMoney(int idUser) throws ServiceException;
	List<Bet> getAllUserBet(int idUser) throws ServiceException;
	List<Line> getLineList() throws ServiceException;
	List<Line> getResultList() throws ServiceException;
	boolean makeBet(Bet bet) throws ServiceException;
}
