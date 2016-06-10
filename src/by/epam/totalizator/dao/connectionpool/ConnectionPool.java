package by.epam.totalizator.dao.connectionpool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

import org.apache.log4j.Logger;

import by.epam.totalizator.dao.connectionpool.exception.ConnectionPoolException;

/**
 * This class implements a pattern singleton. 
 * This class creates, handles and gives connections to database.
 */
public final class ConnectionPool {
	
	private static final Logger LOG = Logger.getLogger(ConnectionPool.class);

	private BlockingQueue<Connection> connectionQueue;
	private BlockingQueue<Connection> givenAwayConQueue;
	
	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	
	/**
	 * The constructor initializes the fields of the class Database Settings.
	 */
	private ConnectionPool() {
		DBResourseManager dbResourseManager = DBResourseManager.getInstance();
		this.driverName = dbResourseManager.getValue(DBParameter.DB_DRIVER);
		this.url = dbResourseManager.getValue(DBParameter.DB_URL);
		this.user = dbResourseManager.getValue(DBParameter.DB_USER);
		this.password = dbResourseManager.getValue(DBParameter.DB_PASSWORD);
		try {
			this.poolSize = Integer.parseInt(dbResourseManager.getValue(DBParameter.DB_POOL_SIZE));
		} catch (NumberFormatException e) {
			poolSize = 10;
			LOG.info("Invalid number format", e);
		}
	}
	
	/**
	 * The inner class for implementation singleton. 
	 * It holds ConnectionPool instance.
	 */
	private static class Holder {
		private static final ConnectionPool INSTANCE = new ConnectionPool();
	}
	
	/**
	 * The method gives ConnectionPool singleton instance.
	 */
	public static synchronized ConnectionPool getInstance() {
		return Holder.INSTANCE;
	}
	
	/**
	 * Directly creates a connection pool
	 */
	public void initPoolData() throws ConnectionPoolException {
		Locale.setDefault(Locale.ENGLISH);
		try {
			Class.forName(driverName);
			connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
			givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				PooledConnection pooledConnection = new PooledConnection(connection);
				connectionQueue.add(pooledConnection);					
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in ConnectionPool", e);
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("Can't find database driver class", e);
		}
		
	}
	
	/**
	 * The method closes all connections in two BlockingQueue.
	 */
	public void dispose() {
		clearConnectionQueue();
	}
	
	/**
	 * The method closes all connections in two BlockingQueue.
	 */
	private void clearConnectionQueue() {
		try {
			closeConnectionsQueue(connectionQueue);
			closeConnectionsQueue(givenAwayConQueue);
		} catch (SQLException e) {
			LOG.error("Error closing the connection.", e);
		}
	}
		
