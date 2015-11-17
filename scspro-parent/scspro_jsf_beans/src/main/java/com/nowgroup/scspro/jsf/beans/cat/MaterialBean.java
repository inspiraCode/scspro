package com.nowgroup.scspro.jsf.beans.cat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.nowgroup.scspro.dto.cat.Material;
import com.nowgroup.scspro.jsf.beans.BaseFacesReporteableBean;
import com.nowgroup.scspro.model.cat.MaterialModel;
import com.nowgroup.scspro.service.SysConfigService;

public class MaterialBean extends BaseFacesReporteableBean<Material> {
    private static final long serialVersionUID = 2525616182718998684L;
    private static Logger log = Logger.getLogger(MaterialBean.class.getName());

    @ManagedProperty("#{sysConfigService}")
    private SysConfigService configService;

    private MaterialModel selectedMaterial;

    public MaterialBean() {
	super(new MaterialModel());
    }

    public MaterialModel getSelectedMaterial() {
	return selectedMaterial;
    }

    public void setSelectedMaterial(MaterialModel selectedMaterial) {
	this.selectedMaterial = selectedMaterial;
	// TODO: Retrieve images from service
	this.selectedMaterial.getImages().add("0.jpg");
	this.selectedMaterial.getImages().add("1.jpg");
	this.selectedMaterial.getImages().add("2.jpg");
    }

    public StreamedContent getSelectedMaterialImage() throws FileNotFoundException {
	StreamedContent result = new DefaultStreamedContent();
	FacesContext context = FacesContext.getCurrentInstance();
	if (context.getCurrentPhaseId() != PhaseId.RENDER_RESPONSE) {
	    log.debug("Phase: " + context.getCurrentPhaseId());
	    String imageName = context.getExternalContext().getRequestParameterMap().get("imageName");

	    if (imageName == null) {
		log.debug("generic material image loading");
		imageName = "generic.jpg";
	    }
	    // browser is requesting the image
	    log.debug("Retrieving material image for user: " + imageName);
	    File file = new File(getConfigService().getMaterialImagePath() + "/" + selectedMaterial.getId() + "/" + imageName);

	    if (!file.exists())
		file = new File(getConfigService().getMaterialImagePath() + "/generic.jpg");

	    log.debug("Retrieving material image: " + file.getAbsolutePath());

	    result = new DefaultStreamedContent(new FileInputStream(file), "image/jpeg");
	}
	return result;
    }

    public SysConfigService getConfigService() {
	return configService;
    }

    public void setConfigService(SysConfigService configService) {
	this.configService = configService;
    }

}
