package by.epam.totalizator.dao.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = -8762071598873112820L;

	public DAOException(String message, Exception e){
		super(message,e);
	}
	public DAOException(String message){
		super(message);
	}
	public DAOException(Exception e){
		super(e);
	}
}
