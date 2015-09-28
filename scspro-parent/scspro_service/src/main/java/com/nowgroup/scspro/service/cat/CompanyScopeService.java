package com.nowgroup.scspro.service.cat;

import com.nowgroup.scspro.dto.cat.CompanyRole;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.service.BaseService;

public interface CompanyScopeService extends BaseService<CompanyScope> {
    CompanyRole getRoleInCompanyScope(int id);

    CompanyScope getByCompanyAndRole(int companyId, String scopeName);
}
