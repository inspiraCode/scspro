package com.nowgroup.scspro.spring.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.sys.RoleDAO;
import com.nowgroup.scspro.dto.sys.Role;
import com.nowgroup.scspro.service.sys.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;

    public Role getRole(int id) {
	return roleDAO.get(id);
    }

    public RoleDAO getRoleDAO() {
	return roleDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
	this.roleDAO = roleDAO;
    }

}
