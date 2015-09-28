package com.nowgroup.scspro.dto.sys;

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
import com.nowgroup.scspro.dto.geo.State;

@Indexed
@Entity
@Table(name = "cat_storage", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = "STORAGE_CODE"),
	@UniqueConstraint(columnNames = "STORAGE_NAME") })
public class Storage implements BaseDTO {
    private static final long serialVersionUID = -2077370864453678425L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORAGE_ID")
    private int id;

    @Field
    @Column(name = "STORAGE_NAME")
    private String name;

    @Field
    @Column(name = "STORAGE_CODE")
    private String code;

    @Field
    @Column(name = "STORAGE_STREET_ADDRESS")
    private String addressStreet;

    @Field
    @Column(name = "STORAGE_ADDITIONAL_ADDRESS")
    private String addressAdditional;

    @Field
    @Column(name = "STORAGE_CITY")
    private String city;

    @Field
    @Column(name = "STORAGE_ZIP")
    private String zip;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORAGE_STATE", nullable = true)
    private State state;

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

    public String getAddressStreet() {
	return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
	this.addressStreet = addressStreet;
    }

    public String getAddressAdditional() {
	return addressAdditional;
    }

    public void setAddressAdditional(String addressAdditional) {
	this.addressAdditional = addressAdditional;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getZip() {
	return zip;
    }

    public void setZip(String zip) {
	this.zip = zip;
    }

    public State getState() {
	return state;
    }

    public void setState(State state) {
	this.state = state;
    }

    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	sb.append("id :" + id);
	sb.append(", name : '" + name + "'");
	sb.append(", code : '" + code + "'");
	sb.append(", addressStreet : '" + addressStreet + "'");
	sb.append(", addressAdditional : '" + addressAdditional + "'");
	sb.append(", city : '" + city + "'");
	sb.append(", zip : '" + zip + "'");
	sb.append("}");
	return sb.toString();
    }
}
