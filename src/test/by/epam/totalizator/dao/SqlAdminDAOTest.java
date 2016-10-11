package test.by.epam.totalizator.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.totalizator.dao.AdminDAO;
import by.epam.totalizator.dao.DAOFactory;
import by.epam.totalizator.dao.connectionpool.ConnectionPool;
import by.epam.totalizator.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.User;

public class SqlAdminDAOTest {

	private static ConnectionPool connectionPool;
	private static DAOFactory factory;
	private static AdminDAO adminDAO;
	
	private String login = "mark";
	private int password = "markpassword".hashCode();
	
	@BeforeClass
	public static void init() {
		try {
			connectionPool = ConnectionPool.getInstance();
			connectionPool.initPoolData();
			factory = DAOFactory.getInstance();
			adminDAO = factory.getAdminDAO();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException("Error connecting to the data sourse", e);
		}
	}
	
	@Test
	public void allowBetForUserTest() throws DAOException {
		User testingUser = adminDAO.getUser(login, password);
		String forbidBet = "0";
		String allowBet = "1";
		int userId;
		
		if (testingUser != null) {
			userId = testingUser.getId();
			
			adminDAO.allowBetForUser(userId, allowBet);
			User actualUser = adminDAO.getUser(login, password);
			assertTrue(allowBet.equals(actualUser.getBetAllow()));
			
			adminDAO.allowBetForUser(userId, forbidBet);
			actualUser = adminDAO.getUser(login, password);
			assertFalse(allowBet.equals(actualUser.getBetAllow()));
		} else {
			fail("AllowBetForUser: Not exist user for testing");
		}
	}
	
	@Test
	public void removeUserTest() throws DAOException {
		User beforeUser = adminDAO.getUser(login, password);
		if (beforeUser != null) {
			adminDAO.removeUser(beforeUser.getId());
			User actualUser = adminDAO.getUser(login, password);
			assertNull(actualUser);
		} else {
			fail("RemoveUser: Not exist user for testing");
		}
	}
	
	@AfterClass
	public static void close() {
		if (connectionPool != null) {
			connectionPool.dispose();
		}
	}
}
