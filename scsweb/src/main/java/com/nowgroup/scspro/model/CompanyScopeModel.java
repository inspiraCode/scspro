package com.nowgroup.scspro.model;

import com.nowgroup.scspro.dto.cat.CompanyScope;

public class CompanyScopeModel extends CompanyScope {
	private static final long serialVersionUID = -790177733979297790L;

	private String displayName;

	public CompanyScopeModel() {
	}

	public CompanyScopeModel(CompanyScope scope) {
		this.setId(scope.getId());
		this.setCompany(scope.getCompany());
		this.setCompanyRole(scope.getCompanyRole());
	}

	public CompanyScope deModelize() {
		CompanyScope result = new CompanyScope();
		result.setId(this.getId());
		result.setCompany(this.getCompany());
		result.setCompanyRole(this.getCompanyRole());
		return result;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
