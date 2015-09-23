package com.nowgroup.scspro.faces.beans.cat.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.DragDropEvent;

import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.CompanyRole;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.faces.beans.BaseFacesBean;
import com.nowgroup.scspro.model.CompanyModel;
import com.nowgroup.scspro.model.CompanyRoleModel;
import com.nowgroup.scspro.model.CompanyScopeModel;
import com.nowgroup.scspro.service.cat.CompanyRoleService;
import com.nowgroup.scspro.service.cat.CompanyScopeService;
import com.nowgroup.scspro.service.cat.CompanyService;
import com.nowgroup.scspro.service.geo.CountryService;
import com.nowgroup.scspro.service.geo.StateService;

@ManagedBean
@SessionScoped
public class CompanyBean extends BaseFacesBean {
    private static final long serialVersionUID = 923379298131353402L;

    private static Logger log = Logger.getLogger(CompanyBean.class.getName());

    private CompanyModel company = new CompanyModel();
    private int countryId = 0;
    private int stateId = 0;
    private int newIndex = 0;

    private Map<String, Integer> countries = null;
    private Map<String, Integer> states = null;

    private List<CompanyScopeModel> rolesList = new ArrayList<CompanyScopeModel>();
    private List<CompanyRoleModel> availableRoles = new ArrayList<CompanyRoleModel>();

    // Injected in applicationContext.xml
    @ManagedProperty("#{companyServiceImpl}")
    private CompanyService companyServiceImpl;

    @ManagedProperty("#{countryServiceImpl}")
    private CountryService countryServiceImpl;

    @ManagedProperty("#{geoStateService}")
    private StateService geoStateService;

    @ManagedProperty("#{companyRoleServiceImpl}")
    private CompanyRoleService companyRoleServiceImpl;

    @ManagedProperty("#{companyScopeServiceImpl}")
    private CompanyScopeService companyScopeServiceImpl;

    @ManagedProperty("#{i18n_companies}")
    private ResourceBundle companyMsgBundle;

    @ManagedProperty("#{companyListBean}")
    private CompanyListBean listBean;

    @PostConstruct
    public void init() {
	List<Country> countries = getCountryServiceImpl().getCountries();
	this.setCountries(new HashMap<String, Integer>());
	for (Country c : countries) {
	    this.getCountries().put(c.getName(), c.getId());
	}
    }

    public Company getCompany() {
	return company;
    }

    public void setCompany(CompanyModel company) {
	this.company = company;
	if (null != company.getState()) {
	    // Query state and country independently because of the lazy object
	    // and the transaction requirements
	    State companyState = getCompanyServiceImpl().getStateInCompanyId(company.getId());
	    log.debug("got state from state manager: " + companyState);
	    this.setStateId(companyState.getId());
	    Country companyCountry = getGeoStateService().getCountryInState(companyState.getId());
	    this.setCountryId(companyCountry.getId());
	    // check if state is available in country list
	    if (getStates() == null || !getStates().containsKey(companyState.getId())) {
		setStates(new HashMap<String, Integer>());
		getStates().put(companyState.getName(), companyState.getId());
	    }
	} else {
	    this.setStateId(0);
	    this.setCountryId(0);
	}
    }

    public String addNew() {
	this.company = new CompanyModel();
	rolesList = new ArrayList<CompanyScopeModel>();
	this.setStateId(0);
	this.setCountryId(0);

	states = null;
	return "company";
    }

    public String showList() {
	return "list";
    }

    public String remove(CompanyModel item) {
	try {
	    companyServiceImpl.deleteCompany(item.deModelize());
	    listBean.setForceDownload(true);
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    publishError(companyMsgBundle.getString("company.remove.error"));
	    publishError(e.getLocalizedMessage());
	}

	return "list";
    }

    public void onCountryChange() {
	if (countryId != 0)
	    states = getCountryServiceImpl().getStatesByCountry(countryId);
	else
	    states = new HashMap<String, Integer>();
    }

