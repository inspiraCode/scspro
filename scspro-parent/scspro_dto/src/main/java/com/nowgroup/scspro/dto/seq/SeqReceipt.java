package com.nowgroup.scspro.dto.seq;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nowgroup.scspro.dto.BaseDTO;

@Entity
@Table(name = "seq_receipt", catalog = "supply_chain")
public class SeqReceipt implements BaseDTO {
    private static final long serialVersionUID = 7633468126979032209L;
    
    public static final String RECEIPT_SEQUENCE_PREFIX = "RC";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ_ID", nullable = false, unique = true)
    private int id;

    @Column(name = "SEQ_BY", nullable = false)
    private int seqBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="SEQ_WHEN", updatable = false, insertable=false, nullable = true)
    private Date seqWhen = new Date();

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

    public Date getSeqWhen() {
	return seqWhen;
    }

    public void setSeqWhen(Date seqWhen) {
	this.seqWhen = seqWhen;
    }

}
