package com.nowgroup.scspro.service.sys;

import java.util.List;

import com.nowgroup.scspro.dto.sys.MeasurementUnit;
import com.nowgroup.scspro.dto.sys.MeasurementUnitRole;

public interface MeasurementUnitService {
	void addMeasurementUnit(MeasurementUnit mu);
	
	void deleteMeasurementUnit(MeasurementUnit mu);
	
	void updateMeasurementUnit(MeasurementUnit mu);
	
	MeasurementUnit getMeasurementUnitById(int id);
	
	List<MeasurementUnit> getMeasurementUnits();
	
	List<MeasurementUnitRole> getMeasurementUnitRoles(int id);
	
	void addMeasurementUnitRole(int id, MeasurementUnitRole mur);
	
	void removeMeasurementUnitRole(int id, MeasurementUnitRole mur);
}
