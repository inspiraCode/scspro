package com.nowgroup.scspro.service.cat;

import java.util.List;

import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.service.BaseService;

public interface CompanyService extends BaseService<Company> {

    void deleteCompanies(List<Company> company);

    int getStateIdInCompanyId(int id);

    List<CompanyScope> getCompanyScope(int id);

    List<Company> getCompaniesByScope(String scope);

}
