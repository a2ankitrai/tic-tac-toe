package com.ank.tictactoe;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class GameException extends RuntimeException {

	private static final long serialVersionUID = 5861310537366287163L;
	private HttpStatus httpStatus;

	public GameException() {
		super();
	}

	public GameException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public GameException(final String message, final HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public GameException(final String message) {
		super(message);
	}

	public GameException(final Throwable cause) {
		super(cause);
	}
}
