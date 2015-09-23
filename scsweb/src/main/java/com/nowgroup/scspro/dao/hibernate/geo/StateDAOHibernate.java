package com.nowgroup.scspro.dao.hibernate.geo;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.geo.StateDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;

@Repository("stateDAO")
public class StateDAOHibernate extends BaseHibernateDAO<State> implements StateDAO {

    public StateDAOHibernate() {
	super(State.class);
    }

    public Country getCountryInState(int stateId) {
	return get(stateId).getCountry();
    }
}
