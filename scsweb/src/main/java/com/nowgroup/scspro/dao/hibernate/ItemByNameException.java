package com.nowgroup.scspro.dao.hibernate;

public class ItemByNameException extends Exception {
    private static final long serialVersionUID = 1125956658496970816L;

    public ItemByNameException() {
	// TODO Auto-generated constructor stub
    }

    public ItemByNameException(String message) {
	super(message);
    }

    public ItemByNameException(Throwable cause) {
	super(cause);
    }

    public ItemByNameException(String message, Throwable cause) {
	super(message, cause);
    }

    public ItemByNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
