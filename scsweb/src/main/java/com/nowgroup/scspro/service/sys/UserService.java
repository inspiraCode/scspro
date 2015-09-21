package com.nowgroup.scspro.service.sys;

import com.nowgroup.scspro.dao.hibernate.ItemByNameException;
import com.nowgroup.scspro.dto.sys.User;
import com.nowgroup.scspro.model.UserModel;

public interface UserService {
	/**
	 * Retrieve a user record from database using the user name field. User name
	 * field is a unique index in database.
	 * 
	 * @param userName
	 * @return
	 * @throws ItemByNameException
	 */
	User getUser(String userName) throws ItemByNameException;

	/**
	 * Register user in database. User activation is done by administrator.
	 * 
	 * @param model
	 */
	void register(UserModel model);
}
