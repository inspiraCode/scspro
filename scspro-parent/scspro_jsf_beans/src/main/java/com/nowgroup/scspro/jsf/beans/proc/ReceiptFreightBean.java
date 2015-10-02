package com.nowgroup.scspro.jsf.beans.proc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.prod.ReceiptFreight;
import com.nowgroup.scspro.dto.sys.PaymentCondition;
import com.nowgroup.scspro.jsf.beans.BaseFacesBean;
import com.nowgroup.scspro.jsf.beans.sys.PaymentConditionBean;
import com.nowgroup.scspro.model.prod.ReceiptFreightModel;
import com.nowgroup.scspro.service.cat.CompanyService;
import com.nowgroup.scspro.service.sys.PaymentConditionService;

@ManagedBean
@SessionScoped
public class ReceiptFreightBean extends BaseFacesBean<ReceiptFreight> {
    private static final long serialVersionUID = 5094500901820048978L;
    private static final Logger log = Logger.getLogger(PaymentConditionBean.class.getName());

    private int receiptFreightId = 0;
    private String receiptGuide = "";
    private Date guideDate = new Date();
    private String vehicle;
    private int freighterId;
    private String freighterName;
    private int paymentConditionId;
    private String receiptFreightComments;
    private List<ReceiptFreightModel> receiptFreights = new ArrayList<ReceiptFreightModel>();
    private List<ReceiptFreightModel> selectedFreights = new ArrayList<ReceiptFreightModel>();
    private int internalID = 0;

    private ReceiptFreightModel receiptFreight;

    @ManagedProperty("#{paymentConditionService}")
    private PaymentConditionService paymentConditionService;

    @ManagedProperty("#{companyService}")
    private CompanyService companyService;

    @ManagedProperty("#{i18n_proc_receipt}")
    private ResourceBundle msg;

    public ReceiptFreightBean() {
	super(new ReceiptFreightModel());
    }
    
    private void clean() {
	receiptGuide = "";
	guideDate = new Date();
	vehicle = "";
	freighterId = 0;
	freighterName = "";
	paymentConditionId = 0;
	receiptFreightComments = "";
	receiptFreightId = 0;
    }

    private boolean guideInFreight() {
	log.debug("validate guideInFreight(" + receiptGuide + ")");
	if ("".equals(receiptGuide) || receiptGuide == null)
	    return false;
	for (ReceiptFreight content : receiptFreights) {
	    if (receiptGuide.equals(content.getGuide())) {
		log.warn("guide found in existing records: " + receiptGuide);
		return true;
	    }
	}
	return false;
    }

    private Company getFreighter(int id) {
	log.debug("getFreighter(" + id + ")");
	Company result = null;
	if (id != 0) {
	    result = companyService.get(id);
	    log.debug("Freighter found: " + result.getName());

	}
	return result;
    }

    private PaymentCondition getPaymentCondition(int id) {
	log.debug("getPaymentCondition");
	PaymentCondition result = null;

	if (id != 0) {
	    result = paymentConditionService.get(id);
	}

	return result;

    }

    public String selectedItem(ReceiptFreightModel item) {
	if (item.isSelected())
	    selectedFreights.add(item);
	else
	    selectedFreights.remove(item);

	return "";
    }

    public String add() {
	log.info("ReceiptFreightBean.add() - BEGIN");

	if (receiptFreightId != 0) {
	    ReceiptFreightModel temp = new ReceiptFreightModel();
	    temp.setId(receiptFreightId);

	    ReceiptFreightModel existent = receiptFreights.get(receiptFreights.indexOf(temp));
	    existent.setComments(receiptFreightComments);

	    Company freighter = getFreighter(freighterId);
	    if (freighter != null) {
		existent.setFreighter(freighter);
		existent.setFreighterName(freighter.getAlias());
	    }

	    existent.setGuide(receiptGuide);
	    existent.setGuideDate(guideDate);

	    PaymentCondition payment = getPaymentCondition(paymentConditionId);
	    if (payment != null) {
		existent.setPaymentCondition(getPaymentCondition(paymentConditionId));
	    }
	    existent.setVehicle(vehicle);
	 // TODO: else if(receiptFreightId > 0) upload to database. 
	} else {
	    if (receiptFreightId != 0 && guideInFreight()) {
		// Internationalize
		publishWarning(msg.getString("receipt.guide.existent"));
		log.info("ReceiptFreightBean.add() - VALIDATION FAILED");
		return "";
	    }
	    // Implement
	    ReceiptFreightModel newRf = new ReceiptFreightModel();
	    newRf.setId(receiptFreightId == 0 ? --internalID : receiptFreightId);
	    newRf.setGuide(receiptGuide);
	    newRf.setGuideDate(guideDate);
	    newRf.setVehicle(vehicle);
	    newRf.setComments(receiptFreightComments);

	    Company freighter = getFreighter(freighterId);
	    if (freighter != null) {
		newRf.setFreighter(freighter);
		newRf.setFreighterName(freighter.getAlias());
	    }

	    PaymentCondition payment = getPaymentCondition(paymentConditionId);
	    if (payment != null) {
		newRf.setPaymentCondition(getPaymentCondition(paymentConditionId));
	    }
	    // TODO: If receipt assigned, then store in database instead.
	    receiptFreights.add(newRf);
	}

	log.debug("new size of receiptFreight items: " + receiptFreights.size());
	log.info("ReceiptFreightBean.add() - END");
	return "";
    }

