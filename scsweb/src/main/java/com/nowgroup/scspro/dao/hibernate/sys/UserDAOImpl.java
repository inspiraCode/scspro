package com.nowgroup.scspro.dao.hibernate.sys;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.hibernate.NamableHibernateDAO;
import com.nowgroup.scspro.dao.sys.UserDAO;
import com.nowgroup.scspro.dto.sys.User;

@Repository
public class UserDAOImpl extends NamableHibernateDAO<User> implements UserDAO {

    public UserDAOImpl() {
	super(User.class);
    }

}
