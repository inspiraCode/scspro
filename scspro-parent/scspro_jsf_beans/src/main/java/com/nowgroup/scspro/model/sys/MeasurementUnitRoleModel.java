package com.nowgroup.scspro.model.sys;

import com.nowgroup.scspro.dto.sys.MeasurementUnitRole;
import com.nowgroup.scspro.model.Modeleable;

public class MeasurementUnitRoleModel extends MeasurementUnitRole implements Modeleable<MeasurementUnitRole> {
    private static final long serialVersionUID = -6232864824037970322L;
    private boolean selected;

    @Override
    public MeasurementUnitRole demodelize() {
	MeasurementUnitRole result = new MeasurementUnitRole();
	result.setId(getId());
	result.setName(getName());
	return result;
    }
    
    @Override
    public Modeleable<MeasurementUnitRole> getModel(MeasurementUnitRole base) {
	this.setId(base.getId());
	this.setName(base.getName());
	this.setSelected(false);
        return this;
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
