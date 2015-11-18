package com.nowgroup.scspro.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.nowgroup.scspro.dto.cat.TariffValidity;
import com.nowgroup.scspro.jsf.beans.BaseFacesBean;
import com.nowgroup.scspro.model.cat.TariffValidityModel;
import com.nowgroup.scspro.service.BaseService;
import com.nowgroup.scspro.service.cat.TariffValidityService;

@ManagedBean
@SessionScoped
public class TariffValidityBean extends BaseFacesBean<TariffValidity> {
    private static final long serialVersionUID = 1952615519036912096L;
    //private static Logger log = Logger.getLogger(TariffValidityBean.class.getName());

    @ManagedProperty("#{tariffValidityService}")
    private TariffValidityService tariffValidityService;
    
    private int countryId, measurementUnitId;
    private String description;

    public TariffValidityBean() {
	super(new TariffValidityModel());
    }

    public TariffValidityService getTariffValidityService() {
	return tariffValidityService;
    }

    public void setTariffValidityService(TariffValidityService tariffValidityService) {
	super.setService((BaseService<TariffValidity>) tariffValidityService);
	this.tariffValidityService = tariffValidityService;
    }

    public int getCountryId() {
	return countryId;
    }

    public void setCountryId(int countryId) {
	this.countryId = countryId;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public int getMeasurementUnitId() {
	return measurementUnitId;
    }

    public void setMeasurementUnitId(int measurementUnitId) {
	this.measurementUnitId = measurementUnitId;
    }

}
