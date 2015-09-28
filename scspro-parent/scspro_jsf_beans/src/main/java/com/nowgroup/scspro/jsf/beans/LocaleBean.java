package com.nowgroup.scspro.jsf.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * A managed bean, which provide language selection list, and a value change
 * event listener to change the locale programmatically
 * 
 * @author torredie
 *
 */
@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private static Map<String, String> languages;
    static {
	languages = new LinkedHashMap<String, String>();
	languages.put("English", "en");
	languages.put("Espa�ol", "es");
    }

    public Map<String, String> getAvailableLanguages() {
	return languages;
    }

    private String locale = "es";

    public String getLocale() {
	return locale;
    }

    public void setLocale(String locale) {
	this.locale = locale;
    }

    public String changeLanguage() {
	return "changedLanguage";
    }
}
