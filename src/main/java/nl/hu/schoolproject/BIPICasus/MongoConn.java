package nl.hu.schoolproject.BIPICasus;

import java.time.LocalDate;
import java.util.Iterator;

import org.bson.Document;
import org.bson.conversions.Bson;
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
import com.mongodb.client.model.Filters;

import nl.hu.schoolproject.BIPICasus.model.DatabaseName;
import nl.hu.schoolproject.BIPICasus.model.Factuur;
import nl.hu.schoolproject.BIPICasus.model.Klant;

public class MongoConn {

	private static Logger logger = LoggerFactory.getLogger(MongoConn.class);
	private static DatabaseName DATABASE = DatabaseName.BIPICasus; // default
	private final static String COLLECTIONFACTUUR = "Factuur";
	private final static MongoClientURI uri = new MongoClientURI(
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
			mongoClient.close();// is did nodig of wordt dit door de try clausule automatisch aangeroepen?
		} catch (MongoException mongoException) {
			logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX");
			mongoException.printStackTrace();
		}
		return false;
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
			mongoClient.close();// is did nodig of wordt dit door de try clausule automatisch aangeroepen?
		} catch (MongoException mongoException) {
			logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX");
			mongoException.printStackTrace();
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
			logger.info("inserted "+factuur.toString());
		} catch (MongoException mongoException) {
			logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX");
			mongoException.printStackTrace();
			return false;
		}
		return true;
	}

}
