package by.epam.totalizator.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.epam.totalizator.dao.connectionpool.ConnectionPool;
import by.epam.totalizator.dao.connectionpool.exception.ConnectionPoolException;

public class TotalizatorContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.dispose();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException();
		}
	}
	
}
