package com.nowgroup.scspro.spring.service.sys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.hibernate.sys.MeasurementUnitDAOHibernate;
import com.nowgroup.scspro.dto.sys.MeasurementUnit;
import com.nowgroup.scspro.dto.sys.MeasurementUnitRole;
import com.nowgroup.scspro.service.sys.MeasurementUnitService;

@Service
@Transactional(readOnly = true)
public class MeasurementUnitServiceImpl implements MeasurementUnitService {

    @Autowired
    private MeasurementUnitDAOHibernate measurementUnitDAO;

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void addMeasurementUnit(MeasurementUnit mu) {
	getMeasurementUnitDAO().add(mu);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void deleteMeasurementUnit(MeasurementUnit mu) {
	getMeasurementUnitDAO().delete(mu);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void updateMeasurementUnit(MeasurementUnit mu) {
	MeasurementUnit dbMu = getMeasurementUnitById(mu.getId());
	dbMu.setCode(mu.getCode());
	dbMu.setName(mu.getName());
	getMeasurementUnitDAO().update(dbMu);
    }

    public MeasurementUnit getMeasurementUnitById(int id) {
	return getMeasurementUnitDAO().get(id);
    }

    public List<MeasurementUnit> getMeasurementUnits() {
	return getMeasurementUnitDAO().getAll();
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
	this.measurementUnitDAO = measurementUnitDAO;
    }
}
