package com.nowgroup.scspro.dao.geo;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dto.geo.State;

public interface StateDAO extends BaseDAO<State> {
    String QUERY_STATE_BY_ID = "from State s where s.id = :stateId";

    /**
     * Retrieve the country record related to an state by id.
     * 
     * @param stateId
     * @return
     */
    int getCountryIdInState(int stateId);
}
