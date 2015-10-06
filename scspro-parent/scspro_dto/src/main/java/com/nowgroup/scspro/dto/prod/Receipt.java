package com.nowgroup.scspro.dto.prod;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;

import com.nowgroup.scspro.dto.BaseDTO;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.dto.sys.DbLog;
import com.nowgroup.scspro.dto.sys.Storage;

@Indexed
@Entity
@Table(name = "prod_receipt", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = "RECEIPT_FOLIO") })
public class Receipt implements BaseDTO {
    private static final long serialVersionUID = 8455979846759458905L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECEIPT_ID", nullable = true, unique = true)
    private int id;

    @Field
    @Column(name = "RECEIPT_FOLIO", nullable = false, unique = true)
    private String folio;

    @IndexedEmbedded
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cross_receipt_company", catalog = "supply_chain", joinColumns = { @JoinColumn(name = "CRC_RECEIPT_ID", nullable = false,
	    updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "CROSS_COMPANY_ROLE_ID", nullable = false, updatable = false) },
	    uniqueConstraints = { @UniqueConstraint(columnNames = { "CRC_RECEIPT_ID", "CROSS_COMPANY_ROLE_ID" }) })
    private Set<CompanyScope> companies = new HashSet<CompanyScope>();

    @Field
    @Column(name = "ARRIVAL_FOLIO")
    private String arrivalFolio;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIPT_STORAGE", nullable = false)
    private Storage storage;

    @Field(analyze = Analyze.NO)
    @DateBridge(resolution = Resolution.DAY)
    @Temporal(TemporalType.DATE)
    private Date receiptDate = new Date();

    @Field
    @Column(name = "RECEIPT_COMMENTS", nullable = true)
    private String comments;

    @Field
    @Column(name = "RECEIPT_BY", nullable = false)
    private String receiptBy;

    @Field(analyze = Analyze.NO)
    @DateBridge(resolution = Resolution.HOUR)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_ON", nullable = true, insertable = false, updatable = false)
    private Date createdOn;

    @IndexedEmbedded
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "log_receipt", catalog = "supply_chain", joinColumns = { @JoinColumn(name = "RECEIPT_ID", nullable = false, updatable = false) },
	    inverseJoinColumns = { @JoinColumn(name = "PROD_LOG_ID", nullable = false, updatable = false) })
    private Set<DbLog> dbLog;

    @IndexedEmbedded
    @OneToMany(mappedBy = "receipt", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReceiptDocument> documents = new HashSet<ReceiptDocument>();

    @IndexedEmbedded
    @OneToMany(mappedBy = "receipt", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReceiptFreight> freights = new HashSet<ReceiptFreight>();

    @IndexedEmbedded
    @OneToMany(mappedBy = "receipt", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReceiptMerchandise> merchandise = new HashSet<ReceiptMerchandise>();

    public Receipt() {
    }

    public Receipt(int id, String folio, Set<CompanyScope> companies, Storage storage, Date receiptDate, String comments, String receiptBy) {
	this.id = id;
	this.folio = folio;
	this.companies = companies;
	this.storage = storage;
	this.receiptDate = receiptDate;
	this.comments = comments;
	this.receiptBy = receiptBy;
    }

    public Receipt(String folio, Set<CompanyScope> companies, Storage storage, Date receiptDate, String comments, String receiptBy) {
	this.folio = folio;
	this.companies = companies;
	this.storage = storage;
	this.receiptDate = receiptDate;
	this.comments = comments;
	this.receiptBy = receiptBy;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getFolio() {
	return folio;
    }

    public void setFolio(String folio) {
	this.folio = folio;
    }

    public Set<CompanyScope> getCompanies() {
	return companies;
    }

    public void setCompanies(Set<CompanyScope> companies) {
	this.companies = companies;
    }

    public Storage getStorage() {
	return storage;
    }

    public void setStorage(Storage storage) {
	this.storage = storage;
    }

    public Date getReceiptDate() {
	return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
	this.receiptDate = receiptDate;
    }

    public String getComments() {
	return comments;
    }

    public void setComments(String comments) {
	this.comments = comments;
    }

    public String getReceiptBy() {
	return receiptBy;
    }

    public void setReceiptBy(String receiptBy) {
	this.receiptBy = receiptBy;
    }

    public Date getCreatedOn() {
	return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
    }

    public String getArrivalFolio() {
	return arrivalFolio;
    }

    public void setArrivalFolio(String arrivalFolio) {
	this.arrivalFolio = arrivalFolio;
    }

    public Set<ReceiptDocument> getDocuments() {
	return documents;
    }

    public void setDocuments(Set<ReceiptDocument> documents) {
	this.documents = documents;
    }

    public Set<ReceiptFreight> getFreights() {
	return freights;
    }

    public void setFreights(Set<ReceiptFreight> freights) {
	this.freights = freights;
    }

    public Set<ReceiptMerchandise> getMerchandise() {
	return merchandise;
    }

    public void setMerchandise(Set<ReceiptMerchandise> merchandise) {
	this.merchandise = merchandise;
    }

}
