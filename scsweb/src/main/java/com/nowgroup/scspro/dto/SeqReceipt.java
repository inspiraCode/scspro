package com.nowgroup.scspro.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seq_receipt", catalog="supply_chain")
public class SeqReceipt implements BaseDTO {
	private static final long serialVersionUID = 7633468126979032209L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SEQ_ID", nullable=false, unique=true)
	private int id;
	
	@Column(name="SEQ_BY", nullable=false)
	private int seqBy;
	

	public int getSeqBy() {
		return seqBy;
	}

	public void setSeqBy(int seqBy) {
		this.seqBy = seqBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
