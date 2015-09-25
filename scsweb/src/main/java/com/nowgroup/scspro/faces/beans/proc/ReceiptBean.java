package com.nowgroup.scspro.faces.beans.proc;

import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import com.nowgroup.scspro.dao.hibernate.ItemByNameException;
import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.faces.beans.BaseFacesReporteableBean;
import com.nowgroup.scspro.model.prod.ReceiptModel;
import com.nowgroup.scspro.service.cat.CompanyScopeService;
import com.nowgroup.scspro.service.cat.CompanyService;
import com.nowgroup.scspro.service.prod.ReceiptService;

@ManagedBean
@SessionScoped
public class ReceiptBean extends BaseFacesReporteableBean {
    private static final long serialVersionUID = -3491798031008285552L;
    private final static String STORAGE_CODE = "101";
    private static final Logger log = Logger.getLogger(ReceiptBean.class.getName());

    private ReceiptModel receipt;
    private int senderId;
    private int receiverId;
    private int sellerId;
    private int purchaserId;

    @ManagedProperty("#{companyScopeServiceImpl}")
    private CompanyScopeService companyScope;

    @ManagedProperty("#{companyServiceImpl}")
    private CompanyService companyService;
    
    @ManagedProperty("#{receiptServiceImpl}")
    private ReceiptService receiptService;

    @ManagedProperty("#{i18n_proc_receipt}")
    private ResourceBundle msg;

    public String addNew() {
	this.receipt = new ReceiptModel();
	try {
	    receipt.setFolio(receiptService.getSequence(STORAGE_CODE));
	} catch (ItemByNameException e) {
	    log.error(e.getMessage(), e);
	    publishError(msg.getString("receipt.sequenceFail"));
	}

	senderId = 0;
	receiverId = 0;
	sellerId = 0;
	purchaserId = 0;

	return "receipt";
    }

    public String edit(ReceiptModel receiptModel) throws Exception {
	// TODO: Implement
	publishWarning("NOT IMPLEMENTED YET");
	return "";
    }

    public String remove(ReceiptModel receiptModel) throws Exception {
	// TODO: Implement
	publishWarning("NOT IMPLEMENTED YET");
	return "";
    }

    public String uploadReceipt() throws Exception {
	// TODO: Implement
	publishWarning("NOT IMPLEMENTED YET");
	return "";
    }

    public String remove() {
	// TODO: Implement
	publishWarning("NOT IMPLEMENTED YET");
	return "";
    }

    public String showList() {
	// TODO: Implement
	publishWarning("NOT IMPLEMENTED YET");
	return "";
    }

    public void senderAjaxListener(AjaxBehaviorEvent event) {
	// TODO: Block used receipt.
	if (senderId != 0) {
	    removeCompanyFromReceipt(CompanyScope.SENDER_SCOPE);
	    CompanyScope profile = companyScope.getByCompanyAndRole(senderId, CompanyScope.SENDER_SCOPE);
	    receipt.getCompanies().add(profile);

	    Company sender = companyService.getCompanyById(senderId);
	    receipt.setSenderName(sender.getAlias());

	    CompanyScope sellerProfile = companyScope.getByCompanyAndRole(senderId, CompanyScope.SELLER_SCOPE);
	    if (sellerProfile != null) {
		removeCompanyFromReceipt(CompanyScope.SELLER_SCOPE);
		sellerId = senderId;
		receipt.getCompanies().add(sellerProfile);
		Company dbCompany = companyService.getCompanyById(sellerId);
		receipt.setSellerName(dbCompany.getAlias());
	    } else {
		publishWarning(msg.getString("receipt.senderNotSeller"));
	    }
	}
    }

    public void receiverAjaxListener(AjaxBehaviorEvent event) {
	if (receiverId != 0) {
	    removeCompanyFromReceipt(CompanyScope.RECEIVER_SCOPE);
	    CompanyScope profile = companyScope.getByCompanyAndRole(receiverId, CompanyScope.RECEIVER_SCOPE);
	    receipt.getCompanies().add(profile);

	    Company dbCompany = companyService.getCompanyById(receiverId);
	    receipt.setReceiverName(dbCompany.getAlias());

	    CompanyScope purchaserProfile = companyScope.getByCompanyAndRole(receiverId, CompanyScope.PURCHASER_SCOPE);
	    if (purchaserProfile != null) {
		removeCompanyFromReceipt(CompanyScope.PURCHASER_SCOPE);
		purchaserId = receiverId;
		receipt.getCompanies().add(purchaserProfile);
		dbCompany = companyService.getCompanyById(purchaserId);
		receipt.setPurchaserName(dbCompany.getAlias());
	    } else {
		publishWarning(msg.getString("receipt.receiverNotPurchaser"));
	    }
	}
    }

    public void purchaserAjaxListener(AjaxBehaviorEvent event) {
	if (purchaserId != 0) {
	    removeCompanyFromReceipt(CompanyScope.PURCHASER_SCOPE);
	    CompanyScope profile = companyScope.getByCompanyAndRole(purchaserId, CompanyScope.PURCHASER_SCOPE);
	    receipt.getCompanies().add(profile);

	    Company dbCompany = companyService.getCompanyById(purchaserId);
	    receipt.setPurchaserName(dbCompany.getAlias());
	}
    }

    public void sellerAjaxListener(AjaxBehaviorEvent event) {
	if (sellerId != 0) {
	    removeCompanyFromReceipt(CompanyScope.SELLER_SCOPE);
	    CompanyScope profile = companyScope.getByCompanyAndRole(sellerId, CompanyScope.SELLER_SCOPE);
	    receipt.getCompanies().add(profile);

	    Company dbCompany = companyService.getCompanyById(sellerId);
	    receipt.setSellerName(dbCompany.getAlias());
	}
    }

    private void removeCompanyFromReceipt(String companyScope) {
	for (CompanyScope scope : receipt.getCompanies()) {
	    if (scope.getCompanyRole().getName().equals(companyScope)) {
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
}
