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
		m.write("Factuurdatum: "+BIPIUtil.getDate(factuur.getDate())+" ");
		m.writeLine("Factuurnummer: "+factuur.getNummer());
		createFactuurRegel(m, factuur);
		//Hier zou je 1 of meer factuurRegels eraan kunnen koppelen maar ik snap niet echt in welk geval dit speelt.
	}

	private void createFactuurRegel(FileManager m, Factuur factuur) throws FileManagerException {
		m.write("Type: "+"R"+" ");
		m.write("Productomschrijving: "+factuur.getProduct().getProductNaame()+" ");
		m.write("Productomschrijving: "+factuur.getProduct().getProductNaame()+" ");
		m.write("Aantal: "+factuur.getProduct().getQuantity()+" ");
		m.write("Prijs per stuk: "+factuur.getProduct().getTotaalprijsExBTWp()+" ");
		m.write("BTW type: "+factuur.getProduct().getBtwCode()+" ");
		m.write("Regel datum: "+BIPIUtil.getDate(factuur.getDate())+" ");
		m.write("Regel tijd: "+BIPIUtil.getTime(factuur.getDate())+" ");
		m.writeLine("Eenheid: "+factuur.getProduct().getUnit()+" ");
	}
}