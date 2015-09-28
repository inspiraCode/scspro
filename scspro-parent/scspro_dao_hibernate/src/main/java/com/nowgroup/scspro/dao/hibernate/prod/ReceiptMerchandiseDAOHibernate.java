package com.nowgroup.scspro.dao.hibernate.prod;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.prod.ReceiptMerchandiseDAO;
import com.nowgroup.scspro.dto.prod.ReceiptMerchandise;

public class ReceiptMerchandiseDAOHibernate extends BaseHibernateDAO<ReceiptMerchandise> implements ReceiptMerchandiseDAO {
    public ReceiptMerchandiseDAOHibernate() {
	super(ReceiptMerchandise.class);
    }
}
