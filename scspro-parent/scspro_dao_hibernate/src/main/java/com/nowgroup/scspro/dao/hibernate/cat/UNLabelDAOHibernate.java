package com.nowgroup.scspro.dao.hibernate.cat;

import com.nowgroup.scspro.dao.cat.UNLabelDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.UNLabel;

public class UNLabelDAOHibernate extends BaseHibernateDAO<UNLabel> implements UNLabelDAO {
    public UNLabelDAOHibernate() {
	super(UNLabel.class);
    }
}
