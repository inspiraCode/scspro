package com.nowgroup.scspro.model.prod;

import com.nowgroup.scspro.dto.prod.ReceiptDocument;
import com.nowgroup.scspro.model.Modeleable;

public class ReceiptDocumentModel extends ReceiptDocument implements Modeleable<ReceiptDocument> {
    private static final long serialVersionUID = 4686205742692074714L;
    private boolean selected = false;
    
    @Override
    public ReceiptDocument demodelize() {
	// TODO Auto-generated method stub
	return null;
    }
    
    @Override
    public Modeleable<ReceiptDocument> getModel(ReceiptDocument base) {
	// TODO Auto-generated method stub
	return null;
    }
    
    @Override
    public boolean isSelected() {
	return selected;
    }

    @Override
    public void setSelected(boolean selected) {
	this.selected = selected;
    }
    
}
