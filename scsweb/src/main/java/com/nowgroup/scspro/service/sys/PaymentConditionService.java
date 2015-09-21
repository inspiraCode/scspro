package com.nowgroup.scspro.service.sys;

import java.util.List;

import com.nowgroup.scspro.dto.sys.PaymentCondition;

public interface PaymentConditionService {
	void addPaymentCondition(PaymentCondition paymentCondition);
	
	void deletePaymentCondition(PaymentCondition paymentCondition);
	
	void updatePaymentCondition(PaymentCondition paymentCondition);
	
	PaymentCondition getPaymentCondition(int id);
	
	List<PaymentCondition> getPaymentCondition();
}
