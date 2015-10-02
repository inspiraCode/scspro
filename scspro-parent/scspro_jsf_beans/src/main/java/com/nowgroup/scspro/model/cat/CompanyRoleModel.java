package com.nowgroup.scspro.model.cat;

import com.nowgroup.scspro.dto.cat.CompanyRole;
import com.nowgroup.scspro.model.Modeleable;

public class CompanyRoleModel extends CompanyRole implements Modeleable<CompanyRole> {
    private static final long serialVersionUID = 9034795124912189107L;
    private String displayName;
    private boolean selected;

    public String getDisplayName() {
	return displayName;
    }

    public void setDisplayName(String displayName) {
	this.displayName = displayName;
    }

    public CompanyRole deModelize() {
	CompanyRole result = new CompanyRole();
	result.setId(this.getId());
	result.setName(this.getName());
	return result;
    }

    @Override
    public CompanyRole demodelize() {
	CompanyRole result = new CompanyRole();
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
