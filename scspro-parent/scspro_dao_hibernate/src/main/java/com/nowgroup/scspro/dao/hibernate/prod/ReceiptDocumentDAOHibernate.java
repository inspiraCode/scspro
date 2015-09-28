package com.nowgroup.scspro.dao.hibernate.prod;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.prod.ReceiptDocumentDAO;
import com.nowgroup.scspro.dto.prod.ReceiptDocument;

public class ReceiptDocumentDAOHibernate extends BaseHibernateDAO<ReceiptDocument> implements ReceiptDocumentDAO {
    public ReceiptDocumentDAOHibernate() {
	super(ReceiptDocument.class);
    }
}
