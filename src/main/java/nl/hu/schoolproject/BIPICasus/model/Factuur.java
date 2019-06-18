package nl.hu.schoolproject.BIPICasus.model;

import java.time.LocalDate;

import org.bson.Document;

import com.mongodb.BasicDBObject;

public class Factuur{
	

	private LocalDate localDate;
	private int nummer;
	private Klant klant;
	private Product product;
	
	public Factuur() {}
	public Factuur(LocalDate localDate, int nummer, Klant klant, Product product) {
		super();
		this.localDate = localDate;
		this.nummer = nummer;
		this.klant = klant;
		this.setProduct(product);
	}
	
	
	public Document getDocument() {
		Document  doc = new Document ("localDate", getLocalDate())
		        .append("nummer", getNummer())
		        .append("klant", getKlant().getDocument())
		        .append("product", getProduct().getDocument());
		return doc;
	}
	
	
	public LocalDate getLocalDate() {
		return localDate;
	}
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
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

}
