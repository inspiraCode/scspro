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

    public CompanyBean() {
	super(new CompanyModel());
    }

    @PostConstruct
    public void init() {
	countries = getCountryServiceImpl().getAll();
    }

    public String getCompanyState(int id) {
	String result = "";

	log.debug("Resolving state for table with Company.Id: " + id);
	int geoStateIdInDb = getCompanyServiceImpl().getStateIdInCompanyId(id);
	log.debug("Resolving state for table with State.Id: " + geoStateIdInDb);
	State geoStateInDb = null;
	if (geoStateIdInDb != 0) {
	    geoStateInDb = getGeoStateService().get(geoStateIdInDb);
	    log.debug("Found state in database: " + geoStateInDb);
	}

	result = geoStateInDb == null || geoStateInDb.getName() == null ? "" : geoStateInDb.getName();
	return result;
    }

    public String getDisplayRoles(int id) {
	String result = "";

	List<CompanyScope> scopes = getCompanyServiceImpl().getCompanyScope(id);

	for (CompanyScope scope : scopes) {
	    String translate = getCompanyScopeServiceImpl().getRoleInCompanyScope(scope.getId()).getName();
	    String translated = companyMsgBundle.getString("companies.profiles." + translate.toLowerCase());
	    result += translated + ", ";
	}

	if (result.length() > 2)
	    result = result.substring(0, result.length() - 2);

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
	    if (companyStateId == 0)
		return;

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
	rolesList = new ArrayList<CompanyScopeModel>();
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
	    log.debug("found state in company to upload: " + stateId);
	    entity.setState(new State());
	    entity.getState().setId(stateId);
	    entity.getState().setCountry(new Country());
	    entity.getState().getCountry().setId(countryId);

	    log.debug("state added to entity model: " + entity.getState().getId());
	    log.debug("country added to entity model: " + entity.getState().getCountry().getId());
	}

	if (!rolesList.isEmpty()) {
	    entity.getCompanyScope().clear();
	    entity.getCompanyScope().addAll(rolesList);
	}

	CompanyModel temp = new CompanyModel();
	Modeleable<Company> tempModel = temp.getModel(entity);
	log.debug("Storing model: " + tempModel);
	super.setModel(tempModel);
	return super.upload();
    }

    public List<CompanyScopeModel> getRolesList() {
	Company company = getModel().demodelize();
	if (company.getId() != 0) {
	    rolesList = new ArrayList<CompanyScopeModel>();
	    List<CompanyScope> scopes = getCompanyServiceImpl().getCompanyScope(company.getId());
	    log.debug("roles from database: " + scopes.size());

	    for (CompanyScope scope : scopes) {
		CompanyScopeModel scopeModel = new CompanyScopeModel(scope);
		rolesList.add(scopeModel);
	    }

	    return rolesList;
	} else {
	    log.debug("Locally resolved roles: " + rolesList.size());
	    return rolesList;
	}
    }

    public List<CompanyRoleModel> getAvailableRolesList() {
	// Retrieve all roles from database
	List<CompanyRole> allRoles = companyRoleServiceImpl.getAll();

	for (CompanyScopeModel usedScope : getRolesList()) {
	    log.debug("Retrieve role details from database with Id:" + usedScope.getId());
	    CompanyRole usedRole = null;
	    if (usedScope.getId() > 0) {
		// retrieve from database
		usedRole = getCompanyScopeServiceImpl().getRoleInCompanyScope(usedScope.getId());
	    } else {
		// use locally
		usedRole = usedScope.getCompanyRole();
	    }
	    log.debug("removing role from available: " + usedRole);

	    if (!allRoles.remove(usedRole))
		log.warn("Role not found in all roles to be removed: " + usedRole);
	}

	// Translate available roles.
	availableRoles = new ArrayList<CompanyRoleModel>();
	for (CompanyRole role : allRoles) {
	    CompanyRoleModel crm = new CompanyRoleModel();
	    crm.setId(role.getId());
	    crm.setName(role.getName());
	    availableRoles.add(crm);
	}

	return availableRoles;
    }

    public String getDisplayName(CompanyRoleModel role) {
	return companyMsgBundle.getString("companies.profiles." + role.getName().toLowerCase());
    }

    public String getDisplayName(CompanyScopeModel scope) {
	log.debug("translating scope: " + scope);
	if (scope == null) {
	    log.warn("Scope is null");
	    return "";
	}
	int id = scope.getId();

	String translate = "";
	if (id < 0) {
	    translate = scope.getCompanyRole().getName();
	} else {
	    CompanyRole role = getCompanyScopeServiceImpl().getRoleInCompanyScope(id);
	    if (role == null) {
		log.warn("Scope not found from company scope service with id:" + id);
		return "";
	    }
	    translate = role.getName();
	}

	return companyMsgBundle.getString("companies.profiles." + translate.toLowerCase());
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
			log.debug("Adding role to company: " + item);
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
	log.debug("Demodelizing role for scope: " + item);
	addedScope.setCompanyRole(item.deModelize());
	addedScope.setId(--newIndex);

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
