package com.nowgroup.scspro.jsf.beans.proc;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.nowgroup.scspro.dto.prod.ReceiptFreight;
import com.nowgroup.scspro.jsf.beans.BaseFacesBean;

@ManagedBean
@SessionScoped
public class ReceiptFreightBean extends BaseFacesBean {
    private static final long serialVersionUID = 5094500901820048978L;

    private String receiptGuide;
    private Date guideDate;
    private String vehicle;
    private int freighterId;
    private String freighterName;
    private int paymentConditionId;
    private String receiptFreightComments;
    private List<ReceiptFreight> receiptFreights;

    public String add() {
	// TODO: Implement
	publishWarning("NOT IMPLEMENTED YET");
	return "";
    }

    public String edit(ReceiptFreight item) {
	// TODO: Implement
	publishWarning("NOT IMPLEMENTED YET");
	return "";
    }
    
    public String remove(ReceiptFreight item){
	// TODO: Implement
	publishWarning("NOT IMPLEMENTED YET");
	return "";
    }
    
    public String upload(){
	// TODO: Implement
	publishWarning("NOT IMPLEMENTED YET");
	return "";
    }

    public void freighterAjaxListener(AjaxBehaviorEvent event) {
	// TODO: Implement
	publishWarning("NOT IMPLEMENTED YET");
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

    public List<ReceiptFreight> getReceiptFreights() {
	return receiptFreights;
    }

    public void setReceiptFreights(List<ReceiptFreight> receiptFreights) {
	this.receiptFreights = receiptFreights;
    }
}
