package com.nowgroup.scspro.dao.hibernate.sys;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.sys.MeasurementUnitRoleDAO;
import com.nowgroup.scspro.dto.sys.MeasurementUnitRole;

@Repository("measurementUitRoleDAO")
public class MeasurementUnitRoleDAOHibernate extends
		BaseHibernateDAO<MeasurementUnitRole> implements MeasurementUnitRoleDAO {

	public MeasurementUnitRoleDAOHibernate() {
		super(MeasurementUnitRole.class);
	}
}
