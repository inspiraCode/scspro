package com.nowgroup.scspro.jsf.beans.cat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.nowgroup.scspro.dto.cat.Storage;
import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.service.cat.StorageService;
import com.nowgroup.scspro.service.geo.CountryService;
import com.nowgroup.scspro.service.geo.StateService;

@ManagedBean
@SessionScoped
public class StorageBean implements Serializable {
    private static final long serialVersionUID = 8410819475089818242L;
    private static final Logger log = Logger.getLogger(StorageBean.class.getName());

    private Storage storage = new Storage();
    private int countryId = 0;
    private int stateId = 0;

    private List<Country> countries = null;
    private List<State> states = null;

    @ManagedProperty("#{storageService}")
    private StorageService storageServiceImpl;

    @ManagedProperty("#{countryService}")
    private CountryService countryManager;

    @ManagedProperty("#{geoStateService}")
    private StateService geoStateManager;

    @ManagedProperty("#{i18n_storages}")
    private ResourceBundle storageMsgBundle;

    @PostConstruct
    public void init() {
	countries = getCountryManager().getAll();
    }

    public List<Storage> getStorages() {
	return storageServiceImpl.getAll();
    }

    public Storage getStorage() {
	return storage;
    }

    public void setStorage(Storage storage) {
	this.storage = storage;
	if (null != storage.getState()) {
	    State storageState = getStorageServiceImpl().getStateInStorage(storage.getId());
	    log.debug("to state from storage manager: " + storageState);
	    this.setStateId(storageState.getId());
	    int countryId = getGeoStateManager().getCountryIdInState(storageState.getId());
	    Country storageCountry = getCountryManager().get(countryId);
	    this.setCountryId(storageCountry.getId());
	    if (getStates() == null || !getStates().contains(storageState)) {
		setStates(new ArrayList<State>());
		getStates().add(storageState);
	    }
	} else {
	    this.setStateId(0);
	    this.setCountryId(0);
	}
    }

    public String addNew() {
	this.storage = new Storage();
	this.setStateId(0);
	this.setCountryId(0);

	states = null;

	return "storage";
    }

    public String showList() {
	return "storages-list";
    }

    public String remove(Storage storage) {
	try {
	    storageServiceImpl.delete(storage);
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    FacesContext.getCurrentInstance().addMessage(null,
		    new FacesMessage(FacesMessage.SEVERITY_ERROR, storageMsgBundle.getString("storage.remove.error"), e.getMessage()));
	}
	return "storages-list";
    }

    public void onCountryChange() {
	if (countryId != 0)
	    states = getCountryManager().getStatesByCountry(countryId);
	else
	    states = new ArrayList<State>();
    }

    public String uploadStorage() {
	log.debug("Uploading storage to database");

	if (this.stateId > 0) {
	    getStorage().setState(new State());
	    getStorage().getState().setId(stateId);
	    getStorage().getState().setCountry(new Country());
	    getStorage().getState().getCountry().setId(countryId);
	}

	try {
	    if (getStorage().getId() != 0) {
		log.debug("Updating existing record");
		getStorageServiceImpl().update(storage);
	    } else {
		log.debug("Adding new record");
		getStorageServiceImpl().add(storage);
	    }
	    log.debug("Storage successfully uploaded");
	    return "success";
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    FacesContext.getCurrentInstance().addMessage(null,
		    new FacesMessage(FacesMessage.SEVERITY_ERROR, storageMsgBundle.getString("storage.save.error"), e.getMessage()));
	    return "";
	}

    }

    public String edit(Storage item) {
	item.setState(getStorageServiceImpl().getStateInStorage(item.getId()));

	if (item.getState() != null) {
	    log.debug("assigned state in storage item: " + item.getState());
	    int stateId = item.getState().getId();
	    int countryId = getGeoStateManager().getCountryIdInState(stateId);
	    Country dbCountry = getCountryManager().get(countryId);
	    log.debug("assigned country in storage item: " + dbCountry.getId());

	    this.setStateId(item.getState().getId());
	    this.setCountryId(dbCountry.getId());
	}

	this.setStorage(item);
	return "storage";
    }

    public int getCountryId() {
	return countryId;
    }

    public void setCountryId(int countryId) {
	this.countryId = countryId;
    }

    public int getStateId() {
	return stateId;
    }

    public void setStateId(int stateId) {
	this.stateId = stateId;
    }

    public List<Country> getCountries() {
	if (countries == null) {
	    getCountriesFromService();
	}
	return countries;
    }

    private void getCountriesFromService() {
	countries = getCountryManager().getAll();
    }

    public void setCountries(List<Country> countries) {
	this.countries = countries;
    }

    public List<State> getStates() {
	return states;
    }

    public void setStates(List<State> states) {
	this.states = states;
    }

    public StorageService getStorageServiceImpl() {
	return storageServiceImpl;
    }

    public void setStorageServiceImpl(StorageService storageServiceImpl) {
	this.storageServiceImpl = storageServiceImpl;
    }

    public CountryService getCountryManager() {
	return countryManager;
    }

    public void setCountryManager(CountryService countryManager) {
	this.countryManager = countryManager;
    }

    public StateService getGeoStateManager() {
	return geoStateManager;
    }

    public void setGeoStateManager(StateService geoStateManager) {
	this.geoStateManager = geoStateManager;
    }

    public ResourceBundle getStorageMsgBundle() {
	return storageMsgBundle;
    }

    public void setStorageMsgBundle(ResourceBundle storageMsgBundle) {
	this.storageMsgBundle = storageMsgBundle;
    }

}
