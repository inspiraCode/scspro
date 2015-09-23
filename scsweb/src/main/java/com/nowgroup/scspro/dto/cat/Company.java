package com.nowgroup.scspro.dto.cat;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.nowgroup.scspro.dto.BaseDTO;
import com.nowgroup.scspro.dto.geo.State;

@Indexed
@Entity
@Table(name = "cat_company", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(name = "COMPANY_NAME_IDX", columnNames = "COMPANY_NAME") })
public class Company implements BaseDTO {
    private static final long serialVersionUID = -5789578134137973419L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID", unique = true, nullable = false)
    private int id;

    @Field
    @Column(name = "COMPANY_NAME", unique = true, nullable = false)
    private String name;

    @Field
    @Column(name = "COMPANY_ALIAS")
    private String alias;

    @Field
    @Column(name = "COMPANY_STREET_ADDRESS")
    private String addressStreet;

    @Field
    @Column(name = "COMPANY_ADDITIONAL_ADDRESS")
    private String addressAdditional;

    @Field
    @Column(name = "COMPANY_CITY")
    private String city;

    @Field
    @Column(name = "COMPANY_ZIP")
    private String zip;

    @Field
    @Column(name = "COMPANY_PHONE")
    private String phone;

    @Field
    @Column(name = "COMPANY_PHONE_SEC")
    private String phoneSecondary;

    @Field
    @Column(name = "COMPANY_FAX")
    private String fax;

    @Field
    @Column(name = "COMPANY_EMAIL")
    private String email;

    @Field
    @Column(name = "COMPANY_WEB")
    private String web;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_STATE", nullable = true)
    private State state;

    @IndexedEmbedded
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL)
    private Set<CompanyScope> companyScope = new HashSet<CompanyScope>();

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

    public String getAlias() {
	return alias;
    }

    public void setAlias(String alias) {
	this.alias = alias;
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

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getPhoneSecondary() {
	return phoneSecondary;
    }

    public void setPhoneSecondary(String phoneSecondary) {
	this.phoneSecondary = phoneSecondary;
    }

    public String getFax() {
	return fax;
    }

    public void setFax(String fax) {
	this.fax = fax;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getWeb() {
	return web;
    }

    public void setWeb(String web) {
	this.web = web;
    }

    public State getState() {
	return state;
    }

    public void setState(State state) {
	this.state = state;
    }

    public Set<CompanyScope> getCompanyScope() {
	return companyScope;
    }

    public void setCompanyScope(Set<CompanyScope> companyScope) {
	this.companyScope.clear();
	this.companyScope.addAll(companyScope);
    }

    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	sb.append("id :" + id);
	sb.append(", name : '" + name + "'");
	sb.append(", alias : '" + alias + "'");
	sb.append(", addressStreet : '" + addressStreet + "'");
	sb.append(", addressAdditional : '" + addressAdditional + "'");
	sb.append(", city : '" + city + "'");
	sb.append(", zip : '" + zip + "'");
	sb.append(", phone : '" + phone + "'");
	sb.append(", phoneSecundary : '" + phoneSecondary + "'");
	sb.append(", fax : '" + fax + "'");
	sb.append(", email : '" + email + "'");
	sb.append(", web : '" + web + "'");
	sb.append("}");
	return sb.toString();
    }
}
