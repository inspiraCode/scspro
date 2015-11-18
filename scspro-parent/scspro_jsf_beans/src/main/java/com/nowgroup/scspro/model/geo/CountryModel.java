package com.nowgroup.scspro.model.geo;

import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.model.Modeleable;

public class CountryModel extends Country implements Modeleable<Country> {
    private static final long serialVersionUID = 7020440853877158261L;
    
    private boolean selected;
    
    @Override
    public Country demodelize() {
	Country result = new Country();
	
	result.setCode(this.getCode());
	result.setId(this.getId());
	result.setName(this.getName());
	result.setStates(this.getStates());
	
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

    @Override
    public Modeleable<Country> getModel(Country base) {
	CountryModel result = new CountryModel();
	
	result.setCode(base.getCode());
	result.setId(base.getId());
	result.setName(base.getName());
	result.setStates(base.getStates());
	
	return result;
    }

}
