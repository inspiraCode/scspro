package com.nowgroup.scspro.jsf.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class PublisherFacesBean implements Serializable {
    private static final long serialVersionUID = 5759956158476516393L;

    protected void publishMessage(FacesMessage.Severity severity, String message, String details) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, details));
    }

    protected void publishError(String message) {
	publishMessage(FacesMessage.SEVERITY_ERROR, message, null);
    }

    protected void publishWarning(String message) {
	publishMessage(FacesMessage.SEVERITY_WARN, message, null);
    }

    protected void publishInfo(String message) {
	publishMessage(FacesMessage.SEVERITY_INFO, message, null);
    }
}
