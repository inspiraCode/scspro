package com.nowgroup.scspro.model.sys;

import com.nowgroup.scspro.dto.sys.MeasurementUnit;
import com.nowgroup.scspro.model.Modeleable;

public class MeasurementUnitModel extends MeasurementUnit implements Modeleable<MeasurementUnit> {
    private static final long serialVersionUID = -8759416234902740894L;
    private String displayRoles;
    private boolean selected;

    public MeasurementUnitModel() {
    }

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

    @Override
    public MeasurementUnit demodelize() {
	MeasurementUnit result = new MeasurementUnit();
	result.setCode(getCode());
	result.setId(getId());
	result.setName(getName());
	result.getRoles().clear();
	result.getRoles().addAll(getRoles());
	return null;
    }

    @Override
    public void setSelected(boolean selected) {
	this.selected = selected;
    }

    @Override
    public boolean isSelected() {
	return selected;
    }
}
