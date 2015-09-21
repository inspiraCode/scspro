package com.nowgroup.scspro.faces.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.dto.sys.Storage;
import com.nowgroup.scspro.service.geo.CountryService;
import com.nowgroup.scspro.service.geo.StateService;
import com.nowgroup.scspro.service.sys.StorageService;

@ManagedBean
@SessionScoped
public class StorageBean implements Serializable {
	private static final long serialVersionUID = 8410819475089818242L;
	private static final Logger log = Logger.getLogger(StorageBean.class
			.getName());

	private Storage storage = new Storage();
	private int countryId = 0;
	private int stateId = 0;

	private Map<String, Integer> countries = null;
	private Map<String, Integer> states = null;

	@ManagedProperty("#{storageServiceImpl}")
	private StorageService storageServiceImpl;

	@ManagedProperty("#{countryServiceImpl}")
	private CountryService countryManager;

	@ManagedProperty("#{geoStateService}")
	private StateService geoStateManager;

	@ManagedProperty("#{i18n_storages}")
	private ResourceBundle storageMsgBundle;

	@PostConstruct
	public void init() {
		List<Country> countries = getCountryManager().getCountries();
		this.setCountries(new HashMap<String, Integer>());
		for (Country c : countries) {
			this.getCountries().put(c.getName(), c.getId());
		}
	}

	public List<Storage> getStorages() {
		return storageServiceImpl.getStorages();
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
		if (null != storage.getState()) {
			State storageState = getStorageServiceImpl().getStateInStorage(
					storage.getId());
			log.debug("to state from storage manager: " + storageState);
			this.setStateId(storageState.getId());
			Country storageCountry = getGeoStateManager().getCountryInState(
					storageState.getId());
			this.setCountryId(storageCountry.getId());
			if (getStates() == null
					|| !getStates().containsKey(storageState.getId())) {
				setStates(new HashMap<String, Integer>());
				getStates().put(storageState.getName(), storageState.getId());
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
			storageServiceImpl.deleteStorage(storage);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							storageMsgBundle.getString("storage.remove.error"),
							e.getMessage()));
		}
		return "storages-list";
	}

	public void onCountryChange() {
		if (countryId != 0)
			states = getCountryManager().getStatesByCountry(countryId);
		else
			states = new HashMap<String, Integer>();
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
				getStorageServiceImpl().updateStorage(storage);
			} else {
				log.debug("Adding new record");
				getStorageServiceImpl().addStorage(storage);
			}
			log.debug("Storage successfully uploaded");
			return "success";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							storageMsgBundle.getString("storage.save.error"), e
									.getMessage()));
			return "";
		}

	}

	public String edit(Storage item) {
		item.setState(getStorageServiceImpl().getStateInStorage(item.getId()));

		if (item.getState() != null) {
			log.debug("assigned state in storage item: " + item.getState());
			int stateId = item.getState().getId();
			Country dbCountry = getGeoStateManager().getCountryInState(stateId);
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

	public Map<String, Integer> getCountries() {
		if (countries == null) {
			getCountriesFromService();
		}
		return countries;
	}

	private void getCountriesFromService() {
		List<Country> countries = getCountryManager().getCountries();
		this.setCountries(new HashMap<String, Integer>());
		for (Country c : countries) {
			this.getCountries().put(c.getName(), c.getId());
		}
	}

	public void setCountries(Map<String, Integer> countries) {
		this.countries = countries;
	}

	public Map<String, Integer> getStates() {
		return states;
	}

	public void setStates(Map<String, Integer> states) {
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
