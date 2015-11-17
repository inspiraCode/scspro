package com.nowgroup.scspro.dao.hibernate.cat;

import com.nowgroup.scspro.dao.cat.StorageDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.Storage;

public class StorageDAOHibernate extends BaseHibernateDAO<Storage> implements StorageDAO {
    public StorageDAOHibernate() {
	super(Storage.class);
    }
}
