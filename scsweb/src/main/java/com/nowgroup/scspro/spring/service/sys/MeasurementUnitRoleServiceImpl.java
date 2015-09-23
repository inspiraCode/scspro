package com.nowgroup.scspro.spring.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.hibernate.sys.MeasurementUnitRoleDAOHibernate;
import com.nowgroup.scspro.dto.sys.MeasurementUnitRole;
import com.nowgroup.scspro.service.sys.MeasurementUnitRoleService;

@Service
@Transactional(readOnly = true)
public class MeasurementUnitRoleServiceImpl implements MeasurementUnitRoleService {

    @Autowired
    private MeasurementUnitRoleDAOHibernate measurementUnitRoleDAO;

    public List<MeasurementUnitRole> getMeasurementUnitRoles() {
	return getMeasurementUnitRoleDAO().getAll();
    }

    public MeasurementUnitRoleDAOHibernate getMeasurementUnitRoleDAO() {
	return measurementUnitRoleDAO;
    }

    public void setMeasurementUnitRoleDAO(MeasurementUnitRoleDAOHibernate measurementUnitRoleDAO) {
	this.measurementUnitRoleDAO = measurementUnitRoleDAO;
    }

}
