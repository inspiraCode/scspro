package com.nowgroup.scspro.dao.hibernate;

import java.util.List;

import com.nowgroup.scspro.dto.ItemByNameDuplicatedException;
import com.nowgroup.scspro.dto.ItemByNameException;
import com.nowgroup.scspro.dto.ItemByNameNotFoundException;
import com.nowgroup.scspro.dto.NamableDTO;

public abstract class NamableHibernateDAO<T extends NamableDTO> extends BaseHibernateDAO<T> {
    private Class<T> type;

    public NamableHibernateDAO(Class<T> type) {
	super(type);
	this.type = type;
    }

    @SuppressWarnings("unchecked")
    public T getByName(String name) throws ItemByNameException {
	String qSearchByName = "from " + type.getCanonicalName() + " t where t.name = ?";
	List<T> helperList = (List<T>) getHibernateTemplate().find(qSearchByName, name);
	if (helperList.isEmpty()) {
	    throw new ItemByNameNotFoundException("Entity " + type.getCanonicalName() + " not found with name " + name);
	}

	if (helperList.size() > 1) {
	    throw new ItemByNameDuplicatedException("Entity " + type.getCanonicalName() + " found twice with name " + name);
	}

	return helperList.get(0);
    }
}
