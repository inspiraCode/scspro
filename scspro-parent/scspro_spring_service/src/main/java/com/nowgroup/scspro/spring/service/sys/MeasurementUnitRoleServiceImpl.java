package com.nowgroup.scspro.spring.service.sys;

import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.hibernate.sys.MeasurementUnitRoleDAOHibernate;
import com.nowgroup.scspro.dto.sys.MeasurementUnitRole;
import com.nowgroup.scspro.service.sys.MeasurementUnitRoleService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class MeasurementUnitRoleServiceImpl extends BaseSpringService<MeasurementUnitRole> implements MeasurementUnitRoleService {

    private MeasurementUnitRoleDAOHibernate measurementUnitRoleDAO;

    public MeasurementUnitRoleDAOHibernate getMeasurementUnitRoleDAO() {
	return measurementUnitRoleDAO;
    }

    public void setMeasurementUnitRoleDAO(MeasurementUnitRoleDAOHibernate measurementUnitRoleDAO) {
	super.setDaoFactory((BaseDAO<MeasurementUnitRole>) measurementUnitRoleDAO);
	this.measurementUnitRoleDAO = measurementUnitRoleDAO;
    }

}
