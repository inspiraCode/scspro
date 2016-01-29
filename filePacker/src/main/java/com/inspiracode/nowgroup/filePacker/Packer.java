package com.inspiracode.nowgroup.filePacker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

public class Packer {

    public static void main(String[] args) {
	if (args.length < 1 || args.length > 2) {
	    printUsage();
	}

	Packer packer = new Packer();
	packer.setFileDirectory(args[0]);

	if (args.length == 2) {
	    packer.setPurchaserAlias(args[1]);
	}

	packer.packFiles();
    }

    private static void printUsage() {
	System.out.println("No se encontraron los parámetros suficientes para ejecutar la acción.");
	System.out.println("Utilice este programa de la siguiente manera:");
	System.out.println("Packer directorio-expediente [alias]");
	System.out.println("DONDE:");
	System.out.println("directorio-expediente: *REQUERIDO. Es la ruta donde se encuentra el conjunto de archivos del expediente.");
	System.out.println("    Este programa fue diseñado para crear paquetes de agrupación que puedan ser entregados a los clientes");
	System.out.println("    el directorio de expediente es el directorio donde se encuentra el conjunto de documentos a entregar.");
	System.out.println("alias: [opcional]. Es el alias del comprador de la mercancia que se describe en los documentos del expediente.");
    }

    private String fileDirectory;
    private File fileDir;
    private String purchaserAlias = "alias";

    public void packFiles() {
	System.out.println("******************************************************");
	System.out.println("*         CREANDO PAQUETES DE EXPEDIENTE             *");
	System.out.println("*         v1.2                                       *");
	System.out.println("******************************************************");
	try {
	    fileDir = new File(fileDirectory);
	    System.out.println("Explorando expediente: " + fileDir.getName());
	    File[] directories = fileDir.listFiles(new FilenameFilter() {
		public boolean accept(File dir, String name) {
		    File temp = new File(dir, name);
		    return temp.isDirectory() && (temp.getName().startsWith("100") || temp.getName().startsWith("210"));
		}
	    });
	    ZipFiles(directories);
	    Arrays.sort(directories, new Comparator<File>() {
		public int compare(File o1, File o2) {
		    return o1.getName().compareTo(o2.getName());
		}
	    });
	    MergePDFs(directories);
	    System.out.println("******************************************************");
	    System.out.println("*                      TERMINADO                     *");
	    System.out.println("******************************************************");
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void MergePDFs(File[] directories) throws IOException, DocumentException {
	System.out.println(">>> Consolidando PDFs >>>>>");
	List<InputStream> list = new ArrayList<InputStream>();
	for (File dir : directories) {
	    File[] pdfs = dir.listFiles(new FilenameFilter() {
		public boolean accept(File dir, String name) {
		    File temp = new File(dir, name);
		    return temp.isFile() && temp.getName().toLowerCase().endsWith("pdf");
		}
	    });
	    Arrays.sort(pdfs, new Comparator<File>() {
		public int compare(File o1, File o2) {
		    if (o1.getName().startsWith("TRAD")) {
			return -1;
		    }

		    return o1.getName().compareTo(o2.getName());
		}
	    });
	    for (File pdf : pdfs) {
		System.out.println(">>> archivo pdf encontrado para ser consolidado: " + pdf.getPath());
		list.add(new FileInputStream(pdf));
	    }
	}

	System.out.println(">>> consolidacion de PDFs en archivo: " + fileDir.getPath() + "\\" + fileDir.getName() + " " + purchaserAlias + ".pdf");
	OutputStream out = new FileOutputStream(new File(fileDir.getPath() + "\\" + fileDir.getName() + " " + purchaserAlias + ".pdf"));

	Merge(list, out);
	System.out.println(">>> PDFs Consolidados >>>>>");
    }

    private void Merge(List<InputStream> list, OutputStream os) throws DocumentException, IOException {
	Document document = new Document();
	PdfWriter writer = PdfWriter.getInstance(document, os);
	document.open();
	PdfContentByte cb = writer.getDirectContent();
	for (InputStream in : list) {
	    PdfReader reader = new PdfReader(in);
	    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
		document.newPage();
		// import the page from source pdf
		PdfImportedPage page = writer.getImportedPage(reader, i);
		// add the page to the destination pdf
		cb.addTemplate(page, 0, 0);
	    }
	}

	os.flush();
	document.close();
	os.close();
    }

    private void ZipFiles(File[] directories) throws IOException {
	System.out.println("### Comprimiendo expediente en archivo zip #####");
	byte[] buffer = new byte[1024];
	FileOutputStream fos = new FileOutputStream(fileDir.getPath() + "\\" + fileDir.getName() + " " + purchaserAlias + ".zip");
	ZipOutputStream zos = new ZipOutputStream(fos);
	for (File directory : directories) {
	    ZipEntry parentZipDir = new ZipEntry(directory.getName() + "/");
	    zos.putNextEntry(parentZipDir);
	    File[] files = directory.listFiles();
	    for (File file : files) {
		if (file.isFile()) {
		    ZipEntry fileZipEntry = new ZipEntry(parentZipDir + "/" + file.getName());
		    zos.putNextEntry(fileZipEntry);
		    FileInputStream in = new FileInputStream(file);
		    int len;
		    while ((len = in.read(buffer)) > 0) {
			zos.write(buffer, 0, len);
		    }
		    in.close();
		    zos.closeEntry();
		}
	    }
	}
	zos.close();
	System.out.println("### Expediente comprimido exitosamente #####");
    }

    public String getFileDirectory() {
	return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory) {
	this.fileDirectory = fileDirectory;
    }

    public String getPurchaserAlias() {
	return purchaserAlias;
    }

    public void setPurchaserAlias(String purchaserAlias) {
	this.purchaserAlias = purchaserAlias;
    }

}
