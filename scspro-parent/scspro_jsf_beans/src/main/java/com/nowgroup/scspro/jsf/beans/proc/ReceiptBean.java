package com.nowgroup.scspro.jsf.beans.proc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
import com.nowgroup.scspro.dto.prod.ReceiptDocument;
import com.nowgroup.scspro.dto.prod.ReceiptFreight;
import com.nowgroup.scspro.dto.prod.ReceiptMerchandise;
import com.nowgroup.scspro.dto.sys.PaymentCondition;
import com.nowgroup.scspro.jsf.beans.BaseFacesReporteableBean;
import com.nowgroup.scspro.model.prod.ReceiptFreightModel;
import com.nowgroup.scspro.model.prod.ReceiptModel;
import com.nowgroup.scspro.service.cat.CompanyScopeService;
import com.nowgroup.scspro.service.cat.CompanyService;
import com.nowgroup.scspro.service.prod.ReceiptFreightService;
import com.nowgroup.scspro.service.prod.ReceiptService;
import com.nowgroup.scspro.service.sys.PaymentConditionService;

@ManagedBean
@SessionScoped
public class ReceiptBean extends BaseFacesReporteableBean<Receipt> {
    private static final long serialVersionUID = -3491798031008285552L;
    private final static String STORAGE_CODE = "101";
    private static final Logger log = Logger.getLogger(ReceiptBean.class.getName());

    //  ***************  RECEIPT HEADER DATA  *********
    private ReceiptModel receipt = new ReceiptModel();
    private int internalId = 0;

    private int senderId;
    private int receiverId;
    private int sellerId;
    private int purchaserId;

    private String senderName;
    private String receiverName;
    private String sellerName;
    private String purchaserName;

    // *************** FREIGHT ITEMS  ***************

    private int receiptFreightId;
    private String receiptGuide;
    private Date guideDate = new Date();
    private int freighterId;
    private int paymentConditionId;
    private String receiptFreightComments;
    private String freighterName;
    private String vehicle;

    private List<ReceiptFreightModel> selectedFreight = new LinkedList<ReceiptFreightModel>();

    // *************** DOCUMENT ITEMS  *****************
    private String docFolio;
    private Date docDate;
    private String poRef;
    private String soRef;
    private int docQty;
    private int docPackId;

    // ************** MERCHANDISE ITEMS  *******************
    private String itemSeq;
    private int goodsQty;
    private int goodsMeasurementUnitId;
    private boolean goodsWooden;
    private BigDecimal goodsKilos = new BigDecimal(0);
    private BigDecimal goodsPounds = new BigDecimal(0);
    private int goodsUNLabelId;
    private String goodsComments;

    // *************** MANAGED PROPERTIES
    @ManagedProperty("#{companyScopeService}")
    private CompanyScopeService companyScope;

    @ManagedProperty("#{companyService}")
    private CompanyService companyService;

    @ManagedProperty("#{receiptService}")
    private ReceiptService receiptService;

    @ManagedProperty("#{receiptFreightService}")
    private ReceiptFreightService receiptFreightService;

    @ManagedProperty("#{paymentConditionService}")
    private PaymentConditionService paymentConditionService;

    @ManagedProperty("#{i18n_proc_receipt}")
    private ResourceBundle msg;

    public ReceiptBean() {
	super(new ReceiptModel());
    }

    private void clearReceipt() {
	receipt = new ReceiptModel();

	senderId = 0;
	receiverId = 0;
	sellerId = 0;
	purchaserId = 0;

	senderName = "";
	receiverName = "";
	sellerName = "";
	purchaserName = "";
    }

    // ******************  FREIGHT ACTIONS  ******************
    private void clearFreight() {
	receiptFreightId = 0;
	receiptGuide = "";
	guideDate = new Date();

	freighterId = 0;
	freighterName = "";

	paymentConditionId = 0;
	receiptFreightComments = "";
	vehicle = "";

	selectedFreight.clear();
    }

