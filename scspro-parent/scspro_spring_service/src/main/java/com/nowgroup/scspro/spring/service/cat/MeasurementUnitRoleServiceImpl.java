package com.nowgroup.scspro.spring.service.cat;

import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.hibernate.cat.MeasurementUnitRoleDAOHibernate;
import com.nowgroup.scspro.dto.cat.MeasurementUnitRole;
import com.nowgroup.scspro.service.cat.MeasurementUnitRoleService;
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
