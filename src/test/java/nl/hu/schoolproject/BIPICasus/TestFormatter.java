package nl.hu.schoolproject.BIPICasus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fileManagment.fileManager.FileManagerException;

public class TestFormatter {
	private static TestData testData;
	
	@BeforeAll
	private static void setup(){
		testData = new TestData();
	}
	
	@Test
	public void testCheckPostcode() throws FileManagerException{
		assertEquals("", testData.bedrijven.get("werkendBedrijf").checkConstructionErrors());
	}
	
	@Test
	public void testPostcodeLetterTeVeel(){
		assertEquals("postcode is not in the A-Zx4 0-9X2 format", testData.bedrijven.get("postcodeLetterTeVeel").checkConstructionErrors());
	}
	
	@Test
	public void testPostcodeCijferTeVeel(){
		assertEquals("postcode is not in the A-Zx4 0-9X2 format", testData.bedrijven.get("postcodeCijferTeVeel").checkConstructionErrors());
	}
	
	@Test
	public void testcheckMaxLength(){
		assertEquals("", testData.bedrijven.get("werkendBedrijf").checkConstructionErrors());
	}
	
	@Test
	public void testcheckMaxLengthRandGeval(){
		assertEquals("", testData.bedrijven.get("bicLengte10").checkConstructionErrors());
	}
	
	@Test
	public void testcheckMaxLengthExceeded(){
		assertEquals("Max length of bIC can not be over 10", testData.bedrijven.get("bicLengte11").checkConstructionErrors());
	}
	
	//cijfers te weinig moeten eigenlijk ook nog.
	//int versie van checkMaxLength moet eigenlijk ook nog.
}
