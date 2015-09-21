package com.nowgroup.scspro.dao.hibernate.geo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nowgroup.scspro.dao.geo.CountryDAO;
import com.nowgroup.scspro.dao.hibernate.BaseHibernateDAO;
import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;

@Repository("countryDAO")
public class CountryDAOHibernate extends BaseHibernateDAO<Country> implements
		CountryDAO {

	public CountryDAOHibernate() {
		super(Country.class);
	}

	public List<State> getStatesByCountry(int countryId) {
		List<State> result = new ArrayList<State>();
		Country c = (Country) conn().createQuery(QUERY_STATES_BY_COUNTRY)
				.setParameter("countryId", countryId).uniqueResult();
		for (State s : c.getStates()) {
			result.add(s);
		}
		result.sort(new Comparator<State>() {
			public int compare(State o1, State o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return result;
	}
}
