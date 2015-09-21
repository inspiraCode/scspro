package com.nowgroup.scspro.dao.hibernate.cat;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.cat.CompanyDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.Company;

@Repository("companyDAO")
public class CompanyDAOHibernate extends BaseHibernateDAO<Company> implements
		CompanyDAO {

	public CompanyDAOHibernate() {
		super(Company.class);
	}
}
