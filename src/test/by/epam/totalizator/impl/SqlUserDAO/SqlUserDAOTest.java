package test.by.epam.totalizator.impl.SqlUserDAO;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.impl.SqlUserDAO;
import by.epam.totalizator.entity.User;

public class SqlUserDAOTest {

	@Test
	public void registerUserTest() throws DAOException {
		SqlUserDAO userDAO = new SqlUserDAO();
		
		User userExpected = new User();
		userExpected.setStatus("client");
		userExpected.setLogin("login");
		userExpected.setPassword("password".hashCode());
		userExpected.setBalance(10.0);
		userExpected.setName("name");
		userExpected.setSurname("surname");
		userExpected.setEmail("email");
		userExpected.setAddress("address");
		userExpected.setPhone("phone");
		userExpected.setPassport("passport");
		userExpected.setDateOfBirth(java.sql.Date.valueOf("1990-02-14"));
		userExpected.setBetAllow("0");
		
		userDAO.registerUser("login", "password", 10.0, "name", "surname", "email", 
				"address", "phone", "passport", java.sql.Date.valueOf("1990-02-14"));
		
		User userActual = userDAO.getUser("login", "password");
		assertEquals(userExpected, userActual);
	}
}
