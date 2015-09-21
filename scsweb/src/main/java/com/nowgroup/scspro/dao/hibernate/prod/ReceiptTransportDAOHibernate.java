package com.nowgroup.scspro.dao.hibernate.prod;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.prod.ReceiptTransportDAO;
import com.nowgroup.scspro.dto.prod.ReceiptTransport;

@Repository("receiptTransportDAO")
public class ReceiptTransportDAOHibernate extends
		BaseHibernateDAO<ReceiptTransport> implements ReceiptTransportDAO {
	public ReceiptTransportDAOHibernate() {
		super(ReceiptTransport.class);
	}
}
