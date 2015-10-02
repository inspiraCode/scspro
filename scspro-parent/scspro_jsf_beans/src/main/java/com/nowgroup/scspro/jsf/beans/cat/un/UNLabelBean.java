package com.nowgroup.scspro.jsf.beans.cat.un;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.nowgroup.scspro.dto.cat.UNLabel;
import com.nowgroup.scspro.jsf.beans.BaseFacesBean;
import com.nowgroup.scspro.model.Modeleable;
import com.nowgroup.scspro.model.cat.UNLabelModel;

@ManagedBean
@RequestScoped
public class UNLabelBean extends BaseFacesBean<UNLabel> {
    private static final long serialVersionUID = 6056357793984342913L;
    public UNLabelBean(Modeleable<UNLabel> modelType) {
	super(new UNLabelModel());
    }
}
