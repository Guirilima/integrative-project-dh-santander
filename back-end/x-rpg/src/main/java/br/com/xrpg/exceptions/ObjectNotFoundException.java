package br.com.xrpg.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String errorMsg) {
		super(errorMsg);
	}

	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
