package com.nowgroup.scspro.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.nowgroup.scspro.dto.cat.Tariff;
import com.nowgroup.scspro.jsf.beans.BaseFacesReporteableBean;
import com.nowgroup.scspro.model.cat.TariffModel;

@ManagedBean
@SessionScoped
public class TariffBean extends BaseFacesReporteableBean<Tariff> {
    private static final long serialVersionUID = -7902524196683072658L;

    public TariffBean() {
	super(new TariffModel());
    }
}
