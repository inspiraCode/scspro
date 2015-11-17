package com.nowgroup.scspro.dto.prod;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;

import com.nowgroup.scspro.dto.BaseDTO;
import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.MeasurementUnit;

@Indexed
@Entity
@Table(name = "prod_receipt_documents", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = { "PRD_FOLIO", "PRD_PURCHASER" }) })
public class ReceiptDocument implements BaseDTO {
    private static final long serialVersionUID = -8327363518133489364L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRD_ID", nullable = false, unique = true)
    private int id;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRD_RECEIPT_ID", nullable = false)
    private Receipt receipt;

    @Field
    @Column(name = "PRD_FOLIO", nullable = false)
    private String folio;

    @Field
    @DateBridge(resolution = Resolution.DAY)
    @Temporal(TemporalType.DATE)
    @Column(name = "PRD_DATE", nullable = false)
    private Date documentDate = new Date();

    @Field
    @Column(name = "PRD_PURCHASE_ORDER")
    private String purchaseOrder;

    @Field
    @Column(name = "PRD_SALE_ORDER")
    private String salesOrder;

    @Field
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "PRD_PURCHASER", nullable = true)
    private Company purchaser;

    @Column(name = "PRD_QUANTITY", nullable = false)
    private int quantity = 0;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRD_PACK_MU", nullable = false)
    private MeasurementUnit packingMU;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Receipt getReceipt() {
	return receipt;
    }

    public void setReceipt(Receipt receipt) {
	this.receipt = receipt;
    }

    public String getFolio() {
	return folio;
    }

    public void setFolio(String folio) {
	this.folio = folio;
    }

    public Date getDocumentDate() {
	return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
	this.documentDate = documentDate;
    }

    public String getPurchaseOrder() {
	return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
	this.purchaseOrder = purchaseOrder;
    }

    public String getSalesOrder() {
	return salesOrder;
    }

    public void setSalesOrder(String salesOrder) {
	this.salesOrder = salesOrder;
    }

    public Company getPurchaser() {
	return purchaser;
    }

    public void setPurchaser(Company purchaser) {
	this.purchaser = purchaser;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public MeasurementUnit getPackingMU() {
	return packingMU;
    }

    public void setPackingMU(MeasurementUnit packingMU) {
	this.packingMU = packingMU;
    }

}
