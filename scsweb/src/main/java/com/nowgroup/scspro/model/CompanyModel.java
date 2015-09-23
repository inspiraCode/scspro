package com.nowgroup.scspro.model;

import com.nowgroup.scspro.dto.cat.Company;

public class CompanyModel extends Company {
    private static final long serialVersionUID = -2147292143916341207L;

    private String displayRoles;
    private boolean selected = false;

    public CompanyModel(Company company) {
	this.setId(company.getId());
	this.setName(company.getName());
	this.setAlias(company.getAlias());

	this.setAddressStreet(company.getAddressStreet());
	this.setAddressAdditional(company.getAddressAdditional());
	this.setCity(company.getCity());
	this.setState(company.getState());
	this.setZip(company.getZip());

	this.setPhone(company.getPhone());
	this.setPhoneSecondary(company.getPhoneSecondary());
	this.setFax(company.getFax());
	this.setEmail(company.getEmail());
	this.setWeb(company.getWeb());
    }

    public Company deModelize() {
	Company result = new Company();
	result.setId(this.getId());
	result.setName(this.getName());
	result.setAlias(this.getAlias());

	result.setAddressStreet(this.getAddressStreet());
	result.setAddressAdditional(this.getAddressAdditional());
	result.setCity(this.getCity());
	result.setState(this.getState());
	result.setZip(this.getZip());

	result.setPhone(this.getPhone());
	result.setPhoneSecondary(this.getPhoneSecondary());
	result.setFax(this.getFax());
	result.setEmail(this.getEmail());
	result.setWeb(this.getWeb());

	result.setCompanyScope(this.getCompanyScope());

	return result;
    }

    public CompanyModel() {
    }

    public String getDisplayRoles() {
	return displayRoles;
    }

    public void setDisplayRoles(String displayRoles) {
	this.displayRoles = displayRoles;
    }

    public boolean isSelected() {
	return selected;
    }

    public void setSelected(boolean selected) {
	this.selected = selected;
    }
}
