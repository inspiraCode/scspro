package com.nowgroup.scspro.model.prod;

import com.nowgroup.scspro.dto.prod.Receipt;
import com.nowgroup.scspro.model.Modeleable;

public class ReceiptModel extends Receipt implements Modeleable<Receipt> {
    private static final long serialVersionUID = 8389106011970442297L;
    private boolean selected;

    public Receipt demodelize(){
	Receipt receipt = new Receipt();
	
	receipt.setId(this.getId());
	receipt.setComments(this.getComments());
	receipt.setCompanies(this.getCompanies());
	receipt.setFolio(this.getFolio());
	receipt.setReceiptBy(this.getReceiptBy());
	receipt.setReceiptDate(this.getReceiptDate());
	receipt.setStorage(this.getStorage());
	
	receipt.getFreights().clear();
	receipt.getMerchandise().clear();
	receipt.getDocuments().clear();
	
	receipt.getFreights().addAll(this.getFreights());
	receipt.getMerchandise().addAll(this.getMerchandise());
	receipt.getDocuments().addAll(this.getDocuments());
	
	return receipt;
    }
    
    @Override
    public Modeleable<Receipt> getModel(Receipt base) {
	
	ReceiptModel result = new ReceiptModel();
	
	result.setArrivalFolio(base.getArrivalFolio());
	result.setComments(base.getComments());
	result.getCompanies().clear();
	result.getCompanies().addAll(base.getCompanies());
	result.setFolio(base.getFolio());
	result.setId(base.getId());
	result.setReceiptBy(base.getReceiptBy());
	result.setReceiptDate(base.getReceiptDate());
	result.setSelected(false);
	result.setStorage(base.getStorage());
	result.setCreatedOn(base.getCreatedOn());
	
	result.getFreights().clear();
	result.getMerchandise().clear();
	result.getDocuments().clear();
	
	result.getFreights().addAll(base.getFreights());
	result.getMerchandise().addAll(base.getMerchandise());
	result.getDocuments().addAll(base.getDocuments());
	
        return result;
    }

    public boolean isSelected() {
	return selected;
    }

    public void setSelected(boolean selected) {
	this.selected = selected;
    }
}
