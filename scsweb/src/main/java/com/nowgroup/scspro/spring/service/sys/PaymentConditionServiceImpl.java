package com.nowgroup.scspro.spring.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.hibernate.sys.PaymentConditionDAOHibernate;
import com.nowgroup.scspro.dto.sys.PaymentCondition;
import com.nowgroup.scspro.service.sys.PaymentConditionService;

@Service
@Transactional(readOnly = true)
public class PaymentConditionServiceImpl implements PaymentConditionService {

    @Autowired
    private PaymentConditionDAOHibernate paymentConditionDAO;

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void addPaymentCondition(PaymentCondition paymentCondition) {
	getPaymentConditionDAO().add(paymentCondition);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void deletePaymentCondition(PaymentCondition paymentCondition) {
	getPaymentConditionDAO().delete(paymentCondition);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @Transactional(readOnly = false)
    public void updatePaymentCondition(PaymentCondition paymentCondition) {
	getPaymentConditionDAO().update(paymentCondition);
    }

    public PaymentCondition getPaymentCondition(int id) {
	return getPaymentConditionDAO().get(id);
    }

    public List<PaymentCondition> getPaymentCondition() {
	return getPaymentConditionDAO().getAll();
    }

    public PaymentConditionDAOHibernate getPaymentConditionDAO() {
	return paymentConditionDAO;
    }

    public void setPaymentConditionDAO(PaymentConditionDAOHibernate paymentConditionDAO) {
	this.paymentConditionDAO = paymentConditionDAO;
    }

}
