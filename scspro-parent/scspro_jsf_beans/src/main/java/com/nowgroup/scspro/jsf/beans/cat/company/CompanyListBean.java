package com.nowgroup.scspro.jsf.beans.cat.company;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.dto.geo.Country;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.jsf.beans.BaseFacesReporteableBean;
import com.nowgroup.scspro.model.cat.CompanyModel;
import com.nowgroup.scspro.service.cat.CompanyScopeService;
import com.nowgroup.scspro.service.cat.CompanyService;
import com.nowgroup.scspro.service.geo.CountryService;
import com.nowgroup.scspro.service.geo.StateService;

@ManagedBean
@SessionScoped
public class CompanyListBean extends BaseFacesReporteableBean {
    private static final long serialVersionUID = -387847711313688442L;

    private static Logger log = Logger.getLogger(CompanyListBean.class.getName());

    @ManagedProperty("#{companyService}")
    private CompanyService companyServiceImpl;

    @ManagedProperty("#{companyScopeService}")
    private CompanyScopeService companyScopeServiceImpl;

    @ManagedProperty("#{geoStateService}")
    private StateService geoStateServiceImpl;

    @ManagedProperty("#{countryService}")
    private CountryService geoCountryService;

    @ManagedProperty("#{i18n_companies}")
    private ResourceBundle companyMsgBundle;

    private boolean forceDownload = false;
    private List<CompanyModel> companiesList = new LinkedList<CompanyModel>();

    private List<CompanyModel> selectedItems = new LinkedList<CompanyModel>();

    public List<CompanyModel> getCompanies() {

	if (!companiesList.isEmpty() && !forceDownload) {
	    return companiesList;
	}

	if (companyMsgBundle == null) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    setCompanyMsgBundle(context.getApplication().getResourceBundle(context, "i18n_companies"));
	    log.debug("Got forced resource bundle: " + companyMsgBundle);
	}
	List<Company> dbCompanies = getCompanyServiceImpl().getAll();
	companiesList = new LinkedList<CompanyModel>();
	log.debug("describing " + dbCompanies.size() + " companies.");
	for (Company dbCompany : dbCompanies) {
	    log.debug("Setting up roles for " + dbCompany.getName());
	    CompanyModel model = new CompanyModel(dbCompany);
	    model.setDisplayRoles("");

	    List<CompanyScope> scopes = getCompanyServiceImpl().getCompanyScope(model.getId());
	    log.debug("scopes in company:" + scopes.size());
	    for (CompanyScope scope : scopes) {
		String roleName = getCompanyScopeServiceImpl().getRoleInCompanyScope(scope.getId()).getName();
		// String roleName = scope.getCompanyRole().getName();
		log.debug("translating " + roleName);
		String translated = companyMsgBundle.getString("companies.profiles." + roleName);
		log.debug("translated as " + translated);
		model.setDisplayRoles(model.getDisplayRoles() + ", " + translated);
	    }

	    if (model.getDisplayRoles().length() > 2) {
		model.setDisplayRoles(model.getDisplayRoles().substring(2));
	    }

	    int geoStateIdInDb = getCompanyServiceImpl().getStateIdInCompanyId(model.getId());
	    if (0 != geoStateIdInDb) {
		int countryIdInDb = getGeoStateServiceImpl().getCountryIdInState(geoStateIdInDb);
		if (0 != countryIdInDb) {
		    Country countryInDb = getGeoCountryService().get(countryIdInDb);
		    log.debug("received ID from database, getting the country: " + geoStateIdInDb);
		    model.setDisplayCountry(countryInDb.getName());
		}
	    }

	    companiesList.add(model);
	}
	forceDownload = false;
	return companiesList;
    }

    public String getCompanyState(int id) {
	String result = "";

	int geoStateIdInDb = getCompanyServiceImpl().getStateIdInCompanyId(id);
	State geoStateInDb = null;
	if (geoStateIdInDb != 0)
	    geoStateInDb = getGeoStateServiceImpl().get(geoStateIdInDb);

	result = geoStateInDb == null || geoStateInDb.getName() == null ? "" : geoStateInDb.getName();
	return result;
    }

    public String selectedItem(CompanyModel item) {
	if (item.isSelected())
	    selectedItems.add(item);
	else
	    selectedItems.remove(item);

	return "";
    }

    public String removeSelected() {
	// Remove selected items and reload list.
	List<Company> removeList = new LinkedList<Company>();
	for (CompanyModel model : selectedItems) {
	    removeList.add(model.deModelize());
	}
	companyServiceImpl.deleteCompanies(removeList);
	for (CompanyModel model : selectedItems) {
	    companiesList.remove(model);
	}
	selectedItems = new LinkedList<CompanyModel>();
	return "";
    }

    public CompanyService getCompanyServiceImpl() {
	return companyServiceImpl;
    }

    public void setCompanyServiceImpl(CompanyService companyServiceImpl) {
	this.companyServiceImpl = companyServiceImpl;
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

    public boolean isForceDownload() {
	return forceDownload;
    }

    public void setForceDownload(boolean forceDownload) {
	this.forceDownload = forceDownload;
    }

    public StateService getGeoStateServiceImpl() {
	return geoStateServiceImpl;
    }

    public void setGeoStateServiceImpl(StateService geoStateServiceImpl) {
	this.geoStateServiceImpl = geoStateServiceImpl;
    }

    public CountryService getGeoCountryService() {
	return geoCountryService;
    }

    public void setGeoCountryService(CountryService geoCountryService) {
	this.geoCountryService = geoCountryService;
    }
}