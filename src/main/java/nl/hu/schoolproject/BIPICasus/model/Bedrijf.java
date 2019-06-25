package nl.hu.schoolproject.BIPICasus.model;

import nl.hu.schoolproject.BIPICasus.Formatter;

public class Bedrijf {
	
	private String bedrijfsnaam;
	private Adress adress;
	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	private String bTWNummer;
	private String iBAN;
	private String bIC;
	
	private StringBuilder sb;
	
	public Bedrijf(String bedrijfsnaam, String straat, int huisnummer, String postcode, String plaats, String bTWNummer,
			String iBAN, String bIC) {
		super();
        sb = new StringBuilder();
		sb.append(setBedrijfsnaam(bedrijfsnaam));
		adress = new Adress(huisnummer, postcode, plaats, straat);
		sb.append(adress.checkConstructionErrors());
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

	public String getBTWNummer() {
		return bTWNummer;
	}
	public String setBTWNummer(String bTWNummer) {
		String error = Formatter.checkMaxLength(bTWNummer, "bTWNummer", 13);
        if (error.equals("")) {
        	this.bTWNummer = bTWNummer;
        }
        return error;	
	}
	public String getIBAN() {
		return iBAN;
	}
	public String setIBAN(String iBAN) {
		String error = Formatter.checkMaxLength(iBAN, "iBAN", 64);
        if (error.equals("")) {
        	this.iBAN = iBAN;
        }
        return error;	
	}
	public String getBIC() {
		return bIC;
	}
	public String setBIC(String bIC) {
		String error = Formatter.checkMaxLength(bIC, "bIC", 10);
        if (error.equals("")) {
    		this.bIC = bIC;
        }
        return error;	
	}

	@Override
	public String toString() {
		return "Bedrijf [bedrijfsnaam=" + bedrijfsnaam + ", adress=" + adress + ", bTWNummer=" + bTWNummer + ", iBAN="
				+ iBAN + ", bIC=" + bIC + ", sb=" + sb + "]";
	}

}
