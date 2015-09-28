package com.nowgroup.scspro.jsf.beans.proc;

import java.util.LinkedList;
import java.util.List;

import com.nowgroup.scspro.jsf.beans.BaseFacesReporteableBean;
import com.nowgroup.scspro.model.prod.ReceiptModel;

public class ReceiptListBean extends BaseFacesReporteableBean {
    private static final long serialVersionUID = -7999246536695559867L;
    
    public List<ReceiptModel> getReceipts(){
	return new LinkedList<ReceiptModel>();
    }
}
