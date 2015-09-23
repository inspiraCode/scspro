package com.nowgroup.scspro.model;

public class UserModel {
    private String name;
    private String password;
    private String confirmPassword;
    private String email;

    public void reset() {
	this.name = null;
	this.password = null;
	this.confirmPassword = null;
	this.email = null;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getConfirmPassword() {
	return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }
}
