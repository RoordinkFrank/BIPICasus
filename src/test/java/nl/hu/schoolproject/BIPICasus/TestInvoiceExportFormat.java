package nl.hu.schoolproject.BIPICasus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
	public void testCreateInvoice() throws FileManagerException{
		//kan alleen worden gecheckt zodra files ook weer uitgelezen worden,
		//maar dat is niet onderdeel van de opdracht?
		//handmatige controles via files bekijken wel mogelijk
		List<Factuur> facturen = new ArrayList<Factuur>();
		facturen.add(testData.facturen.get(2));
		facturen.add(testData.facturen.get(3));
		
		InvoiceExportFormat invoiceFormat = new InvoiceExportFormat();
		invoiceFormat.createInvoice(testData.bedrijven.get("werkendBedrijf"), facturen);
		invoiceFormat.createInvoice(testData.bedrijven.get("postcodeLetterTeVeel"), facturen);
		invoiceFormat.createInvoice(testData.bedrijven.get("postcodeCijferTeVeel"), facturen);
		//Alle variatie van werkend bedrijf zouden hier eigenlijk opnieuw moeten worden getest.
	}
}
