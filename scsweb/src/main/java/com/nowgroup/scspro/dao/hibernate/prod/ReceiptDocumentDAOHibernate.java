package com.nowgroup.scspro.dao.hibernate.prod;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.prod.ReceiptDocumentDAO;
import com.nowgroup.scspro.dto.prod.ReceiptDocument;

@Repository("receiptDocumentDAO")
public class ReceiptDocumentDAOHibernate extends BaseHibernateDAO<ReceiptDocument> implements ReceiptDocumentDAO {
    public ReceiptDocumentDAOHibernate() {
	super(ReceiptDocument.class);
    }
}
