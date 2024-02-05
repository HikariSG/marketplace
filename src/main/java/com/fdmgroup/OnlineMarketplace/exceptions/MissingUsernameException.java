package com.fdmgroup.OnlineMarketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingUsernameException extends RuntimeException{

	public MissingUsernameException() {
		super();
	}

	public MissingUsernameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MissingUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissingUsernameException(String message) {
		super(message);
	}

	public MissingUsernameException(Throwable cause) {
		super(cause);
	}
	
}
