package nl.hu.schoolproject.BIPICasus.model;

import org.bson.Document;


public class Klant{
	
	//String door het format
	private String klantID;
	
	private StringBuilder sb;
	
	public Klant() {
		sb = new StringBuilder();
	}
	public Klant(String id) {
		setId(id);
		sb = new StringBuilder();
		//sb doet voorlopig nog niets in deze klasse.
	}

	public static Klant getKlantVersion(Document mongoObject) {
		Klant k = new Klant();
		k.klantID = (String)mongoObject.get("klantID");
		return k;
	}
	
	public String checkConstructionErrors() {
		return sb.toString();
	}
	
	public static Document getDocumentVersion(Klant localK) {
    	return new Document("klantID", localK.klantID);
    }
	
	public String getId() {
		return klantID;
	}

	//klant behoort in het format <klantID>_<PersoonID> te zijn.
	public void setId(String id) {
		klantID = id;
	}
	@Override
	public String toString() {
		return "Klant [klantID=" + klantID + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((klantID == null) ? 0 : klantID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Klant other = (Klant) obj;
		if (klantID == null) {
			if (other.klantID != null)
				return false;
		} else if (!klantID.equals(other.klantID))
			return false;
		return true;
	}
}
