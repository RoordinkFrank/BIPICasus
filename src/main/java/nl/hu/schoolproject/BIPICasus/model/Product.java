package nl.hu.schoolproject.BIPICasus.model;

import org.bson.Document;


import nl.hu.schoolproject.BIPICasus.Formatter;

public class Product{

	private int productID;
	private String productNaame;
	private double quantity;
	private double totaalprijsExBTWp;
	private BTWCode btwCode;
	private String unit;
	
	private StringBuilder sb;

	public Product() {
		sb = new StringBuilder();
	};
	public Product(int productID, String productNaame, double quantity, double totaalprijsExBTWp, BTWCode btwCode,
			String unit) {
		super();
		sb = new StringBuilder();
		this.productID = productID;
		this.productNaame = productNaame;
		sb.append(setQuantity(quantity));
		sb.append(setTotaalprijsExBTWp(totaalprijsExBTWp));
		this.btwCode = btwCode;
		this.unit = unit;
	}
	

	public String checkConstructionErrors() {
		return sb.toString();
	}
	
	public static Product getProductVersion (Document mongoObject) {
		Product p = new Product();
		p.productID = (int)mongoObject.get("productID");
		p.productNaame = (String)mongoObject.get("productNaame");
		p.quantity = (double)mongoObject.get("quantity");
		p.totaalprijsExBTWp = (double)mongoObject.get("totaalprijsExBTWp");
		p.btwCode = BTWCode.valueOf((String)mongoObject.get("btwCode"));
		p.unit = (String)mongoObject.get("unit");
		return p;
	}
	
    public static Document getDocumentVersion(Product localP) {
    	return new Document("productID", localP.productID)
		        .append("productNaame", localP.productNaame)
		        .append("quantity", localP.quantity)
		        .append("totaalprijsExBTWp", localP.totaalprijsExBTWp)
		        .append("btwCode", localP.btwCode.toString())
		        .append("unit", localP.unit);
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

	public String setQuantity(double quantity) {
		String error = Formatter.checkFactuurPrijsAllowed(quantity);
        if (error.equals("")) {
        	this.quantity = quantity;
        }
        return error;
	}

	public double getTotaalprijsExBTWp() {
		return totaalprijsExBTWp;
	}

	public String setTotaalprijsExBTWp(double totaalprijsExBTWp) {
		String error = Formatter.checkFactuurPrijsAllowed(totaalprijsExBTWp);
        if (error.equals("")) {
        	this.totaalprijsExBTWp = totaalprijsExBTWp;
        }
        return error;
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

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productNaame=" + productNaame + ", quantity=" + quantity
				+ ", totaalprijsExBTWp=" + totaalprijsExBTWp + ", btwCode=" + btwCode + ", unit=" + unit + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((btwCode == null) ? 0 : btwCode.hashCode());
		result = prime * result + productID;
		result = prime * result + ((productNaame == null) ? 0 : productNaame.hashCode());
		long temp;
		temp = Double.doubleToLongBits(quantity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totaalprijsExBTWp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		Product other = (Product) obj;
		if (btwCode != other.btwCode)
			return false;
		if (productID != other.productID)
			return false;
		if (productNaame == null) {
			if (other.productNaame != null)
				return false;
		} else if (!productNaame.equals(other.productNaame))
			return false;
		if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
			return false;
		if (Double.doubleToLongBits(totaalprijsExBTWp) != Double.doubleToLongBits(other.totaalprijsExBTWp))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}
}