    public String edit(ReceiptFreightModel item) {
	receiptFreightId = item.getId();
	receiptGuide = item.getGuide();
	guideDate = item.getGuideDate();
	vehicle = item.getVehicle();
	freighterId = item.getFreighter().getId();
	freighterName = item.getFreighterName();
	paymentConditionId = item.getPaymentCondition().getId();
	receiptFreightComments = item.getComments();
	return "";
    }

    public String remove(ReceiptFreight item) {
	// TODO: if id > 0 first remove from database.
	receiptFreights.remove(item);
	clean();
	return "";
    }

    public void freighterAjaxListener(AjaxBehaviorEvent event) {
	if (freighterId != 0) {
	    Company selectedFreighter = getFreighter(freighterId);
	    setFreighterName(selectedFreighter.getAlias());
	}
    }

    public void addFreightAjaxListener(AjaxBehaviorEvent event) {
	try {
	    add();
	    clean();
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    publishError(e.getMessage());
	}
    }

    public void removeFreightAjaxListener(AjaxBehaviorEvent event) {
	try {
	    for (ReceiptFreightModel model : selectedFreights) {
		remove(model);
	    }
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    publishError(e.getMessage());
	}
    }

    public String getReceiptGuide() {
	return receiptGuide;
    }

    public void setReceiptGuide(String receiptGuide) {
	this.receiptGuide = receiptGuide;
    }

    public Date getGuideDate() {
	return guideDate;
    }

    public void setGuideDate(Date guideDate) {
	this.guideDate = guideDate;
    }

    public String getVehicle() {
	return vehicle;
    }

    public void setVehicle(String vehicle) {
	this.vehicle = vehicle;
    }

    public int getFreighterId() {
	return freighterId;
    }

    public void setFreighterId(int freighterId) {
	this.freighterId = freighterId;
    }

    public String getFreighterName() {
	return freighterName;
    }

    public void setFreighterName(String freighterName) {
	this.freighterName = freighterName;
    }

    public int getPaymentConditionId() {
	return paymentConditionId;
    }

    public void setPaymentConditionId(int paymentConditionId) {
	this.paymentConditionId = paymentConditionId;
    }

    public String getReceiptFreightComments() {
	return receiptFreightComments;
    }

    public void setReceiptFreightComments(String receiptFreightComments) {
	this.receiptFreightComments = receiptFreightComments;
    }

    public List<ReceiptFreightModel> getReceiptFreights() {
	log.debug("getReciptFreights() :: " + receiptFreights.size());
	return receiptFreights;
    }

    public void setReceiptFreights(List<ReceiptFreightModel> receiptFreights) {
	this.receiptFreights = receiptFreights;
    }

    public PaymentConditionService getPaymentConditionService() {
	return paymentConditionService;
    }

    public void setPaymentConditionService(PaymentConditionService paymentConditionService) {
	this.paymentConditionService = paymentConditionService;
    }

    public CompanyService getCompanyService() {
	return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
	this.companyService = companyService;
    }

    public ReceiptFreightModel getReceiptFreight() {
	return receiptFreight;
    }

    public void setReceiptFreight(ReceiptFreightModel receiptFreight) {
	this.receiptFreight = receiptFreight;
    }

    public List<ReceiptFreightModel> getSelectedFreights() {
	return selectedFreights;
    }

    public void setSelectedFreights(List<ReceiptFreightModel> selectedFreights) {
	this.selectedFreights = selectedFreights;
    }

    public ResourceBundle getMsg() {
	return msg;
    }

    public void setMsg(ResourceBundle msg) {
	this.msg = msg;
    }
}
