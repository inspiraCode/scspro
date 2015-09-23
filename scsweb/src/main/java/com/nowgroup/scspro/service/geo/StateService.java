package com.nowgroup.scspro.service.geo;

import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;

public interface StateService {
    State getStateById(int stateId);

    Country getCountryInState(int stateId);
}
