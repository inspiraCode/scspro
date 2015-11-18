package com.nowgroup.scspro.jsf.beans.cat;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.nowgroup.scspro.dto.cat.MeasurementUnit;
import com.nowgroup.scspro.dto.cat.MeasurementUnitRole;
import com.nowgroup.scspro.service.cat.MeasurementUnitService;

@ManagedBean
@RequestScoped
public class MeasurementUnitClassifierBean implements Serializable {
    private static final long serialVersionUID = -2788596679167979403L;

    @ManagedProperty("#{measurementUnitService}")
    private MeasurementUnitService service;

    public List<MeasurementUnit> getPackingMeasurementUnits() {
	return getMeasurementUnitByUsage(MeasurementUnitRole.PACK_USAGE);
    }

    public List<MeasurementUnit> getWeightMeasurementUnits() {
	return getMeasurementUnitByUsage(MeasurementUnitRole.WEIGHT_USAGE);
    }

    public List<MeasurementUnit> getLoadMeasurementUnits() {
	return getMeasurementUnitByUsage(MeasurementUnitRole.LOAD_USAGE);
    }

    private List<MeasurementUnit> getMeasurementUnitByUsage(String usage) {
	List<MeasurementUnit> result = new LinkedList<MeasurementUnit>();
	// TODO: Create query by usage
	for (MeasurementUnit unit : getService().getMeasurementUnitsByUsage(usage)) {
	    result.add(unit);
	}

	Collections.sort(result);
	return result;
    }

    public MeasurementUnitService getService() {
	return service;
    }

    public void setService(MeasurementUnitService service) {
	this.service = service;
    }

}
