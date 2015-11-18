package com.nowgroup.scspro.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.nowgroup.scspro.dto.cat.Tariff;
import com.nowgroup.scspro.jsf.beans.BaseFacesReporteableBean;
import com.nowgroup.scspro.model.cat.TariffModel;
import com.nowgroup.scspro.service.BaseService;
import com.nowgroup.scspro.service.cat.TariffService;

@ManagedBean
@SessionScoped
public class TariffBean extends BaseFacesReporteableBean<Tariff> {
    private static final long serialVersionUID = -7902524196683072658L;
    
    @ManagedProperty("#{tariffService}")
    private TariffService tariffService;
    
    private TariffModel tariff = new TariffModel();

    public TariffBean() {
	super(new TariffModel());
    }

    public TariffService getTariffService() {
	return tariffService;
    }

    public void setTariffService(TariffService tariffService) {
	super.setService((BaseService<Tariff>) tariffService);
	this.tariffService = tariffService;
    }

    public TariffModel getTariff() {
	return tariff;
    }

    public void setTariff(TariffModel tariff) {
	super.setModel(tariff);
	this.tariff = tariff;
    }
}
