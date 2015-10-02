package com.nowgroup.scspro.service.sys;

import java.util.List;

import com.nowgroup.scspro.dto.sys.MeasurementUnit;
import com.nowgroup.scspro.dto.sys.MeasurementUnitRole;
import com.nowgroup.scspro.service.BaseService;

public interface MeasurementUnitService extends BaseService<MeasurementUnit> {
    List<MeasurementUnitRole> getMeasurementUnitRoles(int id);

    void addMeasurementUnitRole(int id, MeasurementUnitRole mur);

    void removeMeasurementUnitRole(int id, MeasurementUnitRole mur);
    
    List<MeasurementUnit> getMeasurementUnitsByUsage(String usage);
}
