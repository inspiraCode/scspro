package com.nowgroup.scspro.dao.hibernate.cat;

import java.util.LinkedList;
import java.util.List;

import com.nowgroup.scspro.dao.cat.CompanyDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.Company;

public class CompanyDAOHibernate extends BaseHibernateDAO<Company> implements CompanyDAO {
    //private static final Logger log = Logger.getLogger(CompanyDAOHibernate.class.getName());

    public CompanyDAOHibernate() {
	super(Company.class);
    }

    @SuppressWarnings("unchecked")
    public List<Company> getCompaniesByScope(String scope) {
	List<Integer> find = (List<Integer>) getHibernateTemplate().find(QUERY_BY_SCOPE, scope);
	List<Company> result = new LinkedList<Company>();
	for(int iCompany : find) {
	    Company company = get(iCompany);
	    result.add(company);
	} 
	return result;
    }
}
