package com.nowgroup.scspro.jsf.beans.sys;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.nowgroup.scspro.dto.sys.PaymentCondition;
import com.nowgroup.scspro.jsf.beans.BaseFacesBean;
import com.nowgroup.scspro.service.sys.PaymentConditionService;

@ManagedBean
@SessionScoped
public class PaymentConditionBean extends BaseFacesBean {
    private static final long serialVersionUID = -2957129988064896266L;
    private static final Logger log = Logger.getLogger(PaymentConditionBean.class.getName());

    private PaymentCondition paymentCondition = new PaymentCondition();

    @ManagedProperty("#{paymentConditionService}")
    private PaymentConditionService paymentConditionServiceImpl;

    @ManagedProperty("#{i18n_pay_cnd}")
    private ResourceBundle paymentCndBundle;

    public List<PaymentCondition> getPaymentConditions() {
	return paymentConditionServiceImpl.getAll();
    }

    public String addNew() {
	this.paymentCondition = new PaymentCondition();
	return "pay-cnd";
    }

    public String showList() {
	return "pay-cnd-list";
    }

    public String remove(PaymentCondition paymentCondition) {
	try {
	    paymentConditionServiceImpl.delete(paymentCondition);
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    FacesContext.getCurrentInstance().addMessage(null,
		    new FacesMessage(FacesMessage.SEVERITY_ERROR, paymentCndBundle.getString("pay_cnd.remove.error"), e.getMessage()));
	}
	return "pay-cnd-list";
    }

    public String uploadPaymentCondition() {
	try {
	    if (paymentCondition.getId() != 0)
		getPaymentConditionServiceImpl().update(paymentCondition);
	    else
		getPaymentConditionServiceImpl().add(paymentCondition);
	    return "success";
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    FacesContext.getCurrentInstance().addMessage(null,
		    new FacesMessage(FacesMessage.SEVERITY_ERROR, paymentCndBundle.getString("pay_cnd.save.error"), e.getMessage()));
	}
	return "";
    }

    public PaymentCondition getPaymentCondition() {
	return paymentCondition;
    }

    public void setPaymentCondition(PaymentCondition paymentCondition) {
	this.paymentCondition = paymentCondition;
    }

    public PaymentConditionService getPaymentConditionServiceImpl() {
	return paymentConditionServiceImpl;
    }

    public void setPaymentConditionServiceImpl(PaymentConditionService paymentConditionServiceImpl) {
	this.paymentConditionServiceImpl = paymentConditionServiceImpl;
    }

    public ResourceBundle getPaymentCndBundle() {
	return paymentCndBundle;
    }

    public void setPaymentCndBundle(ResourceBundle paymentCndBundle) {
	this.paymentCndBundle = paymentCndBundle;
    }
}
