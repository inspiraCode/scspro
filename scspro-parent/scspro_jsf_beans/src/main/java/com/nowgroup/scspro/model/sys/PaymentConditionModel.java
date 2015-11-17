package com.nowgroup.scspro.model.sys;

import com.nowgroup.scspro.dto.cat.PaymentCondition;
import com.nowgroup.scspro.model.Modeleable;

public class PaymentConditionModel extends PaymentCondition implements Modeleable<PaymentCondition> {
    private static final long serialVersionUID = 1L;
    private boolean selected;
    
    
    @Override
    public PaymentCondition demodelize() {
	PaymentCondition result = new PaymentCondition();
	
	result.setCode(getCode());
	result.setDescription(getDescription());
	result.setId(getId());
	
	return result;
    }
    
    @Override
    public Modeleable<PaymentCondition> getModel(PaymentCondition base) {
	this.setId(base.getId());
	this.setCode(base.getCode());
	this.setDescription(base.getDescription());
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
