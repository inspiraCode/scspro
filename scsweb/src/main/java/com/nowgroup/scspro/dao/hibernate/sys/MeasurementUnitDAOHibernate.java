package com.nowgroup.scspro.dao.hibernate.sys;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.sys.MeasurementUnitDAO;
import com.nowgroup.scspro.dto.sys.MeasurementUnit;

@Repository("measurementUnitDAO")
public class MeasurementUnitDAOHibernate extends
		BaseHibernateDAO<MeasurementUnit> implements MeasurementUnitDAO {

	public MeasurementUnitDAOHibernate() {
		super(MeasurementUnit.class);
	}
}
