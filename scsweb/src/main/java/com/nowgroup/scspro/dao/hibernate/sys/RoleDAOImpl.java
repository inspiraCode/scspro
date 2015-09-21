package com.nowgroup.scspro.dao.hibernate.sys;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.sys.RoleDAO;
import com.nowgroup.scspro.dto.sys.Role;

@Repository
public class RoleDAOImpl extends BaseHibernateDAO<Role> implements RoleDAO {
	public RoleDAOImpl() {
		super(Role.class);
	}
}
