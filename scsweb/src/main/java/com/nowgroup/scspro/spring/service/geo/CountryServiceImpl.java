package com.nowgroup.scspro.spring.service.geo;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nowgroup.scspro.dao.hibernate.geo.CountryDAOHibernate;
import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.service.geo.CountryService;

@Service
@Transactional(readOnly = true)
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDAOHibernate countryDAO;

    public List<Country> getCountries() {
	return getCountryDAO().getAll();
    }

    public Map<String, Integer> getStatesByCountry(int countryId) {
	Map<String, Integer> result = new HashMap<String, Integer>();
	List<State> statesInCountry = getCountryDAO().getStatesByCountry(countryId);
	Collections.sort(statesInCountry, new Comparator<State>() {
	    // sort by state name
	    public int compare(State o1, State o2) {
		return o1.getName().compareTo(o2.getName());
	    }
	});
	for (State s : getCountryDAO().getStatesByCountry(countryId)) {
	    result.put(s.getName(), s.getId());
	}
	return result;
    }

    public CountryDAOHibernate getCountryDAO() {
	return countryDAO;
    }

    public void setCountryDAO(CountryDAOHibernate countryDAO) {
	this.countryDAO = countryDAO;
    }

}
