package com.nowgroup.scspro.dao.hibernate.sys;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.sys.StorageDAO;
import com.nowgroup.scspro.dto.sys.Storage;

@Repository("storageDAO")
public class StorageDAOHibernate extends BaseHibernateDAO<Storage> implements
		StorageDAO {
	public StorageDAOHibernate() {
		super(Storage.class);
	}
}
