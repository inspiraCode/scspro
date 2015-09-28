package com.nowgroup.scspro.spring.service.sys;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.hibernate.sys.StorageDAOHibernate;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.dto.sys.Storage;
import com.nowgroup.scspro.service.sys.StorageService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class StorageServiceImpl extends BaseSpringService<Storage> implements StorageService {
    private static final Logger log = Logger.getLogger(StorageServiceImpl.class.getName());

    private StorageDAOHibernate storageDAO;

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public int add(Storage storage) {
	return super.add(storage);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public void delete(Storage storage) {
	getStorageDAO().delete(storage);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public void update(Storage storage) {
	getStorageDAO().update(storage);
    }

    public State getStateInStorage(int id) {
	Storage theStorage = getStorageDAO().get(id);
	State theState = theStorage.getState();
	log.debug("Got storage state: " + theState);
	return theState;
    }

    public StorageDAOHibernate getStorageDAO() {
	return storageDAO;
    }

    public void setStorageDAO(StorageDAOHibernate storageDAO) {
	super.setDaoFactory((BaseDAO<Storage>) storageDAO);
	this.storageDAO = storageDAO;
    }

}
