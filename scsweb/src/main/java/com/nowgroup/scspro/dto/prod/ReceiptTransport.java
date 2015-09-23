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

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.nowgroup.scspro.dto.BaseDTO;
import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.sys.PaymentCondition;

@Indexed
@Entity
@Table(name = "prod_receipt_transport", catalog = "supply_chain", uniqueConstraints = {
	@UniqueConstraint(name = "PRT_RECEIPT_GUIDE_IDX", columnNames = { "PRT_RECEIPT_ID", "PRT_GUIDE" }),
	@UniqueConstraint(name = "PRT_FREIGHTER_GUIDE_IDX", columnNames = { "PRT_GUIDE", "PRT_FREIGHTER" }) })
public class ReceiptTransport implements BaseDTO {
    private static final long serialVersionUID = 6289097662510497688L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRT_ID", nullable = false, unique = true)
    private int id;

    @Field
    @Column(name = "PRT_GUIDE", nullable = false)
    private String guide;

    @Field
    @Column(name = "PRT_VEHICLE")
    private String vehicle;

    @Field
    @Column(name = "PRT_COMMENTS")
    private String comments;

    @Temporal(TemporalType.DATE)
    @Column(name = "PRT_GUIDE_DATE", nullable = false)
    private Date guideDate;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRT_RECEIPT_ID", nullable = false)
    private Receipt receipt;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRT_FREIGHTER", nullable = false)
    private Company freighter;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRT_PAYMENT_CONDITION", nullable = false)
    private PaymentCondition paymentCondition;

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

    public String getGuide() {
	return guide;
    }

    public void setGuide(String guide) {
	this.guide = guide;
    }

    public Date getGuideDate() {
	return guideDate;
    }

    public void setGuideDate(Date guideDate) {
	this.guideDate = guideDate;
    }

    public Company getFreighter() {
	return freighter;
    }

    public void setFreighter(Company freighter) {
	this.freighter = freighter;
    }

    public PaymentCondition getPaymentCondition() {
	return paymentCondition;
    }

    public void setPaymentCondition(PaymentCondition paymentCondition) {
	this.paymentCondition = paymentCondition;
    }

    public String getVehicle() {
	return vehicle;
    }

    public void setVehicle(String vehicle) {
	this.vehicle = vehicle;
    }

    public String getComments() {
	return comments;
    }

    public void setComments(String comments) {
	this.comments = comments;
    }
}
