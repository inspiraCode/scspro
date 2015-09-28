package com.nowgroup.scspro.dao.hibernate.geo;

import com.nowgroup.scspro.dao.geo.StateDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.geo.State;

public class StateDAOHibernate extends BaseHibernateDAO<State> implements StateDAO {

    public StateDAOHibernate() {
	super(State.class);
    }

    public int getCountryIdInState(int stateId) {
	State stateInDb = get(stateId);
	if (stateInDb != null) {
	    return stateInDb.getCountry().getId();
	}
	return 0;
    }
}
