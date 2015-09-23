package com.nowgroup.scspro.dao.hibernate.cat;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.cat.CompanyRoleDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.CompanyRole;

@Repository("companyRoleDAO")
public class CompanyRoleDAOHibernate extends BaseHibernateDAO<CompanyRole> implements CompanyRoleDAO {

    public CompanyRoleDAOHibernate() {
	super(CompanyRole.class);
    }
}