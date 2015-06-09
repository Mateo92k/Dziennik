package Dziennik;

import java.io.FileInputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;

public class DrukowanieNaWywiadowke {
	
//	public static void main(String[] args)
//	{
//		//todo: dokonczyc ta klase
//		FileInputStream textStream;
//		textStream = new FileInputStream(FILE_NAME);
//
//		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
//		Doc mydoc = new SimpleDoc(textStream, flavor, null);
//
//		   PrintService[] services = PrintServiceLookup.lookupPrintServices(
//						flavor, aset);
//		   PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
//
//		   if(services.length == 0) {
//		       if(defaultService == null) {
//		             //no printer found
//
//		       } else {
//		            //print using default
//		            DocPrintJob job = defaultService.createPrintJob();
//		            job.print(mydoc, aset);
//
//		       }
//
//		    } else {
//
//		       //built in UI for printing you may not use this
//		       PrintService service = ServiceUI.printDialog(null, 200, 200, services, defaultService, flavor, aset);
//
//
//		        if (service != null)
//		        {
//		           DocPrintJob job = service.createPrintJob();
//		           job.print(mydoc, aset);
//		        }
//
//		    }
//
//	}
}
