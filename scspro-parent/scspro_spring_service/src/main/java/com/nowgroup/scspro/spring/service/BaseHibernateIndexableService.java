package com.nowgroup.scspro.spring.service;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.BaseDTO;

public abstract class BaseHibernateIndexableService<T extends BaseDTO> extends BaseSpringService<T> {
    private BaseHibernateDAO<T> daoFactory;

    public void indexEntity(T object) {
	daoFactory.indexEntity(object);
    }
    
    @Override
    public BaseHibernateDAO<T> getDaoFactory() {
	return daoFactory;
    }

    public void setDaoFactory(BaseHibernateDAO<T> daoFactory) {
	super.setDaoFactory((BaseDAO<T>) daoFactory);
	this.daoFactory = daoFactory;
    }
}
