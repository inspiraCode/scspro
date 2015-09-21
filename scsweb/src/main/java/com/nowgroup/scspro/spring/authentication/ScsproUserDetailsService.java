package com.nowgroup.scspro.spring.authentication;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.authentication.ScsproUserDetails;
import com.nowgroup.scspro.dao.hibernate.ItemByNameException;
import com.nowgroup.scspro.dao.sys.UserDAO;
import com.nowgroup.scspro.dto.sys.Role;
import com.nowgroup.scspro.dto.sys.User;

@Service
@Transactional(readOnly = true)
public class ScsproUserDetailsService implements UserDetailsService {
	private static final Logger logger = Logger
			.getLogger(ScsproUserDetailsService.class.getName());

	@Autowired
	private UserDAO userDAO;

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		logger.info(String.format(
				"ScsproUserDetailsService.loadUserByUsername() = %s", userName));
		User user = null;
		try {
			user = userDAO.getByName(userName);
		} catch (ItemByNameException e) {
			logger.error(e.getMessage(), e);
		}
		if (user != null) {
			logger.info(String.format(
					"ScsproUserDetailsService.loadUserByUsername() = %s", user));
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

			for (Role role : user.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role
						.getName()));
				logger.info("role found: " + role.getName());
			}

			UserDetails userDetails = new ScsproUserDetails(user,
					grantedAuthorities);
			return userDetails;
		} else
			throw new UsernameNotFoundException(
					"User not registered in database.");
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
