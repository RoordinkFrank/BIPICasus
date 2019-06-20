package nl.hu.schoolproject.BIPICasus.model;

import nl.hu.schoolproject.BIPICasus.Formatter;

public class Bedrijf {
	
	private String bedrijfsnaam;
	private String Straat;
	private int huisnummer;
	private String postcode;
	private String plaats;
	private String BTWNummer;
	private String IBAN;
	private String BIC;
	
	private StringBuilder sb;
	
	public Bedrijf(String bedrijfsnaam, String straat, int huisnummer, String postcode, String plaats, String bTWNummer,
			String iBAN, String bIC) {
		super();
        sb = new StringBuilder();
		sb.append(setBedrijfsnaam(bedrijfsnaam));
		sb.append(setStraat(straat));
		sb.append(setHuisnummer(huisnummer));
		sb.append(setPostcode(postcode));
		sb.append(setPlaats(plaats));
		sb.append(setBTWNummer(bTWNummer));
		sb.append(setIBAN(iBAN));
		sb.append(setBIC(bIC));
	}
	
	public String checkConstructionErrors() {
		return sb.toString();
	}
	
	public String getBedrijfsnaam() {
		return bedrijfsnaam;
	}
	public String setBedrijfsnaam(String bedrijfsnaam){
		String error = Formatter.checkMaxLength(bedrijfsnaam, "bedrijfsnaam", 60);
        if (error.equals("")) {
    		this.bedrijfsnaam = bedrijfsnaam;
        }
        return error;
	}
	public String getStraat() {
		return Straat;
	}
	public String setStraat(String straat) {
		String error = Formatter.checkMaxLength(straat, "straat", 60);
        if (error.equals("")) {
    		Straat = straat;
        }
        return error;
	}
	public int getHuisnummer() {
		return huisnummer;
	}
	public String setHuisnummer(int huisnummer) {
		String error = Formatter.checkMaxLength(huisnummer, "huisnummer", 10);
        if (error.equals("")) {
        	this.huisnummer = huisnummer;
        }
        return error;
	}
	public String getPostcode() {
		return postcode;
	}
	public String setPostcode(String postcode) {
		String error = Formatter.checkPostcode(postcode);
        if (error.equals("")) {
        	this.postcode = postcode;
        }
        return error;
	}
	public String getPlaats() {
		return plaats;
	}
	public String setPlaats(String plaats) {
		String error = Formatter.checkMaxLength(plaats, "plaats", 20);
        if (error.equals("")) {
        	this.plaats = plaats;
        }
        return error;
	}
	public String getBTWNummer() {
		return BTWNummer;
	}
	public String setBTWNummer(String bTWNummer) {
		String error = Formatter.checkMaxLength(bTWNummer, "bTWNummer", 13);
        if (error.equals("")) {
        	BTWNummer = bTWNummer;
        }
        return error;	
	}
	public String getIBAN() {
		return IBAN;
	}
	public String setIBAN(String iBAN) {
		String error = Formatter.checkMaxLength(iBAN, "iBAN", 64);
        if (error.equals("")) {
        	IBAN = iBAN;
        }
        return error;	
	}
	public String getBIC() {
		return BIC;
	}
	public String setBIC(String bIC) {
		String error = Formatter.checkMaxLength(bIC, "bIC", 10);
        if (error.equals("")) {
    		BIC = bIC;
        }
        return error;	
	}
	@Override
	public String toString() {
		return "Bedrijf [bedrijfsnaam=" + bedrijfsnaam + ", Straat=" + Straat + ", huisnummer=" + huisnummer
				+ ", postcode=" + postcode + ", plaats=" + plaats + ", BTWNummer=" + BTWNummer + ", IBAN=" + IBAN
				+ ", BIC=" + BIC + "]";
	}

}
