package com.nowgroup.scspro.service.cat;

import com.nowgroup.scspro.dto.cat.CompanyRole;
import com.nowgroup.scspro.dto.cat.CompanyScope;

public interface CompanyScopeService {
    void removeScope(CompanyScope scope);

    int addScope(CompanyScope scope);

    CompanyRole getRoleInCompanyScope(int id);
}
