package es.ivandrr.microservice.user.exceptions;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = -9092477452692118078L;

	public UserException(String errorMessage) {
		super(errorMessage);
	}

}
