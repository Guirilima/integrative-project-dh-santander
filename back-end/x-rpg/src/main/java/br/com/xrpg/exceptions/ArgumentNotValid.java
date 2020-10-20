package br.com.xrpg.exceptions;

public class ArgumentNotValid extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ArgumentNotValid(String message, Throwable cause) {
		super(message, cause);
	}

	public ArgumentNotValid(String message) {
		super(message);
	}

}
