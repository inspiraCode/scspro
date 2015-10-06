package com.nowgroup.scspro.jsf.beans.cat.un;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

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
    private static final Logger log = Logger.getLogger(UNLabelBean.class.getName());

    @ManagedProperty("#{UNLabelService}")
    private UNLabelService labelService;

    public UNLabelBean() {
	super(new UNLabelModel());
    }

    @Override
    public List<Modeleable<UNLabel>> getAll() throws InstantiationException, IllegalAccessException {
	List<Modeleable<UNLabel>> result = super.getAll();
	List<UNLabelModel> temp = new LinkedList<UNLabelModel>();

	log.info("Reading all UNLabels (" + result.size() + ")");
	for (Modeleable<UNLabel> item : result) {
	    UNLabelModel itemModel = (UNLabelModel) item;
	    log.debug("Adding " + itemModel + " to getAll");
	    temp.add(itemModel);
	}

	Collections.sort(temp);

	result.clear();
	result.addAll(temp);
	log.info("Resolving " + result.size() + " as getAll result.");

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
