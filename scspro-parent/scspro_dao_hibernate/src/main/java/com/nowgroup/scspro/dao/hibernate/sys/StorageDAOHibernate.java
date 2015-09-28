package com.nowgroup.scspro.dao.hibernate.sys;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.sys.StorageDAO;
import com.nowgroup.scspro.dto.sys.Storage;

public class StorageDAOHibernate extends BaseHibernateDAO<Storage> implements StorageDAO {
    public StorageDAOHibernate() {
	super(Storage.class);
    }
}
