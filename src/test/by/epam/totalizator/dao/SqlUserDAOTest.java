package test.by.epam.totalizator.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.totalizator.dao.DAOFactory;
import by.epam.totalizator.dao.UserDAO;
import by.epam.totalizator.dao.connectionpool.ConnectionPool;
import by.epam.totalizator.dao.connectionpool.exception.ConnectionPoolException;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.entity.User;

public class SqlUserDAOTest {

	private static ConnectionPool connectionPool;
	private static DAOFactory factory;
	private static UserDAO userDAO;
	
	private String login = "mark";
	private int password = "markpassword".hashCode();
	
	@BeforeClass
	public static void init() {
		try {
			connectionPool = ConnectionPool.getInstance();
			connectionPool.initPoolData();
			factory = DAOFactory.getInstance();
			userDAO = factory.getUserDAO();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException("Error connecting to the data sourse", e);
		}
	}
	
	@Test
	public void registerUserTest() throws DAOException {
		User expectedUser = new User();
		expectedUser.setLogin(login);
		expectedUser.setPassword(password);
		expectedUser.setName("Mark");
		expectedUser.setSurname("Formelle");
		expectedUser.setEmail("formelle@gmail.com");
		expectedUser.setAddress("Minsk");
		expectedUser.setPhone("80295553360");
		expectedUser.setPassport("MP3902214");
		String dateOfBirthStr = "10.05.1993"; 
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		try {
			Date dateOfBirth = dateFormat.parse(dateOfBirthStr);
			expectedUser.setDateOfBirth(dateOfBirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		userDAO.registerUser(expectedUser);
		
		User actualUser = userDAO.getUser(login, password);
		
		assertTrue(expectedUser.getLogin().equals(actualUser.getLogin()));
		assertTrue(expectedUser.getPassword() == actualUser.getPassword());
		assertTrue(expectedUser.getName().equals(actualUser.getName()));
		assertTrue(expectedUser.getSurname().equals(actualUser.getSurname()));
		assertTrue(expectedUser.getEmail().equals(actualUser.getEmail()));
		assertTrue(expectedUser.getAddress().equals(actualUser.getAddress()));
		assertTrue(expectedUser.getPhone().equals(actualUser.getPhone()));
		assertTrue(expectedUser.getPassport().equals(actualUser.getPassport()));
		assertTrue(expectedUser.getDateOfBirth().equals(actualUser.getDateOfBirth()));
		
		assertFalse(userDAO.isLoginFree(login));
	}
	
	@AfterClass
	public static void close() {
		if (connectionPool != null) {
			connectionPool.dispose();
		}
	}
}
