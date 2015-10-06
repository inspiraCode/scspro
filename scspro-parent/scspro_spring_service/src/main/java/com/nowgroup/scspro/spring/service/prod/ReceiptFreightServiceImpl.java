package com.nowgroup.scspro.spring.service.prod;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.prod.ReceiptFreightDAO;
import com.nowgroup.scspro.dto.prod.ReceiptFreight;
import com.nowgroup.scspro.service.prod.ReceiptFreightService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

public class ReceiptFreightServiceImpl extends BaseSpringService<ReceiptFreight> implements ReceiptFreightService {

    private ReceiptFreightDAO receiptFreightDAO;

    @Override
    public boolean isGuideDuplicated(int freighterId, String guide, int freightId) {
	return receiptFreightDAO.isGuideDuplicated(freighterId, guide, freightId);
    }

    public ReceiptFreightDAO getReceiptFreightDAO() {
	return receiptFreightDAO;
    }

    public void setReceiptFreightDAO(ReceiptFreightDAO receiptFreightDAO) {
	super.setDaoFactory((BaseDAO<ReceiptFreight>) receiptFreightDAO);
	this.receiptFreightDAO = receiptFreightDAO;
    }

}
