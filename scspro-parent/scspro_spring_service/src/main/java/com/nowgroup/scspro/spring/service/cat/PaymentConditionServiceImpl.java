package com.nowgroup.scspro.spring.service.cat;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.hibernate.cat.PaymentConditionDAOHibernate;
import com.nowgroup.scspro.dto.cat.PaymentCondition;
import com.nowgroup.scspro.service.cat.PaymentConditionService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class PaymentConditionServiceImpl extends BaseSpringService<PaymentCondition> implements PaymentConditionService {

    private PaymentConditionDAOHibernate paymentConditionDAO;

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public int add(PaymentCondition paymentCondition) {
	return super.add(paymentCondition);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public void delete(PaymentCondition paymentCondition) {
	getPaymentConditionDAO().delete(paymentCondition);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    @Override
    public void update(PaymentCondition paymentCondition) {
	getPaymentConditionDAO().update(paymentCondition);
    }

    public PaymentConditionDAOHibernate getPaymentConditionDAO() {
	return paymentConditionDAO;
    }

    public void setPaymentConditionDAO(PaymentConditionDAOHibernate paymentConditionDAO) {
	super.setDaoFactory((BaseDAO<PaymentCondition>) paymentConditionDAO);
	this.paymentConditionDAO = paymentConditionDAO;
    }

}
