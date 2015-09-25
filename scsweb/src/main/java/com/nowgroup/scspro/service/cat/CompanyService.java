package com.nowgroup.scspro.service.cat;

import java.util.List;

import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.dto.geo.State;

public interface CompanyService {

    void addCompany(Company company);

    void deleteCompany(Company company);

    void deleteCompanies(List<Company> company);

    void updateCompany(Company company);

    Company getCompanyById(int id);

    State getStateInCompanyId(int id);

    List<Company> getCompanies();

    List<CompanyScope> getCompanyScope(int id);

    List<Company> getCompaniesByScope(String scope);

}
