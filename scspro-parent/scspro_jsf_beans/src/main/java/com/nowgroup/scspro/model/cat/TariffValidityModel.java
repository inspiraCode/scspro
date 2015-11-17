package com.nowgroup.scspro.model.cat;

import com.nowgroup.scspro.dto.cat.TariffValidity;
import com.nowgroup.scspro.model.Modeleable;

public class TariffValidityModel extends TariffValidity implements Modeleable<TariffValidity> {
    private static final long serialVersionUID = -4031119467357200013L;
    private boolean selected = false;
    
    public TariffValidityModel() {
    }
    
    @Override
    public TariffValidity demodelize() {
	TariffValidity result = new TariffValidity();
	
	result.setId(this.getId());
	result.setCountry(this.getCountry());
	result.setDescription(this.getDescription());
	result.setMeasurementUnit(this.getMeasurementUnit());
	
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
    public Modeleable<TariffValidity> getModel(TariffValidity base) {
	TariffValidityModel result = new TariffValidityModel();
	result.setId(base.getId());
	result.setCountry(base.getCountry());
	result.setDescription(base.getDescription());
	result.setMeasurementUnit(base.getMeasurementUnit());
	
	result.setSelected(false);
	
	return result;
    }

}
