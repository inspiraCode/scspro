package com.nowgroup.scspro.model.sys;

import com.nowgroup.scspro.dto.sys.MeasurementUnitRole;
import com.nowgroup.scspro.model.Modeleable;

public class MeasurementUnitRoleModel extends MeasurementUnitRole implements Modeleable<MeasurementUnitRole> {
    private static final long serialVersionUID = -6232864824037970322L;
    private String displayName;
    private boolean selected;

    public String getDisplayName() {
	return displayName;
    }

    public void setDisplayName(String displayName) {
	this.displayName = displayName;
    }

    @Override
    public MeasurementUnitRole demodelize() {
	MeasurementUnitRole result = new MeasurementUnitRole();
	result.setId(getId());
	result.setName(getName());
	return result;
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
