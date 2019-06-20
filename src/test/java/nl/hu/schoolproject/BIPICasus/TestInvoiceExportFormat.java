package nl.hu.schoolproject.BIPICasus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.bson.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fileManagment.fileManager.FileManagerException;
import nl.hu.schoolproject.BIPICasus.model.DatabaseName;
import nl.hu.schoolproject.BIPICasus.model.Factuur;

public class TestInvoiceExportFormat {
private static TestData testData;
	
	@BeforeAll
	private static void setup(){
		testData = new TestData();
	}
	
	@Test
	public void testCreateInvoioce() throws FileManagerException{
		//kan alleen worden gecheckt zodra files ook weer uitgelezen worden,
		//maar dat is niet onderdeel van de opdracht?
		//handmatige controles via files bekijken wel mogelijk
		InvoiceExportFormat invoiceFormat = new InvoiceExportFormat();
		invoiceFormat.createInvoice(1 ,testData.bedrijven.get("werkendBedrijf"));
		invoiceFormat.createInvoice(2 ,testData.bedrijven.get("postcodeLetterTeVeel"));
		invoiceFormat.createInvoice(3 ,testData.bedrijven.get("postcodeCijferTeVeel"));
		//Alle variatie van werkend bedrijf zouden hier eigenlijk opnieuw moeten worden getest.
	}
}
