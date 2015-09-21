package com.nowgroup.scspro.dao.hibernate.cat;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.cat.CompanyScopeDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.CompanyScope;

@Repository("companyScopeDAO")
public class CompanyScopeDAOHibernate extends BaseHibernateDAO<CompanyScope>
		implements CompanyScopeDAO {

	public CompanyScopeDAOHibernate() {
		super(CompanyScope.class);
	}

}
