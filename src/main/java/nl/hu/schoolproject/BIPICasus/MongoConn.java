package nl.hu.schoolproject.BIPICasus;



import java.time.LocalDate;
import java.util.Iterator;

import org.bson.Document;
import org.slf4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import nl.hu.schoolproject.BIPICasus.model.Factuur;
import nl.hu.schoolproject.BIPICasus.model.Klant;

public class MongoConn {
	
	private static Logger logger = LoggerFactory.getLogger(MongoConn.class);
	
	private final static String DATABASE = "BIPICasus";
	private final static String COLLECTIONFACTUUR = "Factuur";
	private final static MongoClientURI uri = new MongoClientURI("mongodb+srv://opdracht2admin:opdracht2frank@cluster0-725c7.mongodb.net/test?retryWrites=true");
	
	
	
	private MongoConn() {}
	
	public static boolean retrieveFactuur(int nummer) {
		
try (MongoClient mongoClient = new MongoClient(uri) ) {
			MongoDatabase db = mongoClient.getDatabase(DATABASE);
			MongoCollection<Document> c = db.getCollection(COLLECTIONFACTUUR);
			
			Iterator<Document> it = c.find().iterator();
			while(it.hasNext())
			{
				Document document = it.next();		
				System.out.println(document);
			}
				
			mongoClient.close();//is did nodig of wordt dit door de try clausule automatisch aangeroepen?	
		} catch (MongoException mongoException) {
			logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX");
			mongoException.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	public static boolean insertFactuur(Factuur factuur) {
		//nummer is unique voor factuur
	
		try (MongoClient mongoClient = new MongoClient(uri) ) {
			
			MongoDatabase db = mongoClient.getDatabase(DATABASE);
			MongoCollection<Document> c = db.getCollection(COLLECTIONFACTUUR);
			c.insertOne(factuur.getDocument());

		} catch (MongoException mongoException) {
			logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX");
			mongoException.printStackTrace();
			return false;
		}
		return true;
	}

}
