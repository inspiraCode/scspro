package com.nowgroup.scspro.jsf.beans.cat.un;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.nowgroup.scspro.dto.cat.UNLabel;
import com.nowgroup.scspro.jsf.beans.BaseFacesBean;
import com.nowgroup.scspro.model.Modeleable;
import com.nowgroup.scspro.model.cat.UNLabelModel;
import com.nowgroup.scspro.service.BaseService;
import com.nowgroup.scspro.service.cat.UNLabelService;

@ManagedBean(name = "UNLabelBean")
@RequestScoped
public class UNLabelBean extends BaseFacesBean<UNLabel> {
    private static final long serialVersionUID = 6056357793984342913L;

    @ManagedProperty("#{UNLabelService}")
    private UNLabelService labelService;

    public UNLabelBean() {
	super(new UNLabelModel());
    }

    @Override
    public List<Modeleable<UNLabel>> getAll() throws InstantiationException, IllegalAccessException {
	List<Modeleable<UNLabel>> result = super.getAll();
	List<UNLabelModel> temp = new LinkedList<UNLabelModel>();

	for (Modeleable<UNLabel> item : temp) {
	    UNLabelModel itemModel = (UNLabelModel) item;
	    temp.add(itemModel);
	}

	Collections.sort(temp);

	result.clear();
	result.addAll(temp);

	return result;
    }

    public UNLabelService getLabelService() {
	return labelService;
    }

    public void setLabelService(UNLabelService labelService) {
	super.setService((BaseService<UNLabel>) labelService);
	this.labelService = labelService;
    }

}
