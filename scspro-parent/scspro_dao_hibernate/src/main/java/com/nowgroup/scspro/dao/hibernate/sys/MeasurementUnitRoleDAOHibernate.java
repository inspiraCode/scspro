package com.nowgroup.scspro.dao.hibernate.sys;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.sys.MeasurementUnitRoleDAO;
import com.nowgroup.scspro.dto.sys.MeasurementUnitRole;

public class MeasurementUnitRoleDAOHibernate extends BaseHibernateDAO<MeasurementUnitRole> implements MeasurementUnitRoleDAO {

    public MeasurementUnitRoleDAOHibernate() {
	super(MeasurementUnitRole.class);
    }
}
