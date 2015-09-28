package com.nowgroup.scspro.spring.service.sys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.hibernate.sys.MeasurementUnitDAOHibernate;
import com.nowgroup.scspro.dto.sys.MeasurementUnit;
import com.nowgroup.scspro.dto.sys.MeasurementUnitRole;
import com.nowgroup.scspro.service.sys.MeasurementUnitService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class MeasurementUnitServiceImpl extends BaseSpringService<MeasurementUnit> implements MeasurementUnitService {

    private MeasurementUnitDAOHibernate measurementUnitDAO;

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public int add(MeasurementUnit mu) {
	return super.add(mu);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public void delete(MeasurementUnit mu) {
	super.delete(mu);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public void update(MeasurementUnit mu) {
	super.update(mu);
    }

    public List<MeasurementUnitRole> getMeasurementUnitRoles(int id) {
	MeasurementUnit mu = getMeasurementUnitDAO().get(id);
	Set<MeasurementUnitRole> rolesInIt = mu.getRoles();
	List<MeasurementUnitRole> murs = new ArrayList<MeasurementUnitRole>();
	for (MeasurementUnitRole mur : rolesInIt) {
	    murs.add(mur);
	}
	return murs;
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void addMeasurementUnitRole(int id, MeasurementUnitRole mur) {
	MeasurementUnit muInDb = getMeasurementUnitDAO().get(id);
	List<MeasurementUnitRole> rolesInIt = getMeasurementUnitRoles(id);
	rolesInIt.add(mur);

	muInDb.setRoles(new HashSet<MeasurementUnitRole>());
	for (MeasurementUnitRole role : rolesInIt) {
	    muInDb.getRoles().add(role);
	}

	getMeasurementUnitDAO().add(muInDb);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void removeMeasurementUnitRole(int id, MeasurementUnitRole mur) {
	MeasurementUnit muInDb = getMeasurementUnitDAO().get(id);
	List<MeasurementUnitRole> rolesInIt = getMeasurementUnitRoles(id);
	rolesInIt.remove(mur);

	muInDb.setRoles(new HashSet<MeasurementUnitRole>());
	for (MeasurementUnitRole role : rolesInIt) {
	    muInDb.getRoles().add(role);
	}

	getMeasurementUnitDAO().update(muInDb);
    }

    public MeasurementUnitDAOHibernate getMeasurementUnitDAO() {
	return measurementUnitDAO;
    }

    public void setMeasurementUnitDAO(MeasurementUnitDAOHibernate measurementUnitDAO) {
	super.setDaoFactory((BaseDAO<MeasurementUnit>) measurementUnitDAO);
	this.measurementUnitDAO = measurementUnitDAO;
    }
}
