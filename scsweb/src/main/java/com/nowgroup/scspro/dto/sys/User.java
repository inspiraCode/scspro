package com.nowgroup.scspro.dto.sys;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.nowgroup.scspro.dto.BaseDTO;
import com.nowgroup.scspro.dto.NamableDTO;

@Entity
@Table(name = "sys_user", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(name = "USER_NAME_UNIQUE", columnNames = "USER_NAME") })
public class User implements BaseDTO, NamableDTO {
    private static final long serialVersionUID = -7993609198296829972L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true, nullable = false)
    private int id;

    @Column(name = "USER_NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_ENABLED")
    private boolean enabled;

    @Column(name = "LOGIN_ATTEMPTS")
    private int loginAttempts;

    @Column(name = "LAST_LOGIN")
    private Date lastLogin;

    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;

    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_user_role", catalog = "supply_chain", joinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) },
	    inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) })
    private Set<Role> roles;

    public User() {
    }

    public User(String name, String password, boolean enabled, int loginAttempts, Date lastLogin, Date expirationDate, String email) {
	this.name = name;
	this.password = password;
	this.enabled = enabled;
	this.loginAttempts = loginAttempts;
	this.lastLogin = lastLogin;
	this.expirationDate = expirationDate;
	this.email = email;
    }

    public User(String name, String password, boolean enabled, int loginAttempts, Date lastLogin, Date expirationDate, String email, Set<Role> roles) {
	this.name = name;
	this.password = password;
	this.enabled = enabled;
	this.loginAttempts = loginAttempts;
	this.lastLogin = lastLogin;
	this.expirationDate = expirationDate;
	this.email = email;
	this.roles = roles;
    }

    public User(String name, String password) {
	this.name = name;
	this.password = password;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
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

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    public int getLoginAttempts() {
	return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
	this.loginAttempts = loginAttempts;
    }

    public Date getLastLogin() {
	return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
	this.lastLogin = lastLogin;
    }

    public Date getExpirationDate() {
	return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
	this.expirationDate = expirationDate;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Set<Role> getRoles() {
	return roles;
    }

    public void setRoles(Set<Role> roles) {
	this.roles = roles;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("{userId:");
	sb.append(this.getId() + ", email:");
	sb.append(this.getEmail() + ", name:");
	sb.append(this.getName() + ", pass:");
	sb.append(this.getPassword() + ", attempts:");
	sb.append(this.getLoginAttempts() + ", isEnabled:");
	sb.append(this.isEnabled() + ", expiration:");
	sb.append(this.getExpirationDate() + ", lastLogin:");
	sb.append(this.getLastLogin() + ", ");
	sb.append("[");
	for (Role role : getRoles()) {
	    sb.append(role + ", ");
	}
	sb.append("{}]");
	sb.append("}");
	return sb.toString();
    }

}
