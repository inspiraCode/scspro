package com.nowgroup.scspro.spring.service.sys;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.hibernate.sys.StorageDAOHibernate;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.dto.sys.Storage;
import com.nowgroup.scspro.service.sys.StorageService;

@Service
@Transactional(readOnly = true)
public class StorageServiceImpl implements StorageService {
	private static final Logger log = Logger.getLogger(StorageServiceImpl.class
			.getName());

	@Autowired
	private StorageDAOHibernate storageDAO;

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@Transactional(readOnly = false)
	public void addStorage(Storage storage) {
		getStorageDAO().add(storage);
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@Transactional(readOnly = false)
	public void deleteStorage(Storage storage) {
		getStorageDAO().delete(storage);
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@Transactional(readOnly = false)
	public void updateStorage(Storage storage) {
		getStorageDAO().update(storage);
	}

	public Storage getStorageById(int id) {
		return getStorageDAO().get(id);
	}

	public State getStateInStorage(int id) {
		Storage theStorage = getStorageDAO().get(id);
		State theState = theStorage.getState();
		log.debug("Got storage state: " + theState);
		return theState;
	}

	public List<Storage> getStorages() {
		return getStorageDAO().getAll();
	}

	public StorageDAOHibernate getStorageDAO() {
		return storageDAO;
	}

	public void setStorageDAO(StorageDAOHibernate storageDAO) {
		this.storageDAO = storageDAO;
	}

}
