package com.nowgroup.scspro.dao.cat;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dto.cat.CompanyScope;

public interface CompanyScopeDAO extends BaseDAO<CompanyScope> {

    public static final String QUERY_SCOPE_BY_COMPANY_AND_NAME = "SELECT scope.id FROM CompanyScope scope JOIN scope.companyRole role JOIN scope.company company "
	    + "WHERE role.name = :scopeName AND company.id = :companyId";

    CompanyScope getByCompanyAndRole(int companyId, String scopeName);

}