    public String uploadCompany() {
	log.debug("Uploading company to database: " + company);

	if (this.stateId > 0) {
	    getCompany().setState(new State());
	    getCompany().getState().setId(stateId);
	    getCompany().getState().setCountry(new Country());
	    getCompany().getState().getCountry().setId(countryId);
	}

	try {
	    if (getCompany().getId() != 0) {
		log.debug("Updating existing record");
		getCompanyServiceImpl().updateCompany(company.deModelize());
	    } else {
		log.debug("Adding new record");
		log.debug("roles in record: " + company.getCompanyScope().size());
		getCompanyServiceImpl().addCompany(company.deModelize());
	    }
	    log.debug("Company Successfully uploaded");
	    listBean.setForceDownload(true);
	    return "success";
	} catch (org.springframework.dao.DataIntegrityViolationException e) {
	    log.error(e.getMessage(), e);
	    publishError(companyMsgBundle.getString("company.save.duplicate"));
	    return "";
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    publishError(companyMsgBundle.getString("company.save.error"));
	    publishError(e.getLocalizedMessage());
	    return "";
	}
    }

    public String edit(CompanyModel item) {
	this.setCompany(item);
	return "company";
    }

    public List<CompanyScopeModel> getRolesList() {
	if (companyMsgBundle == null) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    setCompanyMsgBundle(context.getApplication().getResourceBundle(context, "i18n_companies"));
	    log.debug("Got forced resource bundle: " + companyMsgBundle);
	}
	if (this.getCompany().getId() != 0) {
	    rolesList = new ArrayList<CompanyScopeModel>();
	    List<CompanyScope> scopes = getCompanyServiceImpl().getCompanyScope(this.getCompany().getId());

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
	List<CompanyRole> allRoles = companyRoleServiceImpl.getCompanyRoles();

	if (this.getCompany().getId() == 0) {
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
	if (this.getCompany().getId() != 0)
	    removeRoleCompany(item);
	else {
	    removeLocalCompanyRole(item);
	    rolesList.remove(item);
	}
    }

    private void removeLocalCompanyRole(CompanyScopeModel item) {
	company.getCompanyScope().remove(item.deModelize());
    }

    private void removeRoleCompany(CompanyScopeModel item) {
	companyScopeServiceImpl.removeScope(item);
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
	if (this.getCompany().getId() != 0)
	    addRoleCompany(item);
	else {
	    addLocalCompanyRole(item);
	}
    }

    private void addLocalCompanyRole(CompanyRoleModel item) {
	CompanyScopeModel addedScope = new CompanyScopeModel();
	addedScope.setCompany(company);
	addedScope.setCompanyRole(item.deModelize());
	addedScope.setId(--newIndex);
	addedScope.setDisplayName(item.getDisplayName());

	company.getCompanyScope().add(addedScope);
	rolesList.add(addedScope);
    }

    private void addRoleCompany(CompanyRoleModel item) {
	// company exists, create a CompanyScope in database with role
	CompanyScope newScope = new CompanyScope();
	newScope.setCompany(company);
	newScope.setCompanyRole(item.deModelize());
	int newScopeId = companyScopeServiceImpl.addScope(newScope);
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

    public Map<String, Integer> getCountries() {
	if (countries == null) {
	    getCountriesFromService();
	}
	return countries;
    }

    private void getCountriesFromService() {
	List<Country> countries = getCountryServiceImpl().getCountries();
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

    public CompanyService getCompanyServiceImpl() {
	return companyServiceImpl;
    }

    public void setCompanyServiceImpl(CompanyService companyServiceImpl) {
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
	log.debug("Setting company message bundle: " + companyMsgBundle.getBaseBundleName());
	this.companyMsgBundle = companyMsgBundle;
    }

    public CompanyScopeService getCompanyScopeServiceImpl() {
	return companyScopeServiceImpl;
    }

    public void setCompanyScopeServiceImpl(CompanyScopeService companyScopeServiceImpl) {
	this.companyScopeServiceImpl = companyScopeServiceImpl;
    }

    public CompanyListBean getListBean() {
	return listBean;
    }

    public void setListBean(CompanyListBean listBean) {
	this.listBean = listBean;
    }
}
