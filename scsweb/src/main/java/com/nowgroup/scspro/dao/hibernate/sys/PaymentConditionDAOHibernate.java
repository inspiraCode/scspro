package com.nowgroup.scspro.dao.hibernate.sys;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dao.sys.PaymentConditionDAO;
import com.nowgroup.scspro.dto.sys.PaymentCondition;

@Repository("paymentConditionDAO")
public class PaymentConditionDAOHibernate extends
		BaseHibernateDAO<PaymentCondition> implements PaymentConditionDAO {
	public PaymentConditionDAOHibernate() {
		super(PaymentCondition.class);
	}
}
