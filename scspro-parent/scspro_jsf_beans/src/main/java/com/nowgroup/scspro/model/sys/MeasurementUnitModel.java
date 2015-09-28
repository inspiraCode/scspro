package com.nowgroup.scspro.model.sys;

import com.nowgroup.scspro.dto.sys.MeasurementUnit;

public class MeasurementUnitModel extends MeasurementUnit {
    private static final long serialVersionUID = -8759416234902740894L;
    private String displayRoles;

    public MeasurementUnitModel(MeasurementUnit source) {
	this.setId(source.getId());
	this.setCode(source.getCode());
	this.setName(source.getName());
    }

    public String getDisplayRoles() {
	return displayRoles;
    }

    public void setDisplayRoles(String displayRoles) {
	this.displayRoles = displayRoles;
    }
}
