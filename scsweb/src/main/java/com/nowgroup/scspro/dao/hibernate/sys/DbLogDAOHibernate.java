package com.nowgroup.scspro.dao.hibernate.sys;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.sys.DbLogDAO;
import com.nowgroup.scspro.dto.sys.DbLog;

@Repository("dbLogDAO")
public class DbLogDAOHibernate extends BaseHibernateDAO<DbLog> implements DbLogDAO {
    public DbLogDAOHibernate() {
	super(DbLog.class);
    }
}
