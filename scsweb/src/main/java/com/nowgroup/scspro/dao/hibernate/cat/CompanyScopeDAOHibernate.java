package com.nowgroup.scspro.dao.hibernate.cat;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.cat.CompanyScopeDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.CompanyScope;

@Repository("companyScopeDAO")
public class CompanyScopeDAOHibernate extends BaseHibernateDAO<CompanyScope> implements CompanyScopeDAO {
    private static final Logger log = Logger.getLogger(CompanyScopeDAOHibernate.class.getName());

    public CompanyScopeDAOHibernate() {
	super(CompanyScope.class);
    }

    @SuppressWarnings("unchecked")
    public CompanyScope getByCompanyAndRole(int companyId, String scopeName) {
	String[] parameterNames = { "scopeName", "companyId" };
	Object[] parameterValues = { scopeName, companyId };
	List<Integer> find = (List<Integer>) getHibernateTemplate().findByNamedParam(QUERY_SCOPE_BY_COMPANY_AND_NAME, parameterNames, parameterValues);
	log.debug(find.size() + " results in database");
	CompanyScope result = null;
	if (!find.isEmpty()) {
	    result = get(find.get(0));
	}

	return result;
    }

}
