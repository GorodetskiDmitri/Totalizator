package by.epam.totalizator.dao.connectionpool.exception;

public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = -2858407540246168658L;

	public ConnectionPoolException(String message) {
		super(message);
	}
	
	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}
}
