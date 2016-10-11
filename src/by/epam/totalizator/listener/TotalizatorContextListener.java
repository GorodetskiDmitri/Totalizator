package by.epam.totalizator.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import by.epam.totalizator.command.impl.ShowBetHistoryCommand;
import by.epam.totalizator.dao.connectionpool.ConnectionPool;
import by.epam.totalizator.dao.connectionpool.exception.ConnectionPoolException;

public class TotalizatorContextListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(TotalizatorContextListener.class);
	
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
			logger.error("Could not initialize connection pool");
			throw new RuntimeException();
		}
	}
	
}
