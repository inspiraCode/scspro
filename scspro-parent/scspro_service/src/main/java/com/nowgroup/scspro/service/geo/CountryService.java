package com.nowgroup.scspro.service.geo;

import java.util.List;

import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.service.BaseService;

public interface CountryService extends BaseService<Country> {
    List<State> getStatesByCountry(int countryId);
}
