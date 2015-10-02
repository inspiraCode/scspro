package com.nowgroup.scspro.jsf.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean(name = "loginMgmtBean")
@RequestScoped
public class LoginBean extends PublisherFacesBean {
    private static final long serialVersionUID = 1201695543385512094L;
    private static final Logger logger = Logger.getLogger(LoginBean.class.getName());
    private String userName = null;
    private String password = null;

    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

    @ManagedProperty(value = "#{i18n_login['login.fail']}")
    private String loginError;

    /**
     * Evaluate given username and password.
     * 
     * @return management bean resolution. Resolved to faces-config.xml
     *         navigation rule for login.xhtml
     */
    public String login() {
	try {
	    Authentication request = new UsernamePasswordAuthenticationToken(this.getUserName(), this.getPassword());
	    Authentication result = authenticationManager.authenticate(request);
	    SecurityContextHolder.getContext().setAuthentication(result);
	} catch (AuthenticationException e) {
	    logger.error(e.getMessage(), e);
	    publishError(getLoginError());
	    publishError(e.getLocalizedMessage());
	    return "incorrect";
	}

	return "correct";
    }

    public String cancel() {
	return null;
    }

    public String logout() {
	SecurityContextHolder.clearContext();
	return "loggedout";
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public AuthenticationManager getAuthenticationManager() {
	return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	this.authenticationManager = authenticationManager;
    }

    public String getLoginError() {
	return loginError;
    }

    public void setLoginError(String loginError) {
	this.loginError = loginError;
    }

}
