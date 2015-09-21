package com.nowgroup.scspro.dao.geo;

import java.util.List;

import com.nowgroup.scspro.dao.common.BaseDAO;
import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;

public interface CountryDAO extends BaseDAO<Country> {
	String QUERY_STATES_BY_COUNTRY = "from Country c where c.id = :countryId";

	/**
	 * Get states list in a given country by Id
	 * @param countryId
	 * @return List of related states.
	 */
	public List<State> getStatesByCountry(int countryId);
}
