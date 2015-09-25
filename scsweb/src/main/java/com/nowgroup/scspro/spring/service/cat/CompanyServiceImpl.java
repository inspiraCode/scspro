package com.nowgroup.scspro.spring.service.cat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.cat.CompanyDAO;
import com.nowgroup.scspro.dao.cat.CompanyScopeDAO;
import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.service.cat.CompanyService;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {
    private static final Logger log = Logger.getLogger(CompanyServiceImpl.class.getName());

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private CompanyScopeDAO scopeDAO;

    /*** APPLYING METHOD LEVEL Spring Security ***/
    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void addCompany(Company company) {
	Set<CompanyScope> selectedRoles = new HashSet<CompanyScope>();
	selectedRoles.addAll(company.getCompanyScope());
	company.getCompanyScope().clear();

	getCompanyDAO().add(company);

	for (CompanyScope scope : selectedRoles) {
	    CompanyScope demodelized = new CompanyScope();
	    demodelized.setId(0);
	    demodelized.setCompanyRole(scope.getCompanyRole());
	    demodelized.setCompany(company);

	    scopeDAO.add(demodelized);
	}
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void deleteCompany(Company company) {
	getCompanyDAO().delete(company);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void deleteCompanies(List<Company> companies) {
	for (Company company : companies) {
	    getCompanyDAO().delete(company);
	}
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void updateCompany(Company company) {
	getCompanyDAO().update(company);
    }

    public Company getCompanyById(int id) {
	return getCompanyDAO().get(id);
    }

    public State getStateInCompanyId(int id) {
	State s = null;
	log.debug("Getting state from country id");
	Company c = getCompanyDAO().get(id);
	if (null != c) {
	    log.debug("Got company: " + c + " :: getting its state");
	    s = c.getState();
	    log.debug("Got company state: " + s);
	}

	return s;
    }

    public List<CompanyScope> getCompanyScope(int id) {
	List<CompanyScope> result = new ArrayList<CompanyScope>();
	log.debug("Getting the company scope configuration");
	Company c = getCompanyDAO().get(id);
	log.debug("Got company:" + c + " :: getting its scope configuration");
	if (c != null) {
	    Set<CompanyScope> scopeSet = c.getCompanyScope();
	    log.debug("got " + scopeSet.size() + " company scope definitions.");
	    result.addAll(scopeSet);
	}
	return result;
    }
    
    public List<Company> getCompaniesByScope(String scope) {
	return getCompanyDAO().getCompaniesByScope(scope);
    }

    public List<Company> getCompanies() {
	return getCompanyDAO().getAll();
    }

    public CompanyDAO getCompanyDAO() {
	return companyDAO;
    }

    public void setCompanyDAO(CompanyDAO companyDAO) {
	this.companyDAO = companyDAO;
    }

    public CompanyScopeDAO getScopeDAO() {
	return scopeDAO;
    }

    public void setScopeDAO(CompanyScopeDAO scopeDAO) {
	this.scopeDAO = scopeDAO;
    }
}
