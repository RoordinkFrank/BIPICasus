package nl.hu.schoolproject.BIPICasus.model;

import org.bson.Document;

import com.mongodb.BasicDBObject;

public class Product extends BasicDBObject{

	private int productID;
	private String productNaame;
	private double quantity;
	private double totaalprijsExBTWp;
	private BTWCode btwCode;
	private String unit;

	public Product() {
	};

	public Product(int productID, String productNaame, double quantity, double totaalprijsExBTWp, BTWCode btwCode,
			String unit) {
		super();
		this.productID = productID;
		this.productNaame = productNaame;
		this.quantity = quantity;
		this.totaalprijsExBTWp = totaalprijsExBTWp;
		this.btwCode = btwCode;
		this.unit = unit;
	}
	
    public Document getDocument() {
    	Document doc = new Document("productId", productID)
		        .append("productNaame", productNaame)
		        .append("quantity", quantity)
		        .append("totaalprijsExBTWp", totaalprijsExBTWp)
		        .append("btwCode", btwCode.toString())
		        .append("unit", unit);
    	return doc;
		        
    }

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductNaame() {
		return productNaame;
	}

	public void setProductNaame(String productNaame) {
		this.productNaame = productNaame;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getTotaalprijsExBTWp() {
		return totaalprijsExBTWp;
	}

	public void setTotaalprijsExBTWp(double totaalprijsExBTWp) {
		this.totaalprijsExBTWp = totaalprijsExBTWp;
	}

	public BTWCode getBtwCode() {
		return btwCode;
	}

	public void setBtwCode(BTWCode btwCode) {
		this.btwCode = btwCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
