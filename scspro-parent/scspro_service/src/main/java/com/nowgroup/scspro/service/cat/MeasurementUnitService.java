package com.nowgroup.scspro.service.cat;

import java.util.List;

import com.nowgroup.scspro.dto.cat.MeasurementUnit;
import com.nowgroup.scspro.dto.cat.MeasurementUnitRole;
import com.nowgroup.scspro.service.BaseService;

public interface MeasurementUnitService extends BaseService<MeasurementUnit> {
    List<MeasurementUnitRole> getMeasurementUnitRoles(int id);

    void addMeasurementUnitRole(int id, MeasurementUnitRole mur);

    void removeMeasurementUnitRole(int id, MeasurementUnitRole mur);
    
    List<MeasurementUnit> getMeasurementUnitsByUsage(String usage);
}
