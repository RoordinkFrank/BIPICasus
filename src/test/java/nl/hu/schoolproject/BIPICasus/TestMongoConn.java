package nl.hu.schoolproject.BIPICasus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nl.hu.schoolproject.BIPICasus.model.DatabaseName;
import nl.hu.schoolproject.BIPICasus.model.Factuur;

public class TestMongoConn {

	private static TestData testData;
	
	@BeforeAll
	private static void setup(){
		testData = new TestData();
	}

	@Test
	public void testMongoInsert(){
		MongoConn.insertFactuur(testData.facturen.get("testInsertFactuur"), DatabaseName.BIPICasusTest);
		Document doc = MongoConn.retrieveFactuur(1, DatabaseName.BIPICasusTest);
		Factuur factuur = Factuur.getFactuurVersion(doc);
		assertEquals(factuur, testData.facturen.get("testInsertFactuur"));
	}
	
	@Test
	public void testRetrieveMontlyFacturen() {
		MongoConn.insertFactuur(testData.facturen.get("testRetrieveMontlyFacturen1"), DatabaseName.BIPICasusTest);
		MongoConn.insertFactuur(testData.facturen.get("testRetrieveMontlyFacturen2"), DatabaseName.BIPICasusTest);
		MongoConn.insertFactuur(testData.facturen.get("testRetrieveMontlyFacturen3"), DatabaseName.BIPICasusTest);
		List<Factuur> montlyFacturen = MongoConn.retrieveMontlyFacturen(1992, 9);
			System.out.println(montlyFacturen.size());
			assertEquals(montlyFacturen.size(), 2);
			assertEquals(testData.facturen.get("testRetrieveMontlyFacturen1"), montlyFacturen.get(0));
			assertEquals(testData.facturen.get("testRetrieveMontlyFacturen2"), montlyFacturen.get(1));
			//Als dit synchroon is gaat het goed, mocht dit asynchroon gaan dan moet hier wat
			//meer moeite gedaan worden.
	}
	
	@AfterAll
	private static void cleanup() {
		MongoConn.removeFactuur(1, DatabaseName.BIPICasusTest);
		MongoConn.removeFactuur(3, DatabaseName.BIPICasusTest);
		MongoConn.removeFactuur(4, DatabaseName.BIPICasusTest);
		MongoConn.removeFactuur(5, DatabaseName.BIPICasusTest);
		//remove factuur werkt niet als er eerder wat (rood) crasht in de unit test.
		//vandaar dat het in een aparte cleanup methode staat.
	}
}