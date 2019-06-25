package nl.hu.schoolproject.BIPICasus;

import java.util.List;

import filemanagment.filemanager.FileManager;
import filemanagment.filemanager.FileManagerException;
import nl.hu.schoolproject.BIPICasus.model.Bedrijf;
import nl.hu.schoolproject.BIPICasus.model.Factuur;
import nl.hu.schoolproject.BIPICasus.util.BIPIUtil;

public class InvoiceExportFormat {
	private static final String SPACE = " ";
	private static final String TYPE = "Type: ";

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
		m.write (TYPE+"B"+SPACE);
		m.write ("bedrijfsnaam: "+bedrijf.getBedrijfsnaam()+SPACE);
		m.write ("straat: "+bedrijf.getAdress().getStraat()+SPACE);
		m.write ("huisnummer: "+bedrijf.getAdress().getHuisnummer()+SPACE);
		m.write ("postcode: "+bedrijf.getAdress().getPostcode()+SPACE);
		m.write ("plaats: "+bedrijf.getAdress().getPlaats()+SPACE);
		m.write ("BTWNummer: "+bedrijf.getBTWNummer()+SPACE);
		m.write ("IBAN: "+bedrijf.getIBAN()+SPACE);
		m.writeLine ("BIC: "+bedrijf.getBIC());
	}
	
	//Het is me niet duidelijk wat invoice precies inhoudt, Het heeft een factuurnummer
	//maar in welk scenario geeft dat meer dan 1 factuur?
	private void createInvoiceInformatieRegel(FileManager m, Factuur factuur) throws FileManagerException {
		m.write(TYPE+"F"+SPACE);
		m.write("Factuurdatum: "+BIPIUtil.getDate(factuur.getDate())+SPACE);
		m.writeLine("Factuurnummer: "+factuur.getNummer());
		createFactuurRegel(m, factuur);
		//Hier zou je 1 of meer factuurRegels eraan kunnen koppelen maar ik snap niet echt in welk geval dit speelt.
	}

	private void createFactuurRegel(FileManager m, Factuur factuur) throws FileManagerException {
		m.write(TYPE+"R"+SPACE);
		m.write("Productomschrijving: "+factuur.getProduct().getProductNaame()+SPACE);
		m.write("Productomschrijving: "+factuur.getProduct().getProductNaame()+SPACE);
		m.write("Aantal: "+factuur.getProduct().getQuantity()+SPACE);
		m.write("Prijs per stuk: "+factuur.getProduct().getTotaalprijsExBTWp()+SPACE);
		m.write("BTW type: "+factuur.getProduct().getBtwCode()+SPACE);
		m.write("Regel datum: "+BIPIUtil.getDate(factuur.getDate())+SPACE);
		m.write("Regel tijd: "+BIPIUtil.getTime(factuur.getDate())+SPACE);
		m.writeLine("Eenheid: "+factuur.getProduct().getUnit());
	}
}