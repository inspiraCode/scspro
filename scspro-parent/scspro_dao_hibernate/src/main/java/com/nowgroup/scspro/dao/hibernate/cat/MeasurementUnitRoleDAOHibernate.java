package com.nowgroup.scspro.dao.hibernate.cat;

import com.nowgroup.scspro.dao.cat.MeasurementUnitRoleDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.MeasurementUnitRole;

public class MeasurementUnitRoleDAOHibernate extends BaseHibernateDAO<MeasurementUnitRole> implements MeasurementUnitRoleDAO {

    public MeasurementUnitRoleDAOHibernate() {
	super(MeasurementUnitRole.class);
    }
}
