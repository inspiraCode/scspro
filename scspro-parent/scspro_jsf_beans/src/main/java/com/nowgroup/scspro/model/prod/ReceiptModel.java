package com.nowgroup.scspro.model.prod;

import java.util.Date;

import com.nowgroup.scspro.dto.prod.Receipt;
import com.nowgroup.scspro.model.Modeleable;

public class ReceiptModel extends Receipt implements Modeleable<Receipt> {
    private static final long serialVersionUID = 8389106011970442297L;

    private String senderName;
    private String receiverName;
    private String purchaserName;
    private String sellerName;
    private Date packingListTimestamp;
    private String packingListFolio;
    private String purchaseOrderFolio;
    private String salesOrderFolio;
    private String inventoryFolio;
    private String invoiceFolio;
    private int quantity;
    private String packingType;
    private Date inventoryTimestamp;
    private Date physicalRevisionTimestamp;
    private Date legalRevisionTimestamp;
    private String guide;
    private String freighterName;
    
    private boolean selected;

    public Receipt demodelize(){
	Receipt receipt = new Receipt();
	
	receipt.setId(this.getId());
	receipt.setComments(this.getComments());
	receipt.setCompanies(this.getCompanies());
	receipt.setFolio(this.getFolio());
	receipt.setReceiptBy(this.getReceiptBy());
	receipt.setReceiptDate(this.getReceiptDate());
	receipt.setStorage(this.getStorage());
	
	return receipt;
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

    public Date getPackingListTimestamp() {
	return packingListTimestamp;
    }

    public void setPackingListTimestamp(Date packingListTimestamp) {
	this.packingListTimestamp = packingListTimestamp;
    }

    public String getPackingListFolio() {
	return packingListFolio;
    }

    public void setPackingListFolio(String packingListFolio) {
	this.packingListFolio = packingListFolio;
    }

    public String getPurchaseOrderFolio() {
	return purchaseOrderFolio;
    }

    public void setPurchaseOrderFolio(String purchaseOrderFolio) {
	this.purchaseOrderFolio = purchaseOrderFolio;
    }

    public String getSalesOrderFolio() {
	return salesOrderFolio;
    }

    public void setSalesOrderFolio(String salesOrderFolio) {
	this.salesOrderFolio = salesOrderFolio;
    }

    public String getInventoryFolio() {
	return inventoryFolio;
    }

    public void setInventoryFolio(String inventoryFolio) {
	this.inventoryFolio = inventoryFolio;
    }

    public String getInvoiceFolio() {
	return invoiceFolio;
    }

    public void setInvoiceFolio(String invoiceFolio) {
	this.invoiceFolio = invoiceFolio;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public String getPackingType() {
	return packingType;
    }

    public void setPackingType(String packingType) {
	this.packingType = packingType;
    }

    public Date getInventoryTimestamp() {
	return inventoryTimestamp;
    }

    public void setInventoryTimestamp(Date inventoryTimestamp) {
	this.inventoryTimestamp = inventoryTimestamp;
    }

    public Date getPhysicalRevisionTimestamp() {
	return physicalRevisionTimestamp;
    }

    public void setPhysicalRevisionTimestamp(Date physicalRevisionTimestamp) {
	this.physicalRevisionTimestamp = physicalRevisionTimestamp;
    }

    public Date getLegalRevisionTimestamp() {
	return legalRevisionTimestamp;
    }

    public void setLegalRevisionTimestamp(Date legalRevisionTimestamp) {
	this.legalRevisionTimestamp = legalRevisionTimestamp;
    }

    public String getGuide() {
	return guide;
    }

    public void setGuide(String guide) {
	this.guide = guide;
    }

    public String getFreighterName() {
	return freighterName;
    }

    public void setFreighterName(String freighterName) {
	this.freighterName = freighterName;
    }

    public boolean isSelected() {
	return selected;
    }

    public void setSelected(boolean selected) {
	this.selected = selected;
    }

    public String getPurchaserName() {
	return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
	this.purchaserName = purchaserName;
    }

    public String getSellerName() {
	return sellerName;
    }

    public void setSellerName(String sellerName) {
	this.sellerName = sellerName;
    }

}
