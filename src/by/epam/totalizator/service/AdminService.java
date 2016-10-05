package by.epam.totalizator.service;

import java.util.List;

import by.epam.totalizator.entity.Competition;
import by.epam.totalizator.entity.Line;
import by.epam.totalizator.entity.Sport;
import by.epam.totalizator.entity.User;
import by.epam.totalizator.service.exception.ServiceException;

public interface AdminService {

	List<User> getUserList(String findCriteria) throws ServiceException;
	boolean removeUser(int userId) throws ServiceException;
	boolean allowBetForUser(int userId, String allowBet) throws ServiceException;
	List<Line> getResultListForFix() throws ServiceException;
	boolean fixResult(int score1, int score2, int lineId) throws ServiceException;
	List<Sport> getSportList() throws ServiceException;
	List<Competition> getCompetitionList() throws ServiceException;
	boolean addEvent(Line line) throws ServiceException;
	List<Line> getLast5Lines() throws ServiceException;
}
