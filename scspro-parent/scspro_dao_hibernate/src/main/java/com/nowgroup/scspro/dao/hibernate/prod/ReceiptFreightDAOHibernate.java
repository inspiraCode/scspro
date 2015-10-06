package com.nowgroup.scspro.dao.hibernate.prod;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.prod.ReceiptFreightDAO;
import com.nowgroup.scspro.dto.prod.ReceiptFreight;

public class ReceiptFreightDAOHibernate extends BaseHibernateDAO<ReceiptFreight> implements ReceiptFreightDAO {
    public ReceiptFreightDAOHibernate() {
	super(ReceiptFreight.class);
    }

    @Override
    public boolean isGuideDuplicated(int freighterId, String guide, int freightId) {
	return !getHibernateTemplate().find(QUERY_BY_GUIDE, freighterId, guide, freightId).isEmpty();
    }
}
