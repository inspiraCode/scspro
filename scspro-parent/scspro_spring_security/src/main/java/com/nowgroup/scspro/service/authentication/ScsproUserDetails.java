package com.nowgroup.scspro.service.authentication;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nowgroup.scspro.dto.sys.User;

public class ScsproUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ScsproUserDetails.class.getName());
    private User user;
    private List<GrantedAuthority> grantedAuthorities;

    public ScsproUserDetails(User user, List<GrantedAuthority> grantedAuthorities) {
	this.user = user;
	this.grantedAuthorities = grantedAuthorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
	return grantedAuthorities;
    }

    public String getPassword() {
	return user.getPassword();
    }

    public String getUsername() {
	return user.getName();
    }

    public boolean isAccountNonExpired() {
	Date today = new Date();
	boolean result = user.getExpirationDate().compareTo(today) > 0;
	log.debug("is account non expired? " + user.getExpirationDate() + " > " + today + "? : " + result);
	return result;
    }

    public boolean isAccountNonLocked() {
	log.debug("is non locked? " + user.isEnabled());
	return user.isEnabled();
    }

    public boolean isCredentialsNonExpired() {
	Date today = new Date();
	boolean result = user.getExpirationDate().compareTo(today) > 0;
	log.debug("is credentials non expired? " + user.getExpirationDate() + " > " + today + "? : " + result);
	return result;
    }

    public boolean isEnabled() {
	log.debug("is Enabled? " + user.isEnabled());
	return user.isEnabled();
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

}
