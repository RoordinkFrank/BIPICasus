package nl.hu.schoolproject.BIPICasus;

import fileManagment.fileManager.FileManager;
import fileManagment.fileManager.FileManagerException;
import nl.hu.schoolproject.BIPICasus.model.Bedrijf;

public class InvoiceExportFormat {
	public void createInvoice(int invoiceNummer, Bedrijf bedrijf) throws FileManagerException {
		FileManager m = new FileManager();
		m.openFileWriter(""+invoiceNummer, ".txt");
		createBedrijfRegels(m, bedrijf);
		m.closeFileWriter();
	}
	
	private void createBedrijfRegels(FileManager m, Bedrijf bedrijf) throws FileManagerException {
		m.writeLine("Type: "+"B");
		m.writeLine("bedrijfsnaam: "+bedrijf.getBedrijfsnaam());
		m.writeLine("straat: "+bedrijf.getStraat());
		m.writeLine("huisnummer: "+bedrijf.getHuisnummer());
		m.writeLine("postcode: "+bedrijf.getPostcode());
		m.writeLine("plaats: "+bedrijf.getPlaats());
		m.writeLine("BTWNummer: "+bedrijf.getBTWNummer());
		m.writeLine("IBAN: "+bedrijf.getIBAN());
		m.writeLine("BIC: "+bedrijf.getBIC());
		m.writeLine("");
	}
}
