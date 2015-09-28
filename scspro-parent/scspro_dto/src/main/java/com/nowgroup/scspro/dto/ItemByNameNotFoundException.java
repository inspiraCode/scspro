package com.nowgroup.scspro.dto;

public class ItemByNameNotFoundException extends ItemByNameException {
    private static final long serialVersionUID = 829737032127739602L;

    public ItemByNameNotFoundException() {
    }

    public ItemByNameNotFoundException(String message) {
	super(message);
    }

    public ItemByNameNotFoundException(Throwable cause) {
	super(cause);
    }

    public ItemByNameNotFoundException(String message, Throwable cause) {
	super(message, cause);
    }

    public ItemByNameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
