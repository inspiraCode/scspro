package com.inspiracode.nowgroup.pdfPageExtractor;

import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

public class Splitter {

    /**
     * USAGE
     * Splitter sourceFile destinationFile [pagesToSplit] [fromPage]
     * sourceFile: origin pdf file, should be the receipt scan file.
     * destinationFile: pdf file name to write the split pages. Should be the guide number file pdf.
     * pagesToSplit: number that represent the amount of pages to split. Default as 1.
     * fromPage: page number to start the split (inclusive). defaults to 2.
     * @param args
     */
    public static void main(String[] args) {
	if (args.length < 2 || args.length > 4) {
	    printUsage();
	}

	Splitter splitter = new Splitter();
	splitter.setSourceFile(args[0].toLowerCase());
	splitter.setDestinationFile(args[1].toLowerCase());

	try {
	    if (args.length >= 3)
		splitter.setPagesToSplit(Integer.parseInt(args[2]));

	    if (args.length == 4)
		splitter.setFromPage(Integer.parseInt(args[3]));

	} catch (Exception e) {
	    System.out.println("ERROR: " + e.getMessage());
	    printUsage();
	}

	splitter.splitFile();
    }

    private static void printUsage() {
	System.out.println("No se encontraron los parámetros suficientes para ejecutar la acción.");
	System.out.println("Utilice este programa de la siguiente manera:");
	System.out.println("Splitter archivo-fuente archivo-destino [páginas-a-separar] [desde-la-página]");
	System.out.println("DONDE:");
	System.out.println("archivo-fuente: *REQUERIDO. Es la ruta donde se encuentra el archivo pdf desde donde se van a separar");
	System.out.println("    algunas páginas. Este programa fue diseñado para separar la guia de transporte desde el pdf del recibo");
	System.out.println("    el archivo fuente representa entonces, la ruta al pdf que contiene las imagenes escaneadas del recibo.");
	System.out.println("archivo-destino: *REQUERIDO. Es la ruta donde se va a grabar el arcivo pdf resultante del procedimiento");
	System.out.println("    de este programa. Este programa fue diseñado para separar la guia de transporte desde del pdf del recibo");
	System.out.println("    el archivo destino representa entonces, la ruta al pdf donde se van a guardar las imagenes separadas.");
	System.out.println("    el archivo destino representa entonces, la ruta al pdf donde se van a guardar las imagenes separadas.");
	System.out.println("páginas a separar: [opcional]. Es la cantidad de páginas que han de ser separadas del archivo original.");
	System.out.println("    si no se especifica un número, se separará 1 página.");
	System.out.println("desde la página: [opcional]. Es la página desde la cual se va a iniciar la separación.");
	System.out.println("    si no se especifica un número, se separará a partir de la página 2.");
    }

    private String sourceFile;
    private String destinationFile;
    private int pagesToSplit = 1;
    private int fromPage = 2;

    public void splitFile() {
	System.out.println("******************************************************");
	System.out.println("*                      PROCESANDO                    *");
	System.out.println("******************************************************");
	try {
	    System.out.println("Leyendo archivo: " + sourceFile);
	    PdfReader reader = new PdfReader(sourceFile);
	    int pages = reader.getNumberOfPages();
	    if (pages < fromPage + pagesToSplit - 1) {
		System.out.println("NO HAY SUFICIENTES PÁGINAS!!!");
		return;
	    }

	    System.out.println("Escribiendo archivo: " + destinationFile);

	    Document document = new Document(reader.getPageSizeWithRotation(1));
	    PdfCopy writer = new PdfCopy(document, new FileOutputStream(destinationFile));
	    document.open();
	    for (int i = fromPage; i < fromPage + pagesToSplit; i++) {
		System.out.println("Escribiendo página " + i);
		PdfImportedPage page = writer.getImportedPage(reader, i);
		writer.addPage(page);
	    }
	    document.close();
	    writer.close();

	    System.out.println("******************************************************");
	    System.out.println("*                      TERMINADO                     *");
	    System.out.println("******************************************************");

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String getSourceFile() {
	return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
	this.sourceFile = sourceFile;
    }

    public String getDestinationFile() {
	return destinationFile;
    }

    public void setDestinationFile(String destinationFile) {
	this.destinationFile = destinationFile;
    }

    public int getPagesToSplit() {
	return pagesToSplit;
    }

    public void setPagesToSplit(int pagesToSplit) {
	this.pagesToSplit = pagesToSplit;
    }

    public int getFromPage() {
	return fromPage;
    }

    public void setFromPage(int fromPage) {
	this.fromPage = fromPage;
    }
}
