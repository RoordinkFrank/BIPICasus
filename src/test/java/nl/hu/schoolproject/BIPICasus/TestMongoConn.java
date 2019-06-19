package nl.hu.schoolproject.BIPICasus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nl.hu.schoolproject.BIPICasus.model.BTWCode;
import nl.hu.schoolproject.BIPICasus.model.DatabaseName;
import nl.hu.schoolproject.BIPICasus.model.Factuur;
import nl.hu.schoolproject.BIPICasus.model.Klant;
import nl.hu.schoolproject.BIPICasus.model.Product;

public class TestMongoConn {

	static List<Factuur> facturen = new ArrayList<Factuur>();
	static List<Product> producten = new ArrayList<Product>();
	
	
	@BeforeAll
	private static void setup(){
		Calendar cal = Calendar.getInstance();
	    Date todayDate = new Date();
	    cal.setTime(todayDate);
	    
		
		
		producten.add(new Product(0, "BifiTestProduct", 10, 12, BTWCode.laag, "ditIsEenUnit"));
		facturen.add(new Factuur(todayDate, 1, new Klant("1_5"), producten.get(0)));
		facturen.add(new Factuur(todayDate, 2, new Klant("2_5"), producten.get(0)));
		
		
	}

	@Test
	public void testMongoInsert(){
		MongoConn.insertFactuur(facturen.get(0), DatabaseName.BIPICasusTest);
		Document doc = MongoConn.retrieveFactuur(1, DatabaseName.BIPICasusTest);
		Factuur factuur = Factuur.getFactuurVersion(doc);
		System.out.println(""+factuur);
		System.out.println(""+facturen.get(0));
		assertEquals(factuur, facturen.get(0));

		
	}
	
	@AfterAll
	private static void cleanup() {
		MongoConn.removeFactuur(1, DatabaseName.BIPICasusTest);
		//remove factuur werkt niet als er eerder wat (rood) crasht in de unit test.
		//vandaar dat het in een aparte cleanup methode staat.
	}
}
