package com.nowgroup.scspro.jsf.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.nowgroup.scspro.service.SysConfigService;

@SessionScoped
@ManagedBean(name = "avatarBean")
public class AvatarBean implements Serializable {
    private static final long serialVersionUID = -5624309050491278217L;

    private static Logger log = Logger.getLogger(AvatarBean.class.getName());

    @ManagedProperty("#{sysConfigService}")
    private SysConfigService configService;

    public StreamedContent getAvatar() throws FileNotFoundException {
	StreamedContent result = new DefaultStreamedContent();
	FacesContext context = FacesContext.getCurrentInstance();
	if (context.getCurrentPhaseId() != PhaseId.RENDER_RESPONSE) {
	    log.debug("Phase: " + context.getCurrentPhaseId());
	    String userId = context.getExternalContext().getRequestParameterMap().get("userId");

	    if (userId == null) {
		log.debug("generic image loading");
		userId = "generic";
	    }
	    // browser is requesting the image
	    log.debug("Retrieving avatar image for user: " + userId);
	    File file = new File(configService.getAvatarPath() + "/" + userId + ".jpg");

	    if (!file.exists())
		file = new File(configService.getAvatarPath() + "/generic.jpg");

	    log.debug("Retrieving avatar image: " + file.getAbsolutePath());

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
