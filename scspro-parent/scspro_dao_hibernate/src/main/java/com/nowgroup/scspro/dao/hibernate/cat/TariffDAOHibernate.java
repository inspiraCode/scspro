package com.nowgroup.scspro.dao.hibernate.cat;

import com.nowgroup.scspro.dao.cat.TariffDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.Tariff;

public class TariffDAOHibernate extends BaseHibernateDAO<Tariff> implements TariffDAO {
    public TariffDAOHibernate() {
	super(Tariff.class);
    }
}
