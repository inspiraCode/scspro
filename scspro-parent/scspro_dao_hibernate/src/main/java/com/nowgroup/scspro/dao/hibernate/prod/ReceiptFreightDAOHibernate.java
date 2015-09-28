package com.nowgroup.scspro.dao.hibernate.prod;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.prod.ReceiptTransportDAO;
import com.nowgroup.scspro.dto.prod.ReceiptFreight;

public class ReceiptFreightDAOHibernate extends BaseHibernateDAO<ReceiptFreight> implements ReceiptTransportDAO {
    public ReceiptFreightDAOHibernate() {
	super(ReceiptFreight.class);
    }
}
