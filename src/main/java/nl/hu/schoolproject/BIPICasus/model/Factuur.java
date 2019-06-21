package nl.hu.schoolproject.BIPICasus.model;

import java.time.LocalDate;
import java.util.Date;

import org.bson.Document;

import com.mongodb.BasicDBObject;

public class Factuur{
	private Date date;
	//the object that comes out of the mongoDB can not cast to LocalDate, only Date.
	private int nummer;
	private Klant klant;
	private Product product;
	
	public Factuur() {}
	public Factuur(Date date, int nummer, Klant klant, Product product) {
		super();
		this.date = date;
		this.nummer = nummer;
		this.klant = klant;
		this.setProduct(product);
	}
	
	public static Factuur getFactuurVersion(Document mongoObject) {
		Factuur f = new Factuur();
		f.date = (Date) mongoObject.get("date");
		f.nummer = (int)mongoObject.get("nummer");
		f.klant =  Klant.getKlantVersion((Document) mongoObject.get("klant"));
		f.product = Product.getProductVersion((Document) mongoObject.get("product"));
		return f;
	}
	
	public static Document getDocumentVersion(Factuur localF) {
		Document  doc = new Document ("date", localF.getDate())
		        .append("nummer", localF.getNummer())
		        .append("klant",  Klant.getDocumentVersion(localF.getKlant()))
		        .append("product", Product.getDocumentVersion(localF.getProduct()));
		return doc;
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	public Klant getKlant() {
		return klant;
	}
	public void setKlant(Klant klant) {
		this.klant = klant;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "Factuur [date=" + date + ", nummer=" + nummer + ", klant=" + klant + ", product=" + product
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((klant == null) ? 0 : klant.hashCode());
		result = prime * result + nummer;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		Factuur other = (Factuur) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (klant == null) {
			if (other.klant != null)
				return false;
		} else if (!klant.equals(other.klant))
			return false;
		if (nummer != other.nummer)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}
