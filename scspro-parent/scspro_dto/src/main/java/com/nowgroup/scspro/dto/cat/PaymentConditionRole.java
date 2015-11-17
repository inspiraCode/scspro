package com.nowgroup.scspro.dto.cat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.nowgroup.scspro.dto.BaseDTO;

@Entity
@Table(name = "sys_payment_condition_role", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = "PAY_CND_ROLE_NAME") })
public class PaymentConditionRole implements BaseDTO {
    private static final long serialVersionUID = -1944670551216075792L;

    public static final String FREIGHT_ROLE = "freight";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAY_CND_ROLE_ID", unique = true, nullable = false)
    private int id;

    @Column(name = "PAY_CND_ROLE_NAME", unique = true, nullable = false)
    private String name;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (!(obj instanceof PaymentConditionRole))
	    return false;

	PaymentConditionRole oRole = (PaymentConditionRole) obj;
	if (this.id == oRole.getId())
	    return true;
	return false;
    }

    @Override
    public int hashCode() {
	int tmp = 0;
	tmp = (id + name).hashCode();
	return tmp;
    }
}
