package com.fdmgroup.OnlineMarketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateUsernameException extends RuntimeException {

	public DuplicateUsernameException() {
		super();
	}

	public DuplicateUsernameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DuplicateUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateUsernameException(String message) {
		super(message);
	}

	public DuplicateUsernameException(Throwable cause) {
		super(cause);
	}

}
