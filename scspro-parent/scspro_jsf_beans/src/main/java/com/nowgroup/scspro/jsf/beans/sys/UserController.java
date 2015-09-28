package com.nowgroup.scspro.jsf.beans.sys;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.nowgroup.scspro.dto.sys.User;
import com.nowgroup.scspro.jsf.beans.BaseFacesBean;
import com.nowgroup.scspro.model.sys.UserModel;
import com.nowgroup.scspro.service.sys.UserService;

@ManagedBean
@RequestScoped
public class UserController extends BaseFacesBean {
    private static final long serialVersionUID = 2278327556692461132L;

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
	    if ("".equals(model.getPassword()))
		throw new Exception("Password required!");
	    
	    User newUser = new User();
	    newUser.setName(model.getName());
	    newUser.setEmail(model.getEmail());
	    newUser.setPassword(model.getPassword());
	    userService.add(newUser);
	} catch (Exception e) {
	    publishError(registrationError);
	    return "";
	}
	// Add message
	publishInfo(registrationConfirmation);
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
