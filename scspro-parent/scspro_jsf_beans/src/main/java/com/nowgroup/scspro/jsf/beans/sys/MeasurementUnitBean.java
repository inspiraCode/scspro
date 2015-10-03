package com.nowgroup.scspro.jsf.beans.sys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.DragDropEvent;

import com.nowgroup.scspro.dto.sys.MeasurementUnit;
import com.nowgroup.scspro.dto.sys.MeasurementUnitRole;
import com.nowgroup.scspro.jsf.beans.BaseFacesBean;
import com.nowgroup.scspro.model.sys.MeasurementUnitModel;
import com.nowgroup.scspro.model.sys.MeasurementUnitRoleModel;
import com.nowgroup.scspro.service.sys.MeasurementUnitRoleService;
import com.nowgroup.scspro.service.sys.MeasurementUnitService;

@ManagedBean
@SessionScoped
public class MeasurementUnitBean extends BaseFacesBean<MeasurementUnit> {
    private static final long serialVersionUID = 3950374829927416797L;
    private static Logger log = Logger.getLogger(MeasurementUnitBean.class.getName());

    private MeasurementUnit measurementUnit = new MeasurementUnit();
    private List<MeasurementUnitRoleModel> rolesList = new ArrayList<MeasurementUnitRoleModel>();
    private List<MeasurementUnitRoleModel> availableRoles = new ArrayList<MeasurementUnitRoleModel>();

    @ManagedProperty("#{measurementUnitService}")
    private MeasurementUnitService measurementUnitServiceImpl;

    @ManagedProperty("#{measurementUnitRoleService}")
    private MeasurementUnitRoleService measurementUnitRoleServiceImpl;

    @ManagedProperty("#{i18n_measurement_unit}")
    private ResourceBundle measurementUnitBundle;
    
    public MeasurementUnitBean() {
	super(new MeasurementUnitModel());
    }

    public List<MeasurementUnitModel> getMeasurementUnitList() {
	if (measurementUnitBundle == null) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    setMeasurementUnitBundle(context.getApplication().getResourceBundle(context, "i18n_measurement_unit"));
	    log.debug("Got forced resource bundle: " + measurementUnitBundle);
	}
	// Translate roles
	List<MeasurementUnitModel> result = new ArrayList<MeasurementUnitModel>();
	List<MeasurementUnit> source = measurementUnitServiceImpl.getAll();

	for (MeasurementUnit mu : source) {
	    MeasurementUnitModel mum = new MeasurementUnitModel(mu);
	    List<MeasurementUnitRole> murList = measurementUnitServiceImpl.getMeasurementUnitRoles(mu.getId());
	    String displayRoles = "";
	    for (MeasurementUnitRole mur : murList) {
		displayRoles += ", " + measurementUnitBundle.getString("measurement-unit.profiles." + mur.getName().toLowerCase());
	    }
	    if (displayRoles.length() > 2) {
		displayRoles = displayRoles.substring(2);
	    }

	    //mum.setDisplayRoles(displayRoles);
	    result.add(mum);
	}