    public void addFreightAjaxListener(AjaxBehaviorEvent event) {
	ReceiptFreight freight = new ReceiptFreight();
	if (receiptFreightId != 0) {
	    for (ReceiptFreight item : receipt.getFreights()) {
		if (item.getId() == receiptFreightId) {
		    freight = item;
		    break;
		}
	    }
	} else
	    // new, assign an internal Id
	    freight.setId(--internalId);

	// Validate before add/edit
	if (validateFreight()) {
	    freight.setComments(receiptFreightComments);

	    Company freighter = companyService.get(freighterId);
	    freight.setFreighter(freighter);

	    freight.setGuide(receiptGuide);
	    freight.setGuideDate(guideDate);

	    PaymentCondition paymentCondition = paymentConditionService.get(paymentConditionId);
	    freight.setPaymentCondition(paymentCondition);

	    freight.setVehicle(vehicle);

	    if (receiptFreightId == 0)
		receipt.getFreights().add(freight);

	    clearFreight();
	    publishInfo(msg.getString("freight.add.confirmation"));
	}

    }

    private boolean validateFreight() {
	// Validate empty values
	if ("".equals(receiptGuide)) {
	    publishWarning(msg.getString("freight.guideRequired"));
	    return false;
	}

	if (freighterId == 0) {
	    publishWarning(msg.getString("freight.freighterRequired"));
	    return false;
	}

	if (paymentConditionId == 0) {
	    publishWarning(msg.getString("freight.paymentRequired"));
	    return false;
	}

	if (guideDate == null) {
	    guideDate = new Date();
	}

	// Validate duplicates
	if (duplicatedGuide()) {
	    publishWarning(msg.getString("freight.duplicatedGuide"));
	    return false;
	}

	// TODO: Validate allowed ranges
	return true;
    }

    private boolean duplicatedGuide() {
	// receiptFreightId == 0 when adding new record
	if (receiptFreightId == 0) {
	    for (ReceiptFreight freight : receipt.getFreights()) {
		if (freight.getGuide().equals(receiptGuide))
		    return true;
	    }
	}
	// Check database for duplicated guide in freighter
	return receiptFreightService.isGuideDuplicated(freighterId, receiptGuide, receiptFreightId);
    }

    public void selectFreight(ReceiptFreightModel item) {
	log.debug("selected freight: " + item + " (" + item.isSelected() + ")");

	log.debug("analyzing " + selectedFreight.size() + " selected items.");
	for (ReceiptFreightModel freight : selectedFreight) {
	    log.debug(freight.getId() + " == " + item.getId() + " ?");
	    if (freight.getId() == item.getId()) {
		log.debug("found, removing from list");
		selectedFreight.remove(freight);
		item.setSelected(false);
		log.debug("Selected count: " + selectedFreight.size());
		return;
	    }
	}
	selectedFreight.add(item);
	item.setSelected(true);

	log.debug("Selected count: " + selectedFreight.size());
    }

    public void removeFreightAjaxListener(AjaxBehaviorEvent event) {
	log.debug("removing " + selectedFreight.size() + " freigths");
	for (ReceiptFreightModel item : selectedFreight) {
	    removeFreight(item);
	}
	selectedFreight.clear();
    }

    public void removeFreight(ReceiptFreightModel item) {
	log.debug("removing " + item + " from local receipt resource");
	boolean removed = false;
	for (ReceiptFreight freight : receipt.getFreights()) {
	    if (freight.getId() == item.getId()) {
		removed = receipt.getFreights().remove(freight);
		break;
	    }
	}
	log.debug(removed);
	selectedFreight.clear();
    }

    public void editFreight(ReceiptFreightModel item) {
	log.debug("Editing: " + item);
	receiptFreightId = item.getId();

	receiptGuide = item.getGuide();
	guideDate = item.getGuideDate();

	freighterId = item.getFreighter().getId();
	freighterName = item.getFreighterName();

	paymentConditionId = item.getPaymentCondition().getId();
	receiptFreightComments = item.getComments();
	vehicle = item.getVehicle();
	selectedFreight.clear();
    }

