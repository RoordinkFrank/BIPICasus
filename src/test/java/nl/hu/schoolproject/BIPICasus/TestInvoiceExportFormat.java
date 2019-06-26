package nl.hu.schoolproject.BIPICasus;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import filemanagment.filemanager.FileManagerException;
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
		//handmatige controles via files bekijken wel mogelijk
		List<Factuur> facturen = new ArrayList<Factuur>();
		facturen.add(testData.facturen.get("testRetrieveMontlyFacturen1"));
		facturen.add(testData.facturen.get("testRetrieveMontlyFacturen2"));
		
		InvoiceExportFormat invoiceFormat = new InvoiceExportFormat();
		invoiceFormat.createInvoice(testData.bedrijven.get("werkendBedrijf"), facturen);
		//Alle variatie van werkend bedrijf zouden hier eigenlijk opnieuw moeten worden getest.
	}
}
