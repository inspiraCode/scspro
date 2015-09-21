package com.nowgroup.scspro.model;

import com.nowgroup.scspro.dto.cat.Company;

public class CompanyModel extends Company {
	private static final long serialVersionUID = -2147292143916341207L;
	
	private String displayRoles;

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
	
	public CompanyModel() {
	}

	public String getDisplayRoles() {
		return displayRoles;
	}

	public void setDisplayRoles(String displayRoles) {
		this.displayRoles = displayRoles;
	}
}
