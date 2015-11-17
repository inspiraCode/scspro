package com.nowgroup.scspro.dao.hibernate.cat;

import com.nowgroup.scspro.dao.cat.TariffValidityDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.TariffValidity;

public class TariffValidityDAOHibernate extends BaseHibernateDAO<TariffValidity> implements TariffValidityDAO {
    public TariffValidityDAOHibernate(){
	super(TariffValidity.class);
    }
}
