package com.nowgroup.scspro.model.cat;

import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.model.Modeleable;

public class CompanyModel extends Company implements Modeleable<Company> {
    private static final long serialVersionUID = -2147292143916341207L;
    private boolean selected = false;


    public Company demodelize() {
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

    @Override
    public Modeleable<Company> getModel(Company base) {
	CompanyModel result = new CompanyModel();
	result.setId(base.getId());
	result.setName(base.getName());
	result.setAlias(base.getAlias());

	result.setAddressStreet(base.getAddressStreet());
	result.setAddressAdditional(base.getAddressAdditional());
	result.setCity(base.getCity());
	result.setState(base.getState());
	result.setZip(base.getZip());

	result.setPhone(base.getPhone());
	result.setPhoneSecondary(base.getPhoneSecondary());
	result.setFax(base.getFax());
	result.setEmail(base.getEmail());
	result.setWeb(base.getWeb());
	result.setSelected(false);
	return result;
    }

    public CompanyModel() {
    }

    public boolean isSelected() {
	return selected;
    }

    public void setSelected(boolean selected) {
	this.selected = selected;
    }
}
