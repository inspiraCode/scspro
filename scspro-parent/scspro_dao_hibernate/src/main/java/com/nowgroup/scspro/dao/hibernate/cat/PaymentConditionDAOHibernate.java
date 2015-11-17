package com.nowgroup.scspro.dao.hibernate.cat;

import com.nowgroup.scspro.dao.cat.PaymentConditionDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.cat.PaymentCondition;

public class PaymentConditionDAOHibernate extends BaseHibernateDAO<PaymentCondition> implements PaymentConditionDAO {
    public PaymentConditionDAOHibernate() {
	super(PaymentCondition.class);
    }
}
