package com.nowgroup.scspro.dao.hibernate.prod;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.prod.ReceiptMerchandiseDAO;
import com.nowgroup.scspro.dto.prod.ReceiptMerchandise;

@Repository("receiptMerchandiseDAO")
public class ReceiptMerchandiseDAOHibernate extends BaseHibernateDAO<ReceiptMerchandise> implements ReceiptMerchandiseDAO {
    public ReceiptMerchandiseDAOHibernate() {
	super(ReceiptMerchandise.class);
    }
}
