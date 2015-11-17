package com.nowgroup.scspro.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.nowgroup.scspro.dto.cat.TariffValidity;
import com.nowgroup.scspro.jsf.beans.BaseFacesBean;
import com.nowgroup.scspro.model.cat.TariffValidityModel;

@ManagedBean
@SessionScoped
public class TariffValidityBean extends BaseFacesBean<TariffValidity> {
    private static final long serialVersionUID = 1952615519036912096L;
    //private static Logger log = Logger.getLogger(TariffValidityBean.class.getName());
    
    public TariffValidityBean(){
	super(new TariffValidityModel());
    }
    
}
