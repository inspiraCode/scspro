package com.nowgroup.scspro.model.prod;

import com.nowgroup.scspro.dto.prod.ReceiptMerchandise;
import com.nowgroup.scspro.model.Modeleable;

public class ReceiptMerchandiseModel extends ReceiptMerchandise implements Modeleable<ReceiptMerchandise> {
    private static final long serialVersionUID = 2373328972619541888L;
    private boolean selected = false;

    @Override
    public ReceiptMerchandise demodelize() {
	ReceiptMerchandise result = new ReceiptMerchandise();
	result.setId(this.getId());
	result.setComments(this.getComments());
	result.setItem(this.getItem());
	result.setKilos(this.getKilos());
	result.setLoadMeasurementUnit(this.getLoadMeasurementUnit());
	result.setPounds(this.getPounds());
	result.setQuantity(this.getQuantity());
	result.setUnLabel(this.getUnLabel());
	result.setWooden(this.isWooden());
	return result;
    }

    @Override
    public boolean isSelected() {
	return selected;
    }

    @Override
    public void setSelected(boolean selected) {
	this.selected = selected;
    }

    @Override
    public Modeleable<ReceiptMerchandise> getModel(ReceiptMerchandise base) {
	ReceiptMerchandiseModel result = new ReceiptMerchandiseModel();
	result.setId(base.getId());
	result.setComments(base.getComments());
	result.setItem(base.getItem());
	result.setKilos(base.getKilos());
	result.setLoadMeasurementUnit(base.getLoadMeasurementUnit());
	result.setPounds(base.getPounds());
	result.setQuantity(base.getQuantity());
	result.setSelected(false);
	result.setUnLabel(base.getUnLabel());
	result.setWooden(base.isWooden());
	return result;
    }

}
