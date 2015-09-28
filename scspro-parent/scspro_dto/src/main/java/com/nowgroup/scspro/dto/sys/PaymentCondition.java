package com.nowgroup.scspro.dto.sys;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.nowgroup.scspro.dto.BaseDTO;

@Indexed
@Entity
@Table(name = "sys_payment_condition", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = "PAY_CND_CODE") })
public class PaymentCondition implements BaseDTO {
    private static final long serialVersionUID = 4005765551264037029L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAY_CND_ID", nullable = false, unique = true)
    private int id;

    @Field
    @Column(name = "PAY_CND_CODE", nullable = false, unique = true)
    private String code;

    @Field
    @Column(name = "PAY_CND_DESCRIPTION", nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_cross_payment_condition_role", catalog = "supply_chain", joinColumns = { @JoinColumn(name = "PAY_CND_ID", nullable = false,
	    updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "PAY_CND_ROLE_ID", nullable = false, updatable = false) },
	    uniqueConstraints = { @UniqueConstraint(columnNames = { "PAY_CND_ID", "PAY_CND_ROLE_ID" }) })
    private Set<PaymentConditionRole> roles;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
	return "{id:" + id + ";code:" + code + ";description:" + description + ";}";
    }
}
