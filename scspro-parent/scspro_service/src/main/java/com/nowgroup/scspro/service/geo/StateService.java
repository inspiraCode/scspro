package com.nowgroup.scspro.service.geo;

import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.service.BaseService;

public interface StateService extends BaseService<State> {
    int getCountryIdInState(int stateId);
}
