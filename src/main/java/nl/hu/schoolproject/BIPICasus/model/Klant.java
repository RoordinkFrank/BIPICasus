package nl.hu.schoolproject.BIPICasus.model;

import org.bson.Document;

import com.mongodb.BasicDBObject;

public class Klant extends BasicDBObject{
	
	public Klant() {}
	public Klant(String id) {
		setId(id);
	}
	
	private String klantID;

	
	public Document getDocument() {
    	Document doc = new Document("klantID", klantID);
    	return doc;
    }
	
	
	public String getId() {
		return klantID;
	}

	//klant behoort in het format <klantID>_<PersoonID> te zijn.
	public void setId(String id) {
		klantID = id;
	}
}
