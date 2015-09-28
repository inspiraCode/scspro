package com.nowgroup.scspro.dao.hibernate.sys;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.sys.MeasurementUnitDAO;
import com.nowgroup.scspro.dto.sys.MeasurementUnit;

public class MeasurementUnitDAOHibernate extends BaseHibernateDAO<MeasurementUnit> implements MeasurementUnitDAO {

    public MeasurementUnitDAOHibernate() {
	super(MeasurementUnit.class);
    }
}
