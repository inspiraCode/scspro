package com.nowgroup.scspro.dao.hibernate.sys;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.sys.PaymentConditionDAO;
import com.nowgroup.scspro.dto.sys.PaymentCondition;

public class PaymentConditionDAOHibernate extends BaseHibernateDAO<PaymentCondition> implements PaymentConditionDAO {
    public PaymentConditionDAOHibernate() {
	super(PaymentCondition.class);
    }
}
