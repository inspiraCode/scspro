package com.nowgroup.scspro.dto.cat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.nowgroup.scspro.dto.BaseDTO;

@Indexed
@Entity
@Table(name = "cat_company_role", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = "COMPANY_ROLE_NAME") })
public class CompanyRole implements BaseDTO {
    private static final long serialVersionUID = -7159142805459683720L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ROLE_ID", unique = true, nullable = false)
    private int id;

    @Field
    @Column(name = "COMPANY_ROLE_NAME", unique = true, nullable = false)
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
    public String toString() {
	return "{id:" + id + ";name:" + name + ";}";
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (!(obj instanceof CompanyRole))
	    return false;

	CompanyRole oRole = (CompanyRole) obj;
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