	return result;
    }

    public String addNew() {
	this.measurementUnit = new MeasurementUnit();
	rolesList = new ArrayList<MeasurementUnitRoleModel>();
	return "measurement-unit";
    }

    public String showList() {
	return "measurement-unit-list";
    }

    public String remove(MeasurementUnit item) {
	try {
	    MeasurementUnit clone = new MeasurementUnit();
	    clone.setId(item.getId());
	    clone.setCode(item.getCode());
	    clone.setName(item.getName());
	    getMeasurementUnitServiceImpl().delete(clone);
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    String errorMessage = measurementUnitBundle.getString("measurement-unit.remove.error");
	    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, e.getMessage());
	    FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	return "measurement-unit-list";
    }

    public String uploadMeasurementUnit() {
	try {
	    if (getMeasurementUnit().getId() != 0) {
		getMeasurementUnitServiceImpl().update(measurementUnit);
	    } else {
		Set<MeasurementUnitRole> newllyAssignedRoles = new HashSet<MeasurementUnitRole>();
		for (MeasurementUnitRoleModel murm : rolesList) {
		    MeasurementUnitRole mur = new MeasurementUnitRole();
		    mur.setId(murm.getId());
		    mur.setName(murm.getName());
		    newllyAssignedRoles.add(mur);
		}
		measurementUnit.setRoles(newllyAssignedRoles);
		getMeasurementUnitServiceImpl().add(measurementUnit);
	    }
	    return "success";
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    String errorMessage = measurementUnitBundle.getString("measurement-unit.save.error");
	    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, e.getMessage());
	    FacesContext.getCurrentInstance().addMessage(null, fm);
	    return "";
	}
    }

    public void onRemoveRole(DragDropEvent event) {
	HtmlPanelGroup availableRoles = (HtmlPanelGroup) event.getComponent().findComponent("selectedRoles");
	if (availableRoles != null) {
	    availableRoles.invokeOnComponent(FacesContext.getCurrentInstance(), event.getDragId(), new ContextCallback() {

		public void invokeContextCallback(FacesContext context, UIComponent target) {
		    HtmlPanelGroup draggedItem = (HtmlPanelGroup) target;
		    MeasurementUnitRoleModel item = draggedItem != null ? (MeasurementUnitRoleModel) draggedItem.getAttributes().get("measurementRole")
			    : new MeasurementUnitRoleModel();
		    try {
			log.debug("selected role for removal:" + item);
			removeRole(item);
			String errorMessage = measurementUnitBundle.getString("measurement-unit.role.remove.success");
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, errorMessage, null);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		    } catch (Exception e) {
			log.error(e.getMessage(), e);
			String errorMessage = measurementUnitBundle.getString("measurement-unit.role.remove.error");
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, fm);
		    }
		}
	    });
	} else {
	    String errorMsg = measurementUnitBundle.getString("measurement-unit.role.remove.error");
	    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg, null);
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	}
    }

    public void onAddRole(DragDropEvent event) {
	HtmlPanelGroup availableRoles = (HtmlPanelGroup) event.getComponent().findComponent("availableRoles");
	if (availableRoles != null) {
	    availableRoles.invokeOnComponent(FacesContext.getCurrentInstance(), event.getDragId(), new ContextCallback() {

		public void invokeContextCallback(FacesContext context, UIComponent target) {
		    HtmlPanelGroup draggedItem = (HtmlPanelGroup) target;
		    MeasurementUnitRoleModel item = draggedItem != null ? (MeasurementUnitRoleModel) draggedItem.getAttributes().get("measurementRole")
			    : new MeasurementUnitRoleModel();
		    try {
			log.debug("selected role for adding:" + item);
			addRole(item);
			String errorMessage = measurementUnitBundle.getString("measurement-unit.role.add.success");
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, errorMessage, null);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		    } catch (Exception e) {
			log.error(e.getMessage(), e);
			String errorMessage = measurementUnitBundle.getString("measurement-unit.role.add.error");
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, fm);
		    }
		}
	    });
	} else {
	    String errorMsg = measurementUnitBundle.getString("measurement-unit.add.remove.error");
	    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg, null);
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	}
    }

    public String edit(MeasurementUnit item) {
	MeasurementUnit clone = new MeasurementUnit();
	clone.setId(item.getId());
	clone.setName(item.getName());
	clone.setCode(item.getCode());

	this.setMeasurementUnit(clone);
	return "measurement-unit";
    }

    public MeasurementUnit getMeasurementUnit() {
	return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
	this.measurementUnit = measurementUnit;
    }

    public List<MeasurementUnitRoleModel> getRolesList() {
	if (measurementUnitBundle == null) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    setMeasurementUnitBundle(context.getApplication().getResourceBundle(context, "i18n_measurement_unit"));
	    log.debug("Got forced resource bundle: " + measurementUnitBundle);
	}
	if (this.getMeasurementUnit().getId() != 0) {
	    rolesList = new ArrayList<MeasurementUnitRoleModel>();
	    List<MeasurementUnitRole> mur = getMeasurementUnitServiceImpl().getMeasurementUnitRoles(this.getMeasurementUnit().getId());

	    // Translate roles
	    for (MeasurementUnitRole role : mur) {
		MeasurementUnitRoleModel murm = new MeasurementUnitRoleModel();
		murm.setId(role.getId());
		murm.setName(role.getName());
		//murm.setDisplayName(measurementUnitBundle.getString("measurement-unit.profiles." + role.getName().toLowerCase()));
		rolesList.add(murm);
	    }
	    return rolesList;
	} else {
	    return rolesList;
	}
    }

    public void setRolesList(List<MeasurementUnitRoleModel> rolesList) {
	this.rolesList = rolesList;
    }

    public List<MeasurementUnitRoleModel> getAvailableRoles() {
	if (measurementUnitBundle == null) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    setMeasurementUnitBundle(context.getApplication().getResourceBundle(context, "i18n_measurement_unit"));
	    log.debug("Got forced resource bundle: " + measurementUnitBundle);
	}
	// Retrieve all roles from database
	List<MeasurementUnitRole> allRoles = measurementUnitRoleServiceImpl.getAll();

	for (MeasurementUnitRoleModel usedRole : getRolesList()) {
	    log.debug("removing used role: " + usedRole);
	    MeasurementUnitRole removable = new MeasurementUnitRole();
	    removable.setId(usedRole.getId());
	    removable.setName(usedRole.getName());
	    allRoles.remove(usedRole);
	}

	// Translate available roles.
	availableRoles = new ArrayList<MeasurementUnitRoleModel>();
	for (MeasurementUnitRole role : allRoles) {
	    MeasurementUnitRoleModel murm = new MeasurementUnitRoleModel();
	    murm.setId(role.getId());
	    murm.setName(role.getName());
	    String murmCode = role.getName().toLowerCase();
	    String translated = measurementUnitBundle.getString("measurement-unit.profiles." + murmCode);
	    //murm.setDisplayName(translated);
	    availableRoles.add(murm);
	}

	return availableRoles;
    }

    public void setAvailableRoles(List<MeasurementUnitRoleModel> availableRoles) {
	this.availableRoles = availableRoles;
    }

    public MeasurementUnitService getMeasurementUnitServiceImpl() {
	return measurementUnitServiceImpl;
    }

    public void setMeasurementUnitServiceImpl(MeasurementUnitService measurementUnitServiceImpl) {
	this.measurementUnitServiceImpl = measurementUnitServiceImpl;
    }

    public ResourceBundle getMeasurementUnitBundle() {
	return measurementUnitBundle;
    }

    public void setMeasurementUnitBundle(ResourceBundle measurementUnitBundle) {
	this.measurementUnitBundle = measurementUnitBundle;
    }

    public MeasurementUnitRoleService getMeasurementUnitRoleServiceImpl() {
	return measurementUnitRoleServiceImpl;
    }

    public void setMeasurementUnitRoleServiceImpl(MeasurementUnitRoleService measurementUnitRoleServiceImpl) {
	this.measurementUnitRoleServiceImpl = measurementUnitRoleServiceImpl;
    }

    private void removeRole(MeasurementUnitRoleModel item) {
	if (this.getMeasurementUnit().getId() != 0)
	    removeMeasurementUnitRole(item);
	else {
	    rolesList.remove(item);
	}
    }

    private void removeMeasurementUnitRole(MeasurementUnitRole item) {
	measurementUnitServiceImpl.removeMeasurementUnitRole(this.getMeasurementUnit().getId(), item);
	rolesList.remove(item);
    }

    private void addRole(MeasurementUnitRoleModel item) {
	if (this.getMeasurementUnit().getId() != 0) {
	    addMeasurementUnitRole(item);
	}
	rolesList.add(item);
    }

    public void addMeasurementUnitRole(MeasurementUnitRole item) {
	measurementUnitServiceImpl.addMeasurementUnitRole(this.getMeasurementUnit().getId(), item);
    }

}
