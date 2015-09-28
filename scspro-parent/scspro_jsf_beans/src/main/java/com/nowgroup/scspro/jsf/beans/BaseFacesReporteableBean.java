package com.nowgroup.scspro.jsf.beans;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import org.apache.log4j.Logger;

import com.nowgroup.scspro.dto.BaseDTO;

public abstract class BaseFacesReporteableBean extends BaseFacesBean {
    private static final long serialVersionUID = -6643286985981902407L;
    private static Logger log = Logger.getLogger(BaseFacesReporteableBean.class.getName());
    private JasperPrint jasperPrint;

    @ManagedProperty("#{i18n_global}")
    private ResourceBundle globalMsgBundle;

    private boolean compileReport(String reportName) {
	log.debug("Compiling report name " + reportName);
	String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + reportName + ".jasper");

	File jasperFile = new File(reportPath);
	log.debug("looking for file " + reportPath);
	if (!jasperFile.exists()) {
	    try {
		String xmlFileName = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + reportName + ".jrxml");
		log.debug("First file not found, looking for file " + xmlFileName);
		jasperFile = new File(xmlFileName);

		if (!jasperFile.exists())
		    throw new Exception("report sourcecode file " + xmlFileName + "not found");

		JasperCompileManager.compileReportToFile(xmlFileName, reportPath);
	    } catch (Exception e) {
		log.error(e.getMessage(), e);
		publishError(globalMsgBundle.getString("reports.compile.error") + ": " + reportName);
		publishError(e.getLocalizedMessage());
		return false;
	    }
	}

	return true;
    }

    private void initializeReport(List<? extends BaseDTO> reportList, String reportName, ResourceBundle msg) throws JRException {
	log.debug("filling report with customers list.");
	JRBeanCollectionDataSource connectionDS = new JRBeanCollectionDataSource(reportList);

	if (!compileReport(reportName))
	    throw new JRException("Could not compile report.");

	String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + reportName + ".jasper");

	File jasperFile = new File(reportPath);
	if (!jasperFile.exists()) {
	    throw new JRException("Report not found " + reportPath);
	}
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("REPORT_RESOURCE_BUNDLE", msg);
	log.debug("Report title loaded as " + msg.getString("report.title") + " as locale: " + FacesContext.getCurrentInstance().getViewRoot().getLocale());
	params.put("REPORT_LOCALE", FacesContext.getCurrentInstance().getViewRoot().getLocale());
	jasperPrint = JasperFillManager.fillReport(reportPath, params, connectionDS);
    }

    public String pdfReport(List<? extends BaseDTO> reportList, String reportName, ResourceBundle msg) throws JRException, IOException {
	initializeReport(reportList, reportName, msg);
	log.debug("generating output report (" + reportName + ".pdf) with " + reportList.size() + " records");
	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	response.addHeader("Content-disposition", "attachment; filename=" + reportName + ".pdf");
	ServletOutputStream servletOutputStream = response.getOutputStream();
	JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
	FacesContext.getCurrentInstance().responseComplete();
	return "";
    }

    public String xlsReport(List<? extends BaseDTO> reportList, String reportName, ResourceBundle msg) throws JRException, IOException {
	initializeReport(reportList, reportName, msg);
	log.debug("generating output report (" + reportName + ".xlsx) with " + reportList.size() + " records");
	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	response.addHeader("Content-disposition", "attachment; filename=" + reportName + ".xlsx");
	ServletOutputStream servletOutputStream = response.getOutputStream();

	JRXlsxExporter docxExporter = new JRXlsxExporter();
	docxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	docxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(servletOutputStream));

	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
	configuration.setOnePagePerSheet(false);
	configuration.setDetectCellType(true);
	configuration.setCollapseRowSpan(false);
	docxExporter.setConfiguration(configuration);

	docxExporter.exportReport();

	FacesContext.getCurrentInstance().responseComplete();
	return "";
    }

    public ResourceBundle getGlobalMsgBundle() {
	return globalMsgBundle;
    }

    public void setGlobalMsgBundle(ResourceBundle globalMsgBundle) {
	this.globalMsgBundle = globalMsgBundle;
    }

}
