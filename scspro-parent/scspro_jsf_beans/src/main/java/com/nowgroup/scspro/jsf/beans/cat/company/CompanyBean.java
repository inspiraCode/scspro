package com.nowgroup.scspro.jsf.beans.cat.company;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;
import org.primefaces.event.DragDropEvent;

import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.CompanyRole;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.jsf.beans.BaseFacesBean;
import com.nowgroup.scspro.model.Modeleable;
import com.nowgroup.scspro.model.cat.CompanyModel;
import com.nowgroup.scspro.model.cat.CompanyRoleModel;
import com.nowgroup.scspro.model.cat.CompanyScopeModel;
import com.nowgroup.scspro.service.BaseService;
import com.nowgroup.scspro.service.cat.CompanyRoleService;
import com.nowgroup.scspro.service.cat.CompanyScopeService;
import com.nowgroup.scspro.service.cat.CompanyService;
import com.nowgroup.scspro.service.geo.CountryService;
import com.nowgroup.scspro.service.geo.StateService;

@ManagedBean
@SessionScoped
public class CompanyBean extends BaseFacesBean<Company> {
    
    public CompanyBean() {
	super(new CompanyModel());
    }

    private static final long serialVersionUID = 923379298131353402L;

    private static Logger log = Logger.getLogger(CompanyBean.class.getName());

    private int countryId = 0;
    private int stateId = 0;
    private int newIndex = 0;

    private List<Country> countries = null;
    private List<State> states = null;

    private List<CompanyScopeModel> rolesList = new ArrayList<CompanyScopeModel>();
    private List<CompanyRoleModel> availableRoles = new ArrayList<CompanyRoleModel>();

    // Injected in applicationContext.xml
    @ManagedProperty("#{companyService}")
    private CompanyService companyServiceImpl;

    @ManagedProperty("#{countryService}")
    private CountryService countryServiceImpl;

    @ManagedProperty("#{geoStateService}")
    private StateService geoStateService;

    @ManagedProperty("#{companyRoleService}")
    private CompanyRoleService companyRoleServiceImpl;

    @ManagedProperty("#{companyScopeService}")
    private CompanyScopeService companyScopeServiceImpl;

    @ManagedProperty("#{i18n_companies}")
    private ResourceBundle companyMsgBundle;

    @PostConstruct
    public void init() {
	countries = getCountryServiceImpl().getAll();
    }
    
    public String getCompanyState(int id) {
	String result = "";

	int geoStateIdInDb = getCompanyServiceImpl().getStateIdInCompanyId(id);
	State geoStateInDb = null;
	if (geoStateIdInDb != 0)
	    geoStateInDb = getGeoStateService().get(geoStateIdInDb);

	result = geoStateInDb == null || geoStateInDb.getName() == null ? "" : geoStateInDb.getName();
	return result;
    }
    
    @Override
    public void setModel(Modeleable<Company> model) {
	super.setModel(model);
	Company company = model.demodelize();
	if (null != company.getState()) {
	    // Query state and country independently because of the lazy object
	    // and the transaction requirements
	    int companyStateId = getCompanyServiceImpl().getStateIdInCompanyId(company.getId());
	    log.debug("got state ID from state manager: " + companyStateId);
	    this.setStateId(companyStateId);

	    int countryId = getGeoStateService().getCountryIdInState(companyStateId);
	    Country companyCountry = getCountryServiceImpl().get(countryId);

	    this.setCountryId(companyCountry.getId());
	    // check if state is available in country list

	    State stateInDb = getGeoStateService().get(companyStateId);
	    if (getStates() == null || !getStates().contains(stateInDb)) {
		setStates(new ArrayList<State>());
		getStates().add(stateInDb);
	    }
	} else {
	    this.setStateId(0);
	    this.setCountryId(0);
	}
    }

    @Override
    public String addNew() throws InstantiationException, IllegalAccessException {
	this.setStateId(0);
	this.setCountryId(0);
	
	states = null;
	return super.addNew();
    }

    public void countryAjaxListener(AjaxBehaviorEvent event) {
	if (countryId != 0)
	    states = getCountryServiceImpl().getStatesByCountry(countryId);
	else
	    states = new ArrayList<State>();
    }

