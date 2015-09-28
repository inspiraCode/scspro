package com.nowgroup.scspro.service.geo;

import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.service.BaseService;

public interface StateService extends BaseService<State> {
    Country getCountryInState(int stateId);
}
