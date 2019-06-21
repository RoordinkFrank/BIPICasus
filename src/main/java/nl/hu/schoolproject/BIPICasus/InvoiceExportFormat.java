package nl.hu.schoolproject.BIPICasus;

import java.util.Date;
import java.util.List;

import fileManagment.fileManager.FileManager;
import fileManagment.fileManager.FileManagerException;
import nl.hu.schoolproject.BIPICasus.model.Bedrijf;
import nl.hu.schoolproject.BIPICasus.model.Factuur;
import nl.hu.schoolproject.BIPICasus.util.BIPIUtil;

public class InvoiceExportFormat {
	public void createInvoice(Bedrijf bedrijf, List<Factuur> facturen) throws FileManagerException {
		FileManager m = new FileManager(); //fileManager kan locatie veranderen mocht dat gewenst zijn.
		m.openFileWriter("InvoiceExport "+BIPIUtil.getCurrentTime(), "txt");
		createBedrijfRegel(m, bedrijf);
		
		for (Factuur factuur : facturen) {
			createInvoiceInformatieRegel(m, factuur);
		}
		m.closeFileWriter();
	}
	
	private void createBedrijfRegel(FileManager m, Bedrijf bedrijf) throws FileManagerException {
		m.write ("Type: "+"B"+" ");
		m.write ("bedrijfsnaam: "+bedrijf.getBedrijfsnaam()+" ");
		m.write ("straat: "+bedrijf.getStraat()+" ");
		m.write ("huisnummer: "+bedrijf.getHuisnummer()+" ");
		m.write ("postcode: "+bedrijf.getPostcode()+" ");
		m.write ("plaats: "+bedrijf.getPlaats()+" ");
		m.write ("BTWNummer: "+bedrijf.getBTWNummer()+" ");
		m.write ("IBAN: "+bedrijf.getIBAN()+" ");
		m.writeLine ("BIC: "+bedrijf.getBIC());
	}
	
	//Het is me niet duidelijk wat invoice precies inhoudt, Het heeft een factuurnummer
	//maar in welk scenario geeft dat meer dan 1 factuur?
	private void createInvoiceInformatieRegel(FileManager m, Factuur factuur) throws FileManagerException {
		m.write("Type: "+"F"+" ");
		m.write("Factuurdatum ");
		//FACTUUR MOET NAAR DATE EN TIJD APART.
		m.writeLine("invoiceNummer: ");
	}
}