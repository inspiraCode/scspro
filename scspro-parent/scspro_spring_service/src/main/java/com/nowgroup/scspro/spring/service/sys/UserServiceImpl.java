package com.nowgroup.scspro.spring.service.sys;

import java.util.Calendar;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.sys.UserDAO;
import com.nowgroup.scspro.dto.sys.User;
import com.nowgroup.scspro.service.sys.UserService;
import com.nowgroup.scspro.spring.service.BaseSpringNamableService;

@Transactional
public class UserServiceImpl extends BaseSpringNamableService<User> implements UserService {
    private UserDAO userDAO;
    private BCryptPasswordEncoder encoder;

    @Override
    public int add(User model) {
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
	return super.add(user);
    }

    public UserDAO getUserDAO() {
	return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
	super.setDaoFactory((BaseDAO<User>) userDAO);
	this.userDAO = userDAO;
    }

    public BCryptPasswordEncoder getEncoder() {
	return encoder;
    }

    public void setEncoder(BCryptPasswordEncoder encoder) {
	this.encoder = encoder;
    }

}
