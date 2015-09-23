package com.nowgroup.scspro.faces.beans;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;

import com.nowgroup.scspro.dto.BaseDTO;

public abstract class BaseFacesReporteableBean extends BaseFacesBean {
    private static final long serialVersionUID = -6643286985981902407L;
    private static Logger log = Logger.getLogger(BaseFacesReporteableBean.class.getName());
    private JasperPrint jasperPrint;

    @ManagedProperty("#{i18n_global}")
    private ResourceBundle globalMsgBundle;

    private boolean compileReport(String reportName) {
	String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + reportName + ".jasper");

	File jasperFile = new File(reportPath);
	if (!jasperFile.exists()) {
	    return true;
	}
	try {
	    String xmlFileName = "/reports/" + reportName + ".jrxml";
	    JasperCompileManager.compileReportToFile(xmlFileName, "/reports/" + reportName + ".jasper");
	    return true;
	} catch (Exception e) {
	    publishError(globalMsgBundle.getString("reports.compile.error") + ": " + reportName);
	    publishError(e.getLocalizedMessage());
	    return false;
	}
    }

    private void initializeReport(List<? extends BaseDTO> reportList, String reportName) throws JRException {
	log.debug("filling report with customers list.");
	JRBeanCollectionDataSource connectionDS = new JRBeanCollectionDataSource(reportList);

	if (!compileReport(reportName))
	    throw new JRException("Could not compile report.");

	String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + reportName + ".jasper");

	File jasperFile = new File(reportPath);
	if (!jasperFile.exists()) {
	}

	jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap<String, Object>(), connectionDS);
    }

    public void pdfReport(List<? extends BaseDTO> reportList, String reportName) throws JRException, IOException {
	initializeReport(reportList, reportName);
	log.debug("generating output report (" + reportName + ".pdf) with " + reportList.size() + " records");
	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	response.addHeader("Content-disposition", "attachment; filename=" + reportName + ".pdf");
	ServletOutputStream servletOutputStream = response.getOutputStream();
	JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
	FacesContext.getCurrentInstance().responseComplete();
    }

    public void xlsReport(List<? extends BaseDTO> reportList, String reportName) throws JRException, IOException {
	initializeReport(reportList, reportName);
	log.debug("generating output report (" + reportName + ".xlsx) with " + reportList.size() + " records");
	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	response.addHeader("Content-disposition", "attachment; filename=" + reportName + ".xlsx");
	ServletOutputStream servletOutputStream = response.getOutputStream();

	JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
	FacesContext.getCurrentInstance().responseComplete();
    }

    public ResourceBundle getGlobalMsgBundle() {
	return globalMsgBundle;
    }

    public void setGlobalMsgBundle(ResourceBundle globalMsgBundle) {
	this.globalMsgBundle = globalMsgBundle;
    }

}
