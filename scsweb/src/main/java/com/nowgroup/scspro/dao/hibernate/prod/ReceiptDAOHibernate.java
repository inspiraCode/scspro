package com.nowgroup.scspro.dao.hibernate.prod;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.prod.ReceiptDAO;
import com.nowgroup.scspro.dto.prod.Receipt;

@Repository("receiptDAO")
public class ReceiptDAOHibernate extends BaseHibernateDAO<Receipt> implements ReceiptDAO {

    public ReceiptDAOHibernate() {
	super(Receipt.class);
    }
}
