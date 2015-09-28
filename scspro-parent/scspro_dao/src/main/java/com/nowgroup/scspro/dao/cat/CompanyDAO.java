package com.nowgroup.scspro.dao.cat;

import java.util.List;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dto.cat.Company;

public interface CompanyDAO extends BaseDAO<Company> {
    public final static String QUERY_BY_SCOPE = "select company.id from Company company join company.companyScope scope join scope.companyRole role  where role.name = ?";

    List<Company> getCompaniesByScope(String scope);
}
