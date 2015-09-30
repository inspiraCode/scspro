package com.nowgroup.scspro.model.prod;

import com.nowgroup.scspro.dto.prod.ReceiptFreight;

public class ReceiptFreightModel extends ReceiptFreight {
    private static final long serialVersionUID = 800661619500619839L;
    private String freighterName;
    private boolean selected;
    
    public ReceiptFreight demodelize(){
	ReceiptFreight result = new ReceiptFreight();
	result.setId(getId());
	result.setComments(getComments());
	result.setFreighter(getFreighter());
	result.setGuide(getGuide());
	result.setGuideDate(getGuideDate());
	result.setPaymentCondition(getPaymentCondition());
	result.setReceipt(getReceipt());
	result.setVehicle(getVehicle());
	return result;
    }
    
    public String getFreighterName() {
	return freighterName;
    }
    public void setFreighterName(String freighterName) {
	this.freighterName = freighterName;
    }

    public boolean isSelected() {
	return selected;
    }

    public void setSelected(boolean selected) {
	this.selected = selected;
    }
}