	/**
	 * The method take Connection from BlockingQueue.
	 * 
	 * @return Connection
	 */
	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = connectionQueue.take();
			givenAwayConQueue.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Error connecting to the data sourse", e);
		}
		return connection;
	}
	
	/**
	 * The method closes the connection, statement and resultSet.
	 * 
	 * @param Connection connection
	 * @param Statement statement
	 * @param ResultSet resultSet
	 * @exception SQLException
	 */
	public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			LOG.error("ResultSet isn't closed.", e);
		}
		try {
			statement.close();
		} catch (SQLException e) {
			LOG.error("Statement isn't closed.", e);
		}
		try {
			connection.close();
		} catch (SQLException e) {
			LOG.error("Connection isn't return to the pool.", e);
		}
	}
	
	/**
	 * The method closes the connection and statement.
	 * 
	 * @param Connection connection
	 * @param Statement statement
	 * @exception SQLException
	 */
	public void closeConnection(Connection connection, Statement statement){
		try {
			statement.close();
		} catch (SQLException e) {
			LOG.error("Statement isn't closed.", e);
		}
		try {
			connection.close();
		} catch (SQLException e) {
			LOG.error("Connection isn't return to the pool.", e);
		}
	}
	
	/**
	 * The method closes the connections in BlockingQueue.
	 * 
	 * @param BlockingQueue<Connection> queue
	 * @exception SQLException
	 */
	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			((PooledConnection) connection).reallyClose();
		}
	}
	
	
	
	/**
	 * Class wrapper for Connection.
	 */
	private class PooledConnection implements Connection {

		private Connection connection;
		
		public PooledConnection(Connection connection) throws SQLException {
			this.connection = connection;
			this.connection.setAutoCommit(true);
		}
		
		/**
		 * The method closes the connection.
		 */
		public void reallyClose() throws SQLException {
			connection.close();
		}
		
		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return connection.isWrapperFor(iface);
		}

		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			return connection.unwrap(iface);
		}

		@Override
		public void abort(Executor executor) throws SQLException {
			connection.abort(executor);
		}

		@Override
		public void clearWarnings() throws SQLException {
			connection.clearWarnings();
		}

		@Override
		public void close() throws SQLException {
			if (connection.isClosed()) {
				throw new SQLException("Attemping to close closed connection");
			}
			if (connection.isReadOnly()) {
				connection.setReadOnly(false);
			}
			if (!connection.getAutoCommit()) {
				connection.setAutoCommit(true);
			}
			if (!givenAwayConQueue.remove(this)) {
				throw new SQLException("Error deleting connection from the given away connections pool.");
			}
			if (!connectionQueue.offer(this)) {
				throw new SQLException("Error allocation connection in the pool.");
			}
		}

		@Override
		public void commit() throws SQLException {
			connection.commit();
		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			return connection.createArrayOf(typeName, elements);
		}

		@Override
		public Blob createBlob() throws SQLException {
			return connection.createBlob();
		}

		public Clob createClob() throws SQLException {
			return connection.createClob();
		}

		@Override
		public NClob createNClob() throws SQLException {
			return connection.createNClob();
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			return connection.createSQLXML();
		}
		
		@Override
		public Statement createStatement() throws SQLException {
			return connection.createStatement();
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency);
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			return connection.createStruct(typeName, attributes);
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			return connection.getAutoCommit();
		}

		@Override
		public String getCatalog() throws SQLException {
			return connection.getCatalog();
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			return connection.getClientInfo();
		}

		@Override
		public String getClientInfo(String name) throws SQLException {
			return connection.getClientInfo(name);
		}

		@Override
		public int getHoldability() throws SQLException {
			return connection.getHoldability();
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			return connection.getMetaData();
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			return connection.getNetworkTimeout();
		}

		@Override
		public String getSchema() throws SQLException {
			return connection.getSchema();
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			return connection.getTransactionIsolation();
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			return connection.getTypeMap();
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			return connection.getWarnings();
		}

		@Override
		public boolean isClosed() throws SQLException {
			return connection.isClosed();
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			return connection.isReadOnly();
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {
			return connection.isValid(timeout);
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {
			return connection.nativeSQL(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {
			return connection.prepareCall(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql) throws SQLException {
			return connection.prepareStatement(sql);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			return connection.prepareStatement(sql, autoGeneratedKeys);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			return connection.prepareStatement(sql, columnIndexes);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			return connection.prepareStatement(sql, columnNames);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			connection.releaseSavepoint(savepoint);
		}

		@Override
		public void rollback() throws SQLException {
			connection.rollback();
		}

		@Override
		public void rollback(Savepoint savepoint) throws SQLException {
			connection.rollback(savepoint);
		}

		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException {
			connection.setAutoCommit(autoCommit);
		}

		@Override
		public void setCatalog(String catalog) throws SQLException {
			connection.setCatalog(catalog);
		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			connection.setClientInfo(properties);
		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			connection.setClientInfo(name, value);
		}

		@Override
		public void setHoldability(int holdability) throws SQLException {
			connection.setHoldability(holdability);
		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			connection.setNetworkTimeout(executor, milliseconds);
		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			connection.setReadOnly(readOnly);
		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			return connection.setSavepoint();
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {
			return connection.setSavepoint(name);
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			connection.setSchema(schema);
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			connection.setTransactionIsolation(level);
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			connection.setTypeMap(map);
		}
	}

}	
