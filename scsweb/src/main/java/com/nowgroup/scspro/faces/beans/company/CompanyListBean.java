package com.nowgroup.scspro.faces.beans.company;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.nowgroup.scspro.dto.cat.Company;
import com.nowgroup.scspro.dto.cat.CompanyScope;
import com.nowgroup.scspro.dto.geo.State;
import com.nowgroup.scspro.model.CompanyModel;
import com.nowgroup.scspro.service.cat.CompanyScopeService;
import com.nowgroup.scspro.service.cat.CompanyService;

@ManagedBean
@ViewScoped
public class CompanyListBean {
	private static Logger log = Logger.getLogger(CompanyListBean.class
			.getName());

	// Injected in applicationContext.xml
	@ManagedProperty("#{companyServiceImpl}")
	private CompanyService companyServiceImpl;

	@ManagedProperty("#{companyScopeServiceImpl}")
	private CompanyScopeService companyScopeServiceImpl;

	@ManagedProperty("#{i18n_companies}")
	private ResourceBundle companyMsgBundle;

	private List<CompanyModel> companies = new ArrayList<CompanyModel>();

	public List<CompanyModel> getCompanies() {
		// only query once for the view
		if (!companies.isEmpty())
			return companies;
		if (companyMsgBundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			setCompanyMsgBundle(context.getApplication().getResourceBundle(
					context, "i18n_companies"));
			log.debug("Got forced resource bundle: " + companyMsgBundle);
		}
		List<Company> dbCompanies = getCompanyServiceImpl().getCompanies();
		List<CompanyModel> companiesList = new ArrayList<CompanyModel>();
		log.debug("describing " + dbCompanies.size() + " companies.");
		for (Company dbCompany : dbCompanies) {
			log.debug("Setting up roles for " + dbCompany.getName());
			CompanyModel model = new CompanyModel(dbCompany);
			model.setDisplayRoles("");

			List<CompanyScope> scopes = getCompanyServiceImpl()
					.getCompanyScope(model.getId());
			log.debug("scopes in company:" + scopes.size());
			for (CompanyScope scope : scopes) {
				String roleName = getCompanyScopeServiceImpl()
						.getRoleInCompanyScope(scope.getId()).getName();
				// String roleName = scope.getCompanyRole().getName();
				log.debug("translating " + roleName);
				String translated = companyMsgBundle
						.getString("companies.profiles." + roleName);
				log.debug("translated as " + translated);
				model.setDisplayRoles(model.getDisplayRoles() + ", "
						+ translated);
			}

			if (model.getDisplayRoles().length() > 2) {
				model.setDisplayRoles(model.getDisplayRoles().substring(2));
			}
			companiesList.add(model);
		}
		companies = companiesList;
		return companiesList;
	}

	public String getCompanyState(int id) {
		String result = "";

		State geoStateInDb = getCompanyServiceImpl().getStateInCompanyId(id);

		result = geoStateInDb == null || geoStateInDb.getName() == null ? ""
				: geoStateInDb.getName();
		return result;
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
		log.debug("Setting company message bundle: "
				+ companyMsgBundle.getBaseBundleName());
		this.companyMsgBundle = companyMsgBundle;
	}

	public CompanyScopeService getCompanyScopeServiceImpl() {
		return companyScopeServiceImpl;
	}

	public void setCompanyScopeServiceImpl(
			CompanyScopeService companyScopeServiceImpl) {
		this.companyScopeServiceImpl = companyScopeServiceImpl;
	}
}