package com.nowgroup.scspro.jsf.beans.proc;

import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import com.nowgroup.scspro.dto.ItemByNameException;
import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.CompanyRole;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.dto.prod.Receipt;
import com.nowgroup.scspro.jsf.beans.BaseFacesReporteableBean;
import com.nowgroup.scspro.model.prod.ReceiptModel;
import com.nowgroup.scspro.service.cat.CompanyScopeService;
import com.nowgroup.scspro.service.cat.CompanyService;
import com.nowgroup.scspro.service.prod.ReceiptService;

@ManagedBean
@SessionScoped
public class ReceiptBean extends BaseFacesReporteableBean<Receipt> {
    private static final long serialVersionUID = -3491798031008285552L;
    private final static String STORAGE_CODE = "101";
    private static final Logger log = Logger.getLogger(ReceiptBean.class.getName());

    private ReceiptModel receipt;
    private int senderId;
    private int receiverId;
    private int sellerId;
    private int purchaserId;

    private String receiptGuide;
    private Date guideDate;
    private String receiptVehicle;
    private int freighterId;
    private int paymentConditionId;
    private String receiptFreightComments;
    private String freighterName;
    private String vehicle;

    private String senderName;
    private String receiverName;
    private String sellerName;
    private String purchaserName;

    @ManagedProperty("#{companyScopeService}")
    private CompanyScopeService companyScope;

    @ManagedProperty("#{companyService}")
    private CompanyService companyService;

    @ManagedProperty("#{receiptService}")
    private ReceiptService receiptService;

    @ManagedProperty("#{i18n_proc_receipt}")
    private ResourceBundle msg;

    public ReceiptBean() {
	super(new ReceiptModel());
    }

    public String addNew() {
	this.receipt = new ReceiptModel();
	try {
	    receipt.setFolio(receiptService.getSequence(STORAGE_CODE));
	} catch (ItemByNameException e) {
	    log.error(e.getMessage(), e);
	    publishError(msg.getString("receipt.sequenceFail"));
	}

	setSenderId(0);
	setReceiverId(0);
	setSellerId(0);
	setPurchaserId(0);

	return "receipt";
    }

    public String edit(ReceiptModel receiptModel) throws Exception {
	// TODO: Implement
	publishWarning("NOT IMPLEMENTED YET");
	return "";
    }

    public String remove(ReceiptModel receiptModel) throws Exception {
	try {
	    getReceiptService().delete(receiptModel.demodelize());
	    // TODO: Force download list
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    publishError(msg.getString("receipt.remove.error"));
	    publishError(e.getLocalizedMessage());
	}
	return "list";
    }

    public String uploadReceipt() throws Exception {
	try {
	    if (getReceipt().getId() != 0) {
		getReceiptService().update(receipt.demodelize());
	    } else {
		getReceiptService().add(receipt.demodelize());
	    }
	} catch (org.springframework.dao.DataIntegrityViolationException e) {
	    log.error(e.getMessage(), e);
	    publishError(msg.getString("receipt.save.duplicate"));
	    return "";
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    publishError(msg.getString("receipt.save.error"));
	    publishError(e.getLocalizedMessage());
	    return "";
	}

	return "list";
    }

    public String showList() {
	return "list";
    }

    public void senderAjaxListener(AjaxBehaviorEvent event) {
	// TODO: Block used receipt.
	if (getSenderId() != 0) {
	    removeCompanyFromReceipt(CompanyScope.SENDER_SCOPE);
	    CompanyScope profile = companyScope.getByCompanyAndRole(getSenderId(), CompanyScope.SENDER_SCOPE);
	    receipt.getCompanies().add(profile);

	    Company sender = companyService.get(getSenderId());
	    setSenderName(sender.getAlias());

	    CompanyScope sellerProfile = companyScope.getByCompanyAndRole(getSenderId(), CompanyScope.SELLER_SCOPE);
	    if (sellerProfile != null) {
		removeCompanyFromReceipt(CompanyScope.SELLER_SCOPE);
		setSellerId(getSenderId());
		receipt.getCompanies().add(sellerProfile);
		Company dbCompany = companyService.get(getSellerId());
		setSellerName(dbCompany.getAlias());
	    } else {
		publishWarning(msg.getString("receipt.senderNotSeller"));
	    }
	}
    }

    public void receiverAjaxListener(AjaxBehaviorEvent event) {
	if (getReceiverId() != 0) {
	    removeCompanyFromReceipt(CompanyScope.RECEIVER_SCOPE);
	    CompanyScope profile = companyScope.getByCompanyAndRole(getReceiverId(), CompanyScope.RECEIVER_SCOPE);
	    receipt.getCompanies().add(profile);

	    Company dbCompany = companyService.get(getReceiverId());
	    setReceiverName(dbCompany.getAlias());

	    CompanyScope purchaserProfile = companyScope.getByCompanyAndRole(getReceiverId(), CompanyScope.PURCHASER_SCOPE);
	    if (purchaserProfile != null) {
		removeCompanyFromReceipt(CompanyScope.PURCHASER_SCOPE);
		setPurchaserId(getReceiverId());
		receipt.getCompanies().add(purchaserProfile);
		dbCompany = companyService.get(getPurchaserId());
		setPurchaserName(dbCompany.getAlias());
	    } else {
		publishWarning(msg.getString("receipt.receiverNotPurchaser"));
	    }
	}
    }

    public void purchaserAjaxListener(AjaxBehaviorEvent event) {
	if (getPurchaserId() != 0) {
	    removeCompanyFromReceipt(CompanyScope.PURCHASER_SCOPE);
	    CompanyScope profile = companyScope.getByCompanyAndRole(getPurchaserId(), CompanyScope.PURCHASER_SCOPE);
	    receipt.getCompanies().add(profile);

	    Company dbCompany = companyService.get(getPurchaserId());
	    setPurchaserName(dbCompany.getAlias());
	}
    }

    public void sellerAjaxListener(AjaxBehaviorEvent event) {
	if (getSellerId() != 0) {
	    removeCompanyFromReceipt(CompanyScope.SELLER_SCOPE);
	    CompanyScope profile = companyScope.getByCompanyAndRole(getSellerId(), CompanyScope.SELLER_SCOPE);
	    receipt.getCompanies().add(profile);

	    Company dbCompany = companyService.get(getSellerId());
	    setSellerName(dbCompany.getAlias());
	}
    }

    public void freighterAjaxListener(AjaxBehaviorEvent event) {
	if (freighterId != 0) {
	    Company dbCompany = companyService.get(freighterId);
	    freighterName = dbCompany.getAlias();
	}
    }

    private void removeCompanyFromReceipt(String companyScope) {
	for (CompanyScope scope : receipt.getCompanies()) {
	    CompanyRole dbRole = getCompanyScope().getRoleInCompanyScope(scope.getId());
	    if (companyScope.equals(dbRole.getName())) {
		receipt.getCompanies().remove(scope);
		return;
	    }
	}
    }

    public ReceiptModel getReceipt() {
	return receipt;
    }

    public void setReceipt(ReceiptModel receipt) {
	this.receipt = receipt;
    }

    public int getSenderId() {
	return senderId;
    }

    /*
        public void setSenderId(int senderId) {
    	if (receiverId == 0)
    	    setSenderName("");
    	this.senderId = senderId;
        }

        public int getReceiverId() {
    	return receiverId;
        }

        public void setReceiverId(int receiverId) {
    	if (receiverId == 0)
    	    setReceiverName("");
    	this.receiverId = receiverId;
        }

        public int getSellerId() {
    	return sellerId;
        }

        public void setSellerId(int sellerId) {
    	if (sellerId == 0)
    	    setSellerName("");
    	this.sellerId = sellerId;
        }

        public int getPurchaserId() {
    	return purchaserId;
        }

        public void setPurchaserId(int purchaserId) {
    	if (purchaserId == 0)
    	    setPurchaserName("");
    	this.purchaserId = purchaserId;
        }
        
        public String getSenderName() {
    	return getReceipt().getSenderName();
        }

        public void setSenderName(String senderName) {
    	getReceipt().setSenderName(senderName);
        }

        public String getSellerName() {
    	return getReceipt().getSellerName();
        }

        public void setSellerName(String sellerName) {
    	getReceipt().setSellerName(sellerName);
        }

        public String getPurchaserName() {
    	return getReceipt().getPurchaserName();
        }

        public void setPurchaserName(String purchaserName) {
    	getReceipt().setPurchaserName(purchaserName);
        }

        public String getReceiverName() {
    	return getReceipt().getReceiverName();
        }

        public void setReceiverName(String receiverName) {
    	getReceipt().setReceiverName(receiverName);
        }
    */
    public CompanyScopeService getCompanyScope() {
	return companyScope;
    }

    public void setCompanyScope(CompanyScopeService companyScope) {
	this.companyScope = companyScope;
    }

    public CompanyService getCompanyService() {
	return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
	this.companyService = companyService;
    }

    public ResourceBundle getMsg() {
	return msg;
    }

    public void setMsg(ResourceBundle msg) {
	this.msg = msg;
    }

    public ReceiptService getReceiptService() {
	return receiptService;
    }

    public void setReceiptService(ReceiptService receiptService) {
	this.receiptService = receiptService;
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

    public String getReceiptVehicle() {
	return receiptVehicle;
    }

    public void setReceiptVehicle(String receiptVehicle) {
	this.receiptVehicle = receiptVehicle;
    }

    public int getFreighterId() {
	return freighterId;
    }

    public void setFreighterId(int freighterId) {
	this.freighterId = freighterId;
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

    public String getFreighterName() {
	return freighterName;
    }

    public void setFreighterName(String freighterName) {
	this.freighterName = freighterName;
    }

    public String getVehicle() {
	return vehicle;
    }

    public void setVehicle(String vehicle) {
	this.vehicle = vehicle;
    }

    public String getSenderName() {
	return senderName;
    }

    public void setSenderName(String senderName) {
	this.senderName = senderName;
    }

    public String getReceiverName() {
	return receiverName;
    }

    public void setReceiverName(String receiverName) {
	this.receiverName = receiverName;
    }

    public String getSellerName() {
	return sellerName;
    }

    public void setSellerName(String sellerName) {
	this.sellerName = sellerName;
    }

    public String getPurchaserName() {
	return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
	this.purchaserName = purchaserName;
    }

    public int getReceiverId() {
	return receiverId;
    }

    public void setReceiverId(int receiverId) {
	this.receiverId = receiverId;
    }

    public void setSenderId(int senderId) {
	this.senderId = senderId;
    }

    public int getSellerId() {
	return sellerId;
    }

    public void setSellerId(int sellerId) {
	this.sellerId = sellerId;
    }

    public int getPurchaserId() {
	return purchaserId;
    }

    public void setPurchaserId(int purchaserId) {
	this.purchaserId = purchaserId;
    }
}
