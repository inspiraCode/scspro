package com.nowgroup.scspro.jsf.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.nowgroup.scspro.dto.BaseDTO;
import com.nowgroup.scspro.model.Modeleable;
import com.nowgroup.scspro.service.BaseService;

public abstract class BaseFacesBean<T extends BaseDTO> extends PublisherFacesBean implements Serializable {
    private static final long serialVersionUID = 5405445169294708991L;
    private static Logger log = Logger.getLogger(BaseFacesBean.class.getName());
    private Class<Modeleable<T>> modelType;

    private BaseService<T> service;
    private List<Modeleable<T>> localList = new LinkedList<Modeleable<T>>();
    private List<Modeleable<T>> selectedList = new LinkedList<Modeleable<T>>();
    private Modeleable<T> model;
    private boolean forceDownload;

    private ResourceBundle msgBundle;

    @SuppressWarnings("unchecked")
    public BaseFacesBean(Modeleable<T> modelType) {
	this.modelType = (Class<Modeleable<T>>) modelType.getClass();
    }

    public void selectedItem(Modeleable<T> item) {
	if (item.isSelected())
	    selectedList.add(item);
	else
	    selectedList.remove(item);
    }

    public String removeSelected() {
	List<T> removeList = new LinkedList<T>();
	for (Modeleable<T> item : selectedList) {
	    removeList.add(item.demodelize());
	}
	log.debug("delete " + removeList.size() + " items from database");
	service.deleteAll(removeList);
	for (Modeleable<T> item : selectedList) {
	    localList.remove(item);
	}
	selectedList = new LinkedList<Modeleable<T>>();
	forceDownload = true;
	return "";
    }

    public T get(int id) {
	return service.get(id);
    }

    public List<Modeleable<T>> getAll() throws InstantiationException, IllegalAccessException {
	if (localList.isEmpty() || forceDownload) {
	    localList.clear();
	    for (T item : service.getAll()) {
		log.debug("adding " + item + " to localList model.");
		localList.add(modelType.newInstance().getModel(item));
	    }
	    return localList;
	} else
	    return localList;
    }

    public String upload() {
	T entity = model.demodelize();
	log.info("-- UPLOAD BEGIN: " + entity);
	String result = "";
	try {
	    if (entity.getId() == 0)
		add(entity);
	    else
		update(entity);

	    result = "success";
	} catch (org.springframework.dao.DataIntegrityViolationException e) {
	    log.error(e.getMessage(), e);
	    publishError(getMsgBundle().getString("save.duplicate"));
	    result = "failure";
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    publishError(getMsgBundle().getString("save.error"));
	    publishError(e.getLocalizedMessage());
	    result = "failure";
	}
	log.info("UPLOAD END --");
	return result;
    }

    public int add(T object) {
	log.debug("Add - BEGIN");
	int result = service.add(object);
	forceDownload = true;
	log.debug("Add - END");
	return result;
    }

    public void update(T object) {
	log.debug("Update - BEGIN");
	service.update(object);
	forceDownload = true;
	log.debug("Update - END");
    }

    @SuppressWarnings("unchecked")
    public String delete(T object) {
	try {
	    if(object instanceof Modeleable){
		object = (T) ((Modeleable<T>) object).demodelize();
	    }
	    service.delete(object);
	    forceDownload = true;
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    publishError(msgBundle.getString("delete.error"));
	    publishError(e.getMessage());
	}
	return "list";
    }

    public String addNew() throws InstantiationException, IllegalAccessException {
	log.debug("Adding new item, loading window...");
	model = modelType.newInstance();
	return "success";
    }

    public String edit(Modeleable<T> model) {
	this.setModel(model);
	return "success";
    }

    public String showList() {
	return "success";
    }

    public BaseService<T> getService() {
	return service;
    }

    public void setService(BaseService<T> service) {
	this.service = service;
    }

    public boolean isForceDownload() {
	return forceDownload;
    }

    public void setForceDownload(boolean forceDownload) {
	this.forceDownload = forceDownload;
    }

    public ResourceBundle getMsgBundle() {
	return msgBundle;
    }

    public void setMsgBundle(ResourceBundle msgBundle) {
	this.msgBundle = msgBundle;
    }

    public Modeleable<T> getModel() {
	return model;
    }

    public void setModel(Modeleable<T> model) {
	this.model = model;
    }

    public List<Modeleable<T>> getSelectedList() {
	return selectedList;
    }

    public void setSelectedList(List<Modeleable<T>> selectedList) {
	this.selectedList = selectedList;
    }
}
