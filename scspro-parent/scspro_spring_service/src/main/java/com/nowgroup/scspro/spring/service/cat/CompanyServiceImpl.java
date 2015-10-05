package com.nowgroup.scspro.spring.service.cat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.cat.CompanyDAO;
import com.nowgroup.scspro.dao.cat.CompanyScopeDAO;
import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.service.cat.CompanyService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class CompanyServiceImpl extends BaseSpringService<Company> implements CompanyService {
    private static final Logger log = Logger.getLogger(CompanyServiceImpl.class.getName());
    private CompanyDAO companyDAO;
    private CompanyScopeDAO scopeDAO;

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public int add(Company company) {
	Set<CompanyScope> selectedRoles = new HashSet<CompanyScope>();
	selectedRoles.addAll(company.getCompanyScope());
	company.getCompanyScope().clear();

	int result = super.add(company);

	for (CompanyScope scope : selectedRoles) {
	    CompanyScope demodelized = new CompanyScope();
	    demodelized.setId(0);
	    demodelized.setCompanyRole(scope.getCompanyRole());
	    demodelized.setCompany(company);

	    scopeDAO.add(demodelized);
	}
	return result;
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public void delete(Company company) {
	super.delete(company);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public void update(Company company) {
	log.debug("UPDATE Company: " + company);
	log.debug("UPDATE Company with state: " + company.getState());
	log.debug("UPDATE Company with scope: " + company.getCompanyScope());
	super.update(company);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void deleteCompanies(List<Company> companies) {
	for (Company company : companies) {
	    getCompanyDAO().delete(company);
	}
    }

    public int getStateIdInCompanyId(int id) {
	Company c = getCompanyDAO().get(id);
	int result = 0;
	try {
	    if (null != c && null != c.getState()) {
		result = c.getState().getId();
	    }
	} catch (Exception e) {
	    log.warn(e.getMessage());
	}
	return result;
    }

    public List<CompanyScope> getCompanyScope(int id) {
	List<CompanyScope> result = new ArrayList<CompanyScope>();
	Company c = getCompanyDAO().get(id);
	if (c != null) {
	    Set<CompanyScope> scopeSet = c.getCompanyScope();
	    result.addAll(scopeSet);
	}
	return result;
    }

    public List<Company> getCompaniesByScope(String scope) {
	return getCompanyDAO().getCompaniesByScope(scope);
    }

    public CompanyDAO getCompanyDAO() {
	return companyDAO;
    }

    @Required
    public void setCompanyDAO(CompanyDAO companyDAO) {
	log.debug("Setting up CompanyDAO");
	super.setDaoFactory((BaseDAO<Company>) companyDAO);
	this.companyDAO = companyDAO;
    }

    public CompanyScopeDAO getScopeDAO() {
	return scopeDAO;
    }

    public void setScopeDAO(CompanyScopeDAO scopeDAO) {
	this.scopeDAO = scopeDAO;
    }
}
