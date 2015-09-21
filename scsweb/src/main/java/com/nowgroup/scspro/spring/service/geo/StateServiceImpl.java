package com.nowgroup.scspro.spring.service.geo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.hibernate.geo.StateDAOHibernate;
import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.service.geo.StateService;

@Service("geoStateService")
@Transactional(readOnly = true)
public class StateServiceImpl implements StateService {
	private static final Logger log = Logger.getLogger(StateServiceImpl.class
			.getName());

	@Autowired
	private StateDAOHibernate dao;

	public StateDAOHibernate getDao() {
		return dao;
	}

	public void setDao(StateDAOHibernate dao) {
		this.dao = dao;
	}

	public State getStateById(int stateId) {
		return dao.get(stateId);
	}

	public Country getCountryInState(int stateId) {
		Country dbCountry = dao.getCountryInState(stateId);
		log.debug("found country in db:" + dbCountry);
		return dbCountry;
	}

}
