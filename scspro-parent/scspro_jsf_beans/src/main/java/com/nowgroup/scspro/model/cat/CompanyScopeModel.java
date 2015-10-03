package com.nowgroup.scspro.model.cat;

import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.model.Modeleable;

public class CompanyScopeModel extends CompanyScope implements Modeleable<CompanyScope> {
    private static final long serialVersionUID = -790177733979297790L;

    private boolean selected;

    public CompanyScopeModel() {
    }

    public CompanyScopeModel(CompanyScope scope) {
	this.setId(scope.getId());
	this.setCompany(scope.getCompany());
	this.setCompanyRole(scope.getCompanyRole());
    }

    public CompanyScope demodelize() {
	CompanyScope result = new CompanyScope();
	result.setId(this.getId());
	result.setCompany(this.getCompany());
	result.setCompanyRole(this.getCompanyRole());
	return result;
    }
    
    @Override
    public Modeleable<CompanyScope> getModel(CompanyScope base) {
	this.setCompany(base.getCompany());
	this.setCompanyRole(base.getCompanyRole());
	this.setId(base.getId());
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