    // *********************** RECEIPT NAVIGATION ACTIONS  **************
    @Override
    public String addNew() {
	clearReceipt();
	clearFreight();
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

    public String getItemSeq() {
	return itemSeq;
    }

    public void setItemSeq(String itemSeq) {
	this.itemSeq = itemSeq;
    }

    public Date getDocDate() {
	return docDate;
    }

    public void setDocDate(Date docDate) {
	this.docDate = docDate;
    }

    public String getPoRef() {
	return poRef;
    }

    public void setPoRef(String poRef) {
	this.poRef = poRef;
    }

    public String getSoRef() {
	return soRef;
    }

    public void setSoRef(String soRef) {
	this.soRef = soRef;
    }

    public int getDocQty() {
	return docQty;
    }

    public void setDocQty(int docQty) {
	this.docQty = docQty;
    }

    public int getDocPackId() {
	return docPackId;
    }

    public void setDocPackId(int docPackId) {
	this.docPackId = docPackId;
    }

    public String getDocFolio() {
	return docFolio;
    }

    public void setDocFolio(String docFolio) {
	this.docFolio = docFolio;
    }

    public int getGoodsQty() {
	return goodsQty;
    }

    public void setGoodsQty(int goodsQty) {
	this.goodsQty = goodsQty;
    }

    public int getGoodsMeasurementUnitId() {
	return goodsMeasurementUnitId;
    }

    public void setGoodsMeasurementUnitId(int goodsMeasurementUnitId) {
	this.goodsMeasurementUnitId = goodsMeasurementUnitId;
    }

    public boolean isGoodsWooden() {
	return goodsWooden;
    }

    public void setGoodsWooden(boolean goodsWooden) {
	this.goodsWooden = goodsWooden;
    }

    public BigDecimal getGoodsKilos() {
	return goodsKilos;
    }

    public void setGoodsKilos(BigDecimal goodsKilos) {
	this.goodsKilos = goodsKilos;
    }

    public BigDecimal getGoodsPounds() {
	return goodsPounds;
    }

    public void setGoodsPounds(BigDecimal goodsPounds) {
	this.goodsPounds = goodsPounds;
    }

    public int getGoodsUNLabelId() {
	return goodsUNLabelId;
    }

    public void setGoodsUNLabelId(int goodsUNLabelId) {
	this.goodsUNLabelId = goodsUNLabelId;
    }

    public String getGoodsComments() {
	return goodsComments;
    }

    public void setGoodsComments(String goodsComments) {
	this.goodsComments = goodsComments;
    }

    public List<ReceiptFreightModel> getReceiptFreights() {
	if (receipt == null) {
	    return new LinkedList<ReceiptFreightModel>();
	} else {
	    List<ReceiptFreightModel> result = new LinkedList<ReceiptFreightModel>();
	    for (ReceiptFreight freight : receipt.getFreights()) {
		ReceiptFreightModel rfm = (ReceiptFreightModel) new ReceiptFreightModel().getModel(freight);
		String freighterName = rfm != null ? freight.getFreighter().getName() : "";
		rfm.setFreighterName(freighterName);
		result.add(rfm);
	    }

	    return result;
	}
    }

    public Set<ReceiptDocument> getReceiptDocuments() {
	if (receipt == null) {
	    return new HashSet<ReceiptDocument>();
	} else {
	    return receipt.getDocuments();
	}
    }

    public Set<ReceiptMerchandise> getReceiptMerchandise() {
	if (receipt == null) {
	    return new HashSet<ReceiptMerchandise>();
	} else {
	    return receipt.getMerchandise();
	}
    }

    public PaymentConditionService getPaymentConditionService() {
	return paymentConditionService;
    }

    public void setPaymentConditionService(PaymentConditionService paymentConditionService) {
	this.paymentConditionService = paymentConditionService;
    }

    public List<ReceiptFreightModel> getSelectedFreight() {
	return selectedFreight;
    }

    public void setSelectedFreight(List<ReceiptFreightModel> selectedFreight) {
	this.selectedFreight = selectedFreight;
    }

    public int getReceiptFreightId() {
	return receiptFreightId;
    }

    public void setReceiptFreightId(int receiptFreightId) {
	this.receiptFreightId = receiptFreightId;
    }

    public ReceiptFreightService getReceiptFreightService() {
	return receiptFreightService;
    }

    public void setReceiptFreightService(ReceiptFreightService receiptFreightService) {
	this.receiptFreightService = receiptFreightService;
    }
}
