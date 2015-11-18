package com.nowgroup.scspro.jsf.beans.cat;

import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.nowgroup.scspro.dto.cat.PaymentCondition;
import com.nowgroup.scspro.jsf.beans.BaseFacesBean;
import com.nowgroup.scspro.model.cat.PaymentConditionModel;
import com.nowgroup.scspro.service.BaseService;
import com.nowgroup.scspro.service.cat.PaymentConditionService;

@ManagedBean
@SessionScoped
public class PaymentConditionBean extends BaseFacesBean<PaymentCondition> {
    private static final long serialVersionUID = -2957129988064896266L;

    @ManagedProperty("#{paymentConditionService}")
    private PaymentConditionService paymentConditionServiceImpl;

    @ManagedProperty("#{i18n_pay_cnd}")
    private ResourceBundle paymentCndBundle;

    public PaymentConditionBean() {
	super(new PaymentConditionModel());
    }

    public PaymentConditionService getPaymentConditionServiceImpl() {
	return paymentConditionServiceImpl;
    }

    public void setPaymentConditionServiceImpl(PaymentConditionService paymentConditionServiceImpl) {
	super.setService((BaseService<PaymentCondition>)paymentConditionServiceImpl);
	this.paymentConditionServiceImpl = paymentConditionServiceImpl;
    }

    public ResourceBundle getPaymentCndBundle() {
	return paymentCndBundle;
    }

    public void setPaymentCndBundle(ResourceBundle paymentCndBundle) {
	super.setMsgBundle(paymentCndBundle);
	this.paymentCndBundle = paymentCndBundle;
    }
}
