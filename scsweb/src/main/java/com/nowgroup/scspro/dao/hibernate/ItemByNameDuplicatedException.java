package com.nowgroup.scspro.dao.hibernate;

public class ItemByNameDuplicatedException extends ItemByNameException {
	private static final long serialVersionUID = 5836414545443866306L;

	public ItemByNameDuplicatedException() {
	}

	public ItemByNameDuplicatedException(String message) {
		super(message);
	}

	public ItemByNameDuplicatedException(Throwable cause) {
		super(cause);
	}

	public ItemByNameDuplicatedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemByNameDuplicatedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
