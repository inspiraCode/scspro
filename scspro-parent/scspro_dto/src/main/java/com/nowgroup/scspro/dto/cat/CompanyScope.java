package com.nowgroup.scspro.dto.cat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.nowgroup.scspro.dto.BaseDTO;

@Indexed
@Entity
@Table(name = "cross_company_role", catalog = "supply_chain")
public class CompanyScope implements BaseDTO {
    private static final long serialVersionUID = -1440418445301190014L;

    public static final String SENDER_SCOPE = "sender";
    public static final String RECEIVER_SCOPE = "receiver";
    public static final String SELLER_SCOPE = "seller";
    public static final String PURCHASER_SCOPE = "purchaser";
    public static final String FREIGHTER_SCOPE = "freighter";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CROSS_COMPANY_ROLE_ID", nullable = false, unique = true)
    private int id;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ROLE_ID")
    private CompanyRole companyRole;

    public CompanyScope() {
    }

    public CompanyScope(int id, Company company, CompanyRole companyRole) {
	this.id = id;
	this.company = company;
	this.companyRole = companyRole;
    }

    public CompanyScope(Company company, CompanyRole companyRole) {
	this.company = company;
	this.companyRole = companyRole;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Company getCompany() {
	return company;
    }

    public void setCompany(Company company) {
	this.company = company;
    }

    public CompanyRole getCompanyRole() {
	return companyRole;
    }

    public void setCompanyRole(CompanyRole companyRole) {
	this.companyRole = companyRole;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (!(obj instanceof CompanyScope))
	    return false;

	CompanyScope oCi = (CompanyScope) obj;
	return this.id == oCi.id;
    }

}
