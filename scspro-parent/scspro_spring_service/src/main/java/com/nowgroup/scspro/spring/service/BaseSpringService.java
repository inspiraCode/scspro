package com.nowgroup.scspro.spring.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dto.BaseDTO;
import com.nowgroup.scspro.service.BaseService;

public abstract class BaseSpringService<T extends BaseDTO> implements BaseService<T> {
    private static Logger log = Logger.getLogger(BaseSpringService.class.getName());
    private BaseDAO<T> daoFactory;

    public T get(int id) {
	return (T) daoFactory.get(id);
    }

    public List<T> getAll() {
	if (daoFactory == null) {
	    log.warn("DAO Factory is null, should force instantiation.");
	} else {
	    log.debug("DAO Factory evaluated: " + daoFactory);
	}
	List<T> result = daoFactory.getAll();
	return result != null ? (List<T>) result : null;
    }

    @Transactional(readOnly = false)
    public int add(T object) {
	return daoFactory.add(object);
    }

    @Transactional(readOnly = false)
    public void update(T object) {
	daoFactory.update(object);
    }

    @Transactional(readOnly = false)
    public void delete(T object) {
	daoFactory.delete(object);
    }

    public BaseDAO<T> getDaoFactory() {
	return daoFactory;
    }

    public void setDaoFactory(BaseDAO<T> daoFactory) {
	this.daoFactory = daoFactory;
    }
}
