package com.nowgroup.scspro.spring.service.cat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.cat.CompanyScopeDAO;
import com.nowgroup.scspro.dto.cat.CompanyRole;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.service.cat.CompanyScopeService;

@Service
@Transactional(readOnly = true)
public class CompanyScopeServiceImpl implements CompanyScopeService {

    @Autowired
    private CompanyScopeDAO companyScopeDAO;

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void removeScope(CompanyScope scope) {
	companyScopeDAO.delete(scope);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public int addScope(CompanyScope scope) {
	companyScopeDAO.add(scope);
	return scope.getId();
    }

    public CompanyScopeDAO getCompanyScopeDAO() {
	return companyScopeDAO;
    }

    public void setCompanyScopeDAO(CompanyScopeDAO companyScopeDAO) {
	this.companyScopeDAO = companyScopeDAO;
    }

    public CompanyRole getRoleInCompanyScope(int id) {
	CompanyScope dbScope = this.companyScopeDAO.get(id);
	CompanyRole result = dbScope.getCompanyRole();
	result.setId(dbScope.getCompanyRole().getId());
	result.setName(dbScope.getCompanyRole().getName());
	return result;
    }

}
