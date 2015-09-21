package com.nowgroup.scspro.spring.service.sys;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.hibernate.ItemByNameException;
import com.nowgroup.scspro.dao.sys.UserDAO;
import com.nowgroup.scspro.dto.sys.User;
import com.nowgroup.scspro.model.UserModel;
import com.nowgroup.scspro.service.sys.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public User getUser(String userName) throws ItemByNameException {
		return userDAO.getByName(userName);
	}
	
	public void register(UserModel model) {
		User user = new User();
		user.setName(model.getName());
		user.setEmail(model.getEmail());
		
		String hashedPassword = encoder.encode(model.getPassword());
		user.setPassword(hashedPassword);
		
		user.setEnabled(false);
		user.setLoginAttempts(0);
		
		// Expire in 1 week if not attended by admins to allow access.
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 7);
		
		user.setExpirationDate(c.getTime());
		userDAO.add(user);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public BCryptPasswordEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

}
