package com.nowgroup.scspro.spring.service.geo;

import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.BaseDAO;
import com.nowgroup.scspro.dao.hibernate.geo.CountryDAOHibernate;
import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.service.geo.CountryService;
import com.nowgroup.scspro.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class CountryServiceImpl extends BaseSpringService<Country> implements CountryService {
    private CountryDAOHibernate countryDAO;

    public List<Country> getCountries() {
	return getCountryDAO().getAll();
    }

    public List<State> getStatesByCountry(int countryId) {
	List<State> statesInCountry = getCountryDAO().getStatesByCountry(countryId);
	Collections.sort(statesInCountry);
	return statesInCountry;
    }

    public CountryDAOHibernate getCountryDAO() {
	return countryDAO;
    }

    public void setCountryDAO(CountryDAOHibernate countryDAO) {
	super.setDaoFactory((BaseDAO<Country>) countryDAO);
	this.countryDAO = countryDAO;
    }

}
