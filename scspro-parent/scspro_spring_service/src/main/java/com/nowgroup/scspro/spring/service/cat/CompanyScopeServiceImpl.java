package com.nowgroup.scspro.spring.service.cat;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.cat.CompanyScopeDAO;
import com.nowgroup.scspro.dto.cat.CompanyRole;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.service.cat.CompanyScopeService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class CompanyScopeServiceImpl extends BaseSpringService<CompanyScope> implements CompanyScopeService {
    private CompanyScopeDAO companyScopeDAO;

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public void delete(CompanyScope scope){
	super.delete(scope);
    }
    
    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public int add(CompanyScope scope){
	return super.add(scope);
    }

    public CompanyScopeDAO getCompanyScopeDAO() {
	return companyScopeDAO;
    }

    public void setCompanyScopeDAO(CompanyScopeDAO companyScopeDAO) {
	super.setDaoFactory((BaseDAO<CompanyScope>) companyScopeDAO);
	this.companyScopeDAO = companyScopeDAO;
    }

    public CompanyRole getRoleInCompanyScope(int id) {
	CompanyScope dbScope = this.companyScopeDAO.get(id);
	CompanyRole result = dbScope.getCompanyRole();
	result.setId(dbScope.getCompanyRole().getId());
	result.setName(dbScope.getCompanyRole().getName());
	return result;
    }

    public CompanyScope getByCompanyAndRole(int companyId, String scopeName) {
	CompanyScope dbScope = companyScopeDAO.getByCompanyAndRole(companyId, scopeName);
	return dbScope;
    }

}
