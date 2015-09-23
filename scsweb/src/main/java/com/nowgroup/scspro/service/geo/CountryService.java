package com.nowgroup.scspro.service.geo;

import java.util.List;
import java.util.Map;

import com.nowgroup.scspro.dto.geo.Country;

public interface CountryService {
    List<Country> getCountries();

    Map<String, Integer> getStatesByCountry(int countryId);
}
