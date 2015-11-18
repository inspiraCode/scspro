package com.nowgroup.scspro.jsf.beans.geo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.jsf.beans.BaseFacesBean;
import com.nowgroup.scspro.model.geo.CountryModel;
import com.nowgroup.scspro.service.BaseService;
import com.nowgroup.scspro.service.geo.CountryService;

@ManagedBean
@SessionScoped
public class CountryBean extends BaseFacesBean<Country> {
    private static final long serialVersionUID = 344834603184579601L;
    
    @ManagedProperty("#{countryService}")
    private CountryService countryService;
    
    public CountryBean(){
	super(new CountryModel());
    }

    public CountryService getCountryService() {
	return countryService;
    }

    public void setCountryService(CountryService countryService) {
	super.setService((BaseService<Country>) countryService);
	this.countryService = countryService;
    }
}
