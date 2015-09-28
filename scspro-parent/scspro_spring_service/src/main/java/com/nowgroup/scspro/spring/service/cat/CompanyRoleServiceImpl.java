package com.nowgroup.scspro.spring.service.cat;

import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.cat.CompanyRoleDAO;
import com.nowgroup.scspro.dto.cat.CompanyRole;
import com.nowgroup.scspro.service.cat.CompanyRoleService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class CompanyRoleServiceImpl extends BaseSpringService<CompanyRole> implements CompanyRoleService {
    private CompanyRoleDAO companyRoleDAO;

    public CompanyRoleDAO getCompanyRoleDAO() {
	return companyRoleDAO;
    }

    public void setCompanyRoleDAO(CompanyRoleDAO companyRoleDAO) {
	super.setDaoFactory((BaseDAO<CompanyRole>) companyRoleDAO);
	this.companyRoleDAO = companyRoleDAO;
    }

}
