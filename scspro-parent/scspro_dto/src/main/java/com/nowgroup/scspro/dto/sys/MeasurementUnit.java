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
import org.hibernate.search.annotations.IndexedEmbedded;

import com.nowgroup.scspro.dto.BaseDTO;

@Indexed
@Entity
@Table(name = "sys_measurement_unit", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = "MU_NAME") })
public class MeasurementUnit implements BaseDTO {
    private static final long serialVersionUID = 2287186741350117934L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MU_ID", nullable = false, unique = true)
    private int id;

    @Field
    @Column(name = "MU_NAME", nullable = false, unique = true)
    private String name;

    @Field
    @Column(name = "MU_CODE", nullable = false)
    private String code;

    @IndexedEmbedded
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_cross_measurement_unit_role", catalog = "supply_chain", joinColumns = { @JoinColumn(name = "MU_ID", nullable = false,
	    updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "MU_ROLE_ID", nullable = false, updatable = false) },
	    uniqueConstraints = { @UniqueConstraint(columnNames = { "MU_ID", "MU_ROLE_ID" }) })
    private Set<MeasurementUnitRole> roles;

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

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public Set<MeasurementUnitRole> getRoles() {
	return roles;
    }

    public void setRoles(Set<MeasurementUnitRole> roles) {
	this.roles = roles;
    }
}