    @Override
    public String upload() {
	log.debug("Uploading company to database: " + getModel());
	Company entity = getModel().demodelize();
	
	if (this.stateId > 0) {
	    entity.setState(new State());
	    entity.getState().setId(stateId);
	    entity.getState().setCountry(new Country());
	    entity.getState().getCountry().setId(countryId);
	}
	return super.upload();
    }

    public List<CompanyScopeModel> getRolesList() {
	if (companyMsgBundle == null) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    setCompanyMsgBundle(context.getApplication().getResourceBundle(context, "i18n_companies"));
	    log.debug("Got forced resource bundle: " + companyMsgBundle);
	}
	Company company = getModel().demodelize();
	if (company.getId() != 0) {
	    rolesList = new ArrayList<CompanyScopeModel>();
	    List<CompanyScope> scopes = getCompanyServiceImpl().getCompanyScope(company.getId());

	    for (CompanyScope scope : scopes) {
		String translate = getCompanyScopeServiceImpl().getRoleInCompanyScope(scope.getId()).getName();
		log.debug("translating role in scope: " + translate);
		CompanyScopeModel scopeModel = new CompanyScopeModel(scope);

		log.debug("translating from " + translate);
		String translated = companyMsgBundle.getString("companies.profiles." + translate.toLowerCase());
		scopeModel.setDisplayName(translated);

		rolesList.add(scopeModel);
	    }

	    return rolesList;
	} else {
	    return rolesList;
	}
    }

    public List<CompanyRoleModel> getAvailableRolesList() {
	if (companyMsgBundle == null) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    setCompanyMsgBundle(context.getApplication().getResourceBundle(context, "i18n_companies"));
	    log.debug("Got forced resource bundle: " + companyMsgBundle);
	}
	// Retrieve all roles from database
	List<CompanyRole> allRoles = companyRoleServiceImpl.getAll();

	if (getModel().demodelize().getId() == 0) {
	    // Handle locally
	    for (CompanyScopeModel usedScope : getRolesList()) {
		allRoles.remove(usedScope.getCompanyRole());
	    }
	} else {
	    for (CompanyScopeModel usedScope : getRolesList()) {
		CompanyRole usedRole = getCompanyScopeServiceImpl().getRoleInCompanyScope(usedScope.getId());
		allRoles.remove(usedRole);
	    }
	}

	// Translate available roles.
	availableRoles = new ArrayList<CompanyRoleModel>();
	for (CompanyRole role : allRoles) {
	    CompanyRoleModel crm = new CompanyRoleModel();
	    crm.setId(role.getId());
	    crm.setName(role.getName());
	    crm.setDisplayName(companyMsgBundle.getString("companies.profiles." + role.getName().toLowerCase()));
	    log.debug("translating role: " + crm);

	    availableRoles.add(crm);
	}

	return availableRoles;
    }

    public void onRemoveRole(DragDropEvent event) {
	HtmlPanelGroup availableRoles = (HtmlPanelGroup) event.getComponent().findComponent("selectedRoles");
	if (availableRoles != null) {
	    availableRoles.invokeOnComponent(FacesContext.getCurrentInstance(), event.getDragId(), new ContextCallback() {

		public void invokeContextCallback(FacesContext context, UIComponent target) {
		    HtmlPanelGroup draggedItem = (HtmlPanelGroup) target;
		    CompanyScopeModel item = draggedItem != null ? (CompanyScopeModel) draggedItem.getAttributes().get("companyRole") : new CompanyScopeModel();

		    try {
			removeRole(item);
			publishInfo(companyMsgBundle.getString("companies.profiles.removed"));
		    } catch (Exception e) {
			log.error(e.getMessage(), e);
			publishError(companyMsgBundle.getString("companies.profiles.error"));
			publishError(e.getLocalizedMessage());
		    }
		}

	    });
	} else {
	    publishError(companyMsgBundle.getString("companies.profiles.error"));
	}
    }

    private void removeRole(CompanyScopeModel item) {
	if (getModel().demodelize().getId() != 0)
	    removeRoleCompany(item);
	else {
	    removeLocalCompanyRole(item);
	    rolesList.remove(item);
	}
    }

    private void removeLocalCompanyRole(CompanyScopeModel item) {
	getModel().demodelize().getCompanyScope().remove(item.demodelize());
    }

    private void removeRoleCompany(CompanyScopeModel item) {
	companyScopeServiceImpl.delete(item);
	rolesList.remove(item);
    }

    public void onAddRole(DragDropEvent event) {

	HtmlPanelGroup availableRoles = (HtmlPanelGroup) event.getComponent().findComponent("availableRoles");
	if (availableRoles != null) {
	    availableRoles.invokeOnComponent(FacesContext.getCurrentInstance(), event.getDragId(), new ContextCallback() {

		public void invokeContextCallback(FacesContext context, UIComponent target) {
		    HtmlPanelGroup draggedItem = (HtmlPanelGroup) target;
		    CompanyRoleModel item = draggedItem != null ? (CompanyRoleModel) draggedItem.getAttributes().get("companyRole") : new CompanyRoleModel();

		    try {
			addRole(item);
			publishInfo(companyMsgBundle.getString("companies.profiles.added"));
		    } catch (Exception e) {
			log.error(e.getMessage(), e);
			publishError(companyMsgBundle.getString("companies.profiles.error"));
			publishError(e.getLocalizedMessage());
		    }
		}

	    });
	} else {
	    publishError(companyMsgBundle.getString("companies.profiles.error"));
	}
    }

    private void addRole(CompanyRoleModel item) {
	if (getModel().demodelize().getId() != 0)
	    addRoleCompany(item);
	else {
	    addLocalCompanyRole(item);
	}
    }

    private void addLocalCompanyRole(CompanyRoleModel item) {
	CompanyScopeModel addedScope = new CompanyScopeModel();
	addedScope.setCompany(getModel().demodelize());
	addedScope.setCompanyRole(item.deModelize());
	addedScope.setId(--newIndex);
	addedScope.setDisplayName(item.getDisplayName());

	getModel().demodelize().getCompanyScope().add(addedScope);
	rolesList.add(addedScope);
    }

    private void addRoleCompany(CompanyRoleModel item) {
	// company exists, create a CompanyScope in database with role
	CompanyScope newScope = new CompanyScope();
	newScope.setCompany(getModel().demodelize());
	newScope.setCompanyRole(item.deModelize());
	int newScopeId = companyScopeServiceImpl.add(newScope);
	newScope.setId(newScopeId);
	CompanyScopeModel newScopeModel = new CompanyScopeModel(newScope);
	newScopeModel.setDisplayName(item.getName());
	rolesList.add(newScopeModel);
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

    private List<Country> getCountriesFromService() {
	countries = getCountryServiceImpl().getAll();
	return countries;
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

    public CompanyService getCompanyServiceImpl() {
	return companyServiceImpl;
    }

    public void setCompanyServiceImpl(CompanyService companyServiceImpl) {
	super.setService((BaseService<Company>) companyServiceImpl);
	this.companyServiceImpl = companyServiceImpl;
    }

    public CountryService getCountryServiceImpl() {
	return countryServiceImpl;
    }

    public void setCountryServiceImpl(CountryService countryServiceImpl) {
	this.countryServiceImpl = countryServiceImpl;
    }

    public StateService getGeoStateService() {
	return geoStateService;
    }

    public void setGeoStateService(StateService geoGeoStateService) {
	this.geoStateService = geoGeoStateService;
    }

    public CompanyRoleService getCompanyRoleServiceImpl() {
	return companyRoleServiceImpl;
    }

    public void setCompanyRoleServiceImpl(CompanyRoleService companyRoleServiceImpl) {
	this.companyRoleServiceImpl = companyRoleServiceImpl;
    }

    public ResourceBundle getCompanyMsgBundle() {
	return companyMsgBundle;
    }

    public void setCompanyMsgBundle(ResourceBundle companyMsgBundle) {
	super.setMsgBundle(companyMsgBundle);
	this.companyMsgBundle = companyMsgBundle;
    }

    public CompanyScopeService getCompanyScopeServiceImpl() {
	return companyScopeServiceImpl;
    }

    public void setCompanyScopeServiceImpl(CompanyScopeService companyScopeServiceImpl) {
	this.companyScopeServiceImpl = companyScopeServiceImpl;
    }
}
