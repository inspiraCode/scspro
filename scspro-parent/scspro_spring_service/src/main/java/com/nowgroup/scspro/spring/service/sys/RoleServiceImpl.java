package com.nowgroup.scspro.spring.service.sys;

import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.sys.RoleDAO;
import com.nowgroup.scspro.dto.sys.Role;
import com.nowgroup.scspro.service.sys.RoleService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

@Transactional
public class RoleServiceImpl extends BaseSpringService<Role> implements RoleService {
    private RoleDAO roleDAO;

    public RoleDAO getRoleDAO() {
	return roleDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
	super.setDaoFactory((BaseDAO<Role>) roleDAO);
	this.roleDAO = roleDAO;
    }

}
