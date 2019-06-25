package nl.hu.schoolproject.BIPICasus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import nl.hu.schoolproject.BIPICasus.model.DatabaseName;
import nl.hu.schoolproject.BIPICasus.model.Factuur;
public class MongoConn {

	private static final String START_MONGO_LOGGING_ERROR = "XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX";
	private static Logger logger = LoggerFactory.getLogger(MongoConn.class);
	private static final DatabaseName DATABASE = DatabaseName.BIPICasus; // default
	private static final String COLLECTIONFACTUUR = "Factuur";
	private static final MongoClientURI uri = new MongoClientURI(
			"mongodb+srv://opdracht2admin:opdracht2frank@cluster0-725c7.mongodb.net/test?retryWrites=true");

	private MongoConn() {
	}

	public static boolean removeFactuur(int nummer) {
		return removeFactuur(nummer, DATABASE);
	}

	public static boolean removeFactuur(int nummer, DatabaseName name) {
		try (MongoClient mongoClient = new MongoClient(uri)) {
			MongoDatabase db = mongoClient.getDatabase(name.toString());
			MongoCollection<Document> c = db.getCollection(COLLECTIONFACTUUR);

			Iterator<Document> it = c.find().iterator();
			while (it.hasNext()) {
				Document document = it.next();
				if (nummer == (int) document.get("nummer")) {
					Bson filter = Filters.eq("nummer", nummer);
					c.deleteOne(filter);
				}
			}
			//MongoClient.close is handled auto by try catch.
		} catch (MongoException mongoException) {
			logger.info(START_MONGO_LOGGING_ERROR);
			logger.info(mongoException.getMessage());
		}
		return false;
	}
	
	public static List<Factuur> retrieveMontlyFacturen(int year, int month) {
		return retrieveMontlyFacturen(year, month, DATABASE);
	}
	
	public static List<Factuur> retrieveMontlyFacturen(int year, int month, DatabaseName name) {
		List<Factuur> montlyFacturen = new ArrayList<Factuur>();
		try (MongoClient mongoClient = new MongoClient(uri)) {
			MongoDatabase db = mongoClient.getDatabase(name.toString());
			MongoCollection<Document> c = db.getCollection(COLLECTIONFACTUUR);
			
			Iterator<Document> it = c.find().iterator();
			while (it.hasNext()) {
				Document document = it.next();
				
				Factuur f = Factuur.getFactuurVersion(document);
				if (f.getDate().getMonthValue() == month && f.getDate().getYear() == year) {
					montlyFacturen.add(f);
				}
			}
			//MongoClient.close is handled auto by try catch.
		} catch (MongoException mongoException) {
			logger.info(START_MONGO_LOGGING_ERROR);
			logger.info(mongoException.getMessage());
		}
		return montlyFacturen;
	}

	public static Document retrieveFactuur(int nummer) {
		return retrieveFactuur(nummer, DATABASE);
	}

	public static Document retrieveFactuur(int nummer, DatabaseName name) {

		try (MongoClient mongoClient = new MongoClient(uri)) {
			MongoDatabase db = mongoClient.getDatabase(name.toString());
			MongoCollection<Document> c = db.getCollection(COLLECTIONFACTUUR);

			Iterator<Document> it = c.find().iterator();
			while (it.hasNext()) {
				Document document = it.next();
				if (nummer == (int) document.get("nummer")) {
					return document;
				}
			}
			//MongoClient.close is handled auto by try catch.
		} catch (MongoException mongoException) {
			logger.info(START_MONGO_LOGGING_ERROR);
			logger.info(mongoException.getMessage());
		}
		return null;
	}

	public static boolean insertFactuur(Factuur factuur) {
		return insertFactuur(factuur, DATABASE);
	}

	public static boolean insertFactuur(Factuur factuur, DatabaseName name) {
		// nummer is unique voor factuur
		try (MongoClient mongoClient = new MongoClient(uri)) {

			MongoDatabase db = mongoClient.getDatabase(name.toString());
			MongoCollection<Document> c = db.getCollection(COLLECTIONFACTUUR);
			c.insertOne(Factuur.getDocumentVersion(factuur));
		} catch (MongoException mongoException) {
			logger.info(START_MONGO_LOGGING_ERROR);
			logger.info(mongoException.getMessage());
			return false;
		}
		return true;
	}

}
