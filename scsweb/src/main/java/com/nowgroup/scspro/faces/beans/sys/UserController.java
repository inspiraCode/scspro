package com.nowgroup.scspro.faces.beans.sys;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.nowgroup.scspro.model.UserModel;
import com.nowgroup.scspro.service.sys.UserService;

@ManagedBean
@RequestScoped
public class UserController {
    @ManagedProperty("#{userServiceImpl}")
    private UserService userService;

    @ManagedProperty(value = "#{i18n_register['register.fail']}")
    private String registrationError;
    @ManagedProperty(value = "#{i18n_register['register.succeed']}")
    private String registrationConfirmation;

    private UserModel model = new UserModel();

    public UserService getUserService() {
	return userService;
    }

    public void setUserService(UserService userService) {
	this.userService = userService;
    }

    public UserModel getModel() {
	return model;
    }

    public void setModel(UserModel model) {
	this.model = model;
    }

    public String register() {
	// Calling Business Service
	try {
	    if (!model.getPassword().equals(model.getConfirmPassword())) {
		throw new Exception("Passwords do not match!!");
	    }
	    userService.register(getModel());
	} catch (Exception e) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, registrationError, e.getMessage()));
	    return "";
	}
	// Add message
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(registrationConfirmation));
	return "reg_confirm";
    }

    public String getRegistrationError() {
	return registrationError;
    }

    public void setRegistrationError(String registrationError) {
	this.registrationError = registrationError;
    }

    public String getRegistrationConfirmation() {
	return registrationConfirmation;
    }

    public void setRegistrationConfirmation(String registrationConfirmation) {
	this.registrationConfirmation = registrationConfirmation;
    }

}
