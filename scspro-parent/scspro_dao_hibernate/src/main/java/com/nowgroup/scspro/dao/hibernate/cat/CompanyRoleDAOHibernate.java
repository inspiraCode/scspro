package com.nowgroup.scspro.dao.hibernate.cat;

import com.nowgroup.scspro.dao.cat.CompanyRoleDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.CompanyRole;

public class CompanyRoleDAOHibernate extends BaseHibernateDAO<CompanyRole> implements CompanyRoleDAO {

    public CompanyRoleDAOHibernate() {
	super(CompanyRole.class);
    }
}