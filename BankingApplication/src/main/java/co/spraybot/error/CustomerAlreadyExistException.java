package co.spraybot.error;

public class CustomerAlreadyExistException extends RuntimeException{
	private static final long serialVersionUID = 5861310537366287163L;
	
	public CustomerAlreadyExistException() {
		super();
	}
	
	public CustomerAlreadyExistException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	public CustomerAlreadyExistException(final String message) {
		super(message);
	}
	
	public CustomerAlreadyExistException(final Throwable cause) {
		super(cause);
	}
}
