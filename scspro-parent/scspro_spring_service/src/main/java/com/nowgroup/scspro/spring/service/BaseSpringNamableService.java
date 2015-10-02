package com.nowgroup.scspro.spring.service;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.hibernate.NamableHibernateDAO;
import com.nowgroup.scspro.dto.ItemByNameException;
import com.nowgroup.scspro.dto.NamableDTO;

public class BaseSpringNamableService<T extends NamableDTO> extends BaseSpringService<T> {
    private NamableHibernateDAO<T> daoFactory;

    public T getByName(String name)  throws ItemByNameException {
	return daoFactory.getByName(name);
    }
    
    @Override
    public BaseHibernateDAO<T> getDaoFactory() {
	return daoFactory;
    }

    public void setDaoFactory(NamableHibernateDAO<T> daoFactory) {
	super.setDaoFactory((BaseDAO<T>) daoFactory);
	this.daoFactory = daoFactory;
    }
    
}
