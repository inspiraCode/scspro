package com.nowgroup.scspro.spring.service.geo;

import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.hibernate.geo.StateDAOHibernate;
import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.service.geo.StateService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class StateServiceImpl extends BaseSpringService<State> implements StateService {
    private StateDAOHibernate stateDAO;

    public Country getCountryInState(int stateId) {
	Country dbCountry = stateDAO.getCountryInState(stateId);
	return dbCountry;
    }

    public StateDAOHibernate getStateDAO() {
	return stateDAO;
    }

    public void setStateDAO(StateDAOHibernate stateDAO) {
	super.setDaoFactory((BaseDAO<State>) stateDAO);
	this.stateDAO = stateDAO;
    }

}
