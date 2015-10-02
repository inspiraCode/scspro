package com.nowgroup.scspro.jsf.beans.cat.company;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.service.cat.CompanyService;

@ManagedBean
@RequestScoped
public class CompanyClassifierBean implements Serializable {
    private static final long serialVersionUID = -8484705273446991914L;
    //private static final Logger logger = Logger.getLogger(CompanyClassifierBean.class.getName());

    @ManagedProperty("#{companyService}")
    private CompanyService companyServiceImpl;

    public List<Company> getSenderCompanies() {
	return getCompaniesByScope(CompanyScope.SENDER_SCOPE);
    }

    public List<Company> getReceiverCompanies() {
	return getCompaniesByScope(CompanyScope.RECEIVER_SCOPE);
    }

    public List<Company> getSellerCompanies() {
	return getCompaniesByScope(CompanyScope.SELLER_SCOPE);
    }

    public List<Company> getPurchaserCompanies() {
	return getCompaniesByScope(CompanyScope.PURCHASER_SCOPE);
    }

    public List<Company> getFreighterCompanies() {
	return getCompaniesByScope(CompanyScope.FREIGHTER_SCOPE);
    }

    private List<Company> getCompaniesByScope(String scope) {
	List<Company> result = new LinkedList<Company>();
	for (Company company : getCompanyServiceImpl().getCompaniesByScope(scope)) {
	    result.add(company);
	}
	Collections.sort(result);
	return result;
    }

    public CompanyService getCompanyServiceImpl() {
	return companyServiceImpl;
    }

    public void setCompanyServiceImpl(CompanyService companyServiceImpl) {
	this.companyServiceImpl = companyServiceImpl;
    }

}
