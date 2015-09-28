package com.nowgroup.scspro.service.cat;

import java.util.List;

import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.service.BaseService;

public interface CompanyService extends BaseService<Company> {

    void deleteCompanies(List<Company> company);

    State getStateInCompanyId(int id);

    List<CompanyScope> getCompanyScope(int id);

    List<Company> getCompaniesByScope(String scope);

}
