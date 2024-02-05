package com.fdmgroup.OnlineMarketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateEmailException extends RuntimeException{

	public DuplicateEmailException() {
		super();
	}

	public DuplicateEmailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DuplicateEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateEmailException(String message) {
		super(message);
	}

	public DuplicateEmailException(Throwable cause) {
		super(cause);
	}
}
