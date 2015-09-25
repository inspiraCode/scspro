package com.nowgroup.scspro.dao.hibernate.cat;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.cat.CompanyDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.Company;

@Repository("companyDAO")
public class CompanyDAOHibernate extends BaseHibernateDAO<Company> implements CompanyDAO {
    private static final Logger log = Logger.getLogger(CompanyDAOHibernate.class.getName());

    public CompanyDAOHibernate() {
	super(Company.class);
    }

    @SuppressWarnings("unchecked")
    public List<Company> getCompaniesByScope(String scope) {
	List<Integer> find = (List<Integer>) getHibernateTemplate().find(QUERY_BY_SCOPE, scope);
	List<Company> result = new LinkedList<Company>();
	for(int iCompany : find) {
	    log.debug("found " + scope + " company with id: " + iCompany);
	    Company company = get(iCompany);
	    result.add(company);
	} 
	return result;
    }
}
