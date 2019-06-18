package nl.hu.schoolproject.BIPICasus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nl.hu.schoolproject.BIPICasus.model.BTWCode;
import nl.hu.schoolproject.BIPICasus.model.Factuur;
import nl.hu.schoolproject.BIPICasus.model.Klant;
import nl.hu.schoolproject.BIPICasus.model.Product;

public class TestMongoConn {

	static List<Factuur> facturen = new ArrayList<Factuur>();
	static List<Product> producten = new ArrayList<Product>();
	
	
	@BeforeAll
	private static void setup(){
		producten.add(new Product(0, "BifiTestProduct", 10, 12, BTWCode.laag, "ditIsEenUnit"));
		facturen.add(new Factuur(LocalDate.now(), 1, new Klant("1_5"), producten.get(0)));
		
	}

	@Test
	public void testMongoInsert(){
		MongoConn.insertFactuur(facturen.get(0));
	}
	
	@Test
	public void testMongoGet() {
		MongoConn.retrieveFactuur(1);
	}
}
