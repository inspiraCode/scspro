package com.nowgroup.scspro.model;

import com.nowgroup.scspro.dto.cat.CompanyRole;

public class CompanyRoleModel extends CompanyRole {
    private static final long serialVersionUID = 9034795124912189107L;
    private String displayName;

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
}
