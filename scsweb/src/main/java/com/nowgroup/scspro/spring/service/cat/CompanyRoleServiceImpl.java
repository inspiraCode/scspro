package com.nowgroup.scspro.spring.service.cat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.cat.CompanyRoleDAO;
import com.nowgroup.scspro.dto.cat.CompanyRole;
import com.nowgroup.scspro.service.cat.CompanyRoleService;

@Service
@Transactional(readOnly = true)
public class CompanyRoleServiceImpl implements CompanyRoleService {

	@Autowired
	private CompanyRoleDAO companyRoleDAO;

	public List<CompanyRole> getCompanyRoles() {
		return companyRoleDAO.getAll();
	}

	public CompanyRoleDAO getCompanyRoleDAO() {
		return companyRoleDAO;
	}

	public void setCompanyRoleDAO(CompanyRoleDAO companyRoleDAO) {
		this.companyRoleDAO = companyRoleDAO;
	}

}
