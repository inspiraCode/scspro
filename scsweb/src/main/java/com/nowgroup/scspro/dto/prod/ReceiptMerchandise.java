package com.nowgroup.scspro.dto.prod;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.nowgroup.scspro.dto.BaseDTO;
import com.nowgroup.scspro.dto.cat.UNLabel;
import com.nowgroup.scspro.dto.sys.MeasurementUnit;

@Entity
@Indexed
@Table(name = "prod_receipt_goods", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(name = "PRT_RECEIPT_ITEM_IDX", columnNames = {
		"PRG_RECEIPT_ID", "PRG_ITEM" }) })
public class ReceiptMerchandise implements BaseDTO {
	private static final long serialVersionUID = -8547651534312912575L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRG_ID", nullable = false, unique = true)
	private int id;

	@Field
	@Column(name = "PRG_ITEM", nullable = false)
	private String item;

	@Field
	@Column(name = "PRG_ITEM", nullable = false)
	private int quantity = 0;

	@Field
	@Column(name = "PRG_WOODEN", nullable = false)
	private boolean wooden = false;

	@Field
	@Column(name = "PRG_COMMENTS")
	private String comments;

	@Column(name = "PRG_WEIGHT_POUNDS", precision = 7, scale = 3, nullable = false)
	private BigDecimal pounds = new BigDecimal(0);

	@Column(name = "PRG_WEIGHT_POUNDS", precision = 7, scale = 3, nullable = false)
	private BigDecimal kilos = new BigDecimal(0);

	@IndexedEmbedded
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PRG_RECEIPT_ID", nullable = false)
	private Receipt receipt;

	@IndexedEmbedded
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PRG_LOAD_MU", nullable = false)
	private MeasurementUnit loadMeasurementUnit;

	@IndexedEmbedded
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PRG_IDUN", nullable = false)
	private UNLabel unLabel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPounds() {
		return pounds;
	}

	public void setPounds(BigDecimal pounds) {
		this.pounds = pounds;
	}

	public BigDecimal getKilos() {
		return kilos;
	}

	public void setKilos(BigDecimal kilos) {
		this.kilos = kilos;
	}

	public boolean isWooden() {
		return wooden;
	}

	public void setWooden(boolean wooden) {
		this.wooden = wooden;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public MeasurementUnit getLoadMeasurementUnit() {
		return loadMeasurementUnit;
	}

	public void setLoadMeasurementUnit(MeasurementUnit loadMeasurementUnit) {
		this.loadMeasurementUnit = loadMeasurementUnit;
	}

	public UNLabel getUnLabel() {
		return unLabel;
	}

	public void setUnLabel(UNLabel unLabel) {
		this.unLabel = unLabel;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof ReceiptMerchandise))
			return false;

		ReceiptMerchandise oRG = (ReceiptMerchandise) obj;
		if (this.id == oRG.getId())
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		int tmp = 0;
		tmp = (id + item).hashCode();
		return tmp;
	}

}
