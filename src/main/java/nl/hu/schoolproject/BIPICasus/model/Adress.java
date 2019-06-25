package nl.hu.schoolproject.BIPICasus.model;

import nl.hu.schoolproject.BIPICasus.Formatter;

public class Adress {
	//Bedrijf mag niet meer dan 8 constructor argumenten hebben.
	//Created met auto move.
	public int huisnummer;
	public String postcode;
	public String plaats;
	private String straat;

	private StringBuilder sb;
	
	public Adress(int huisnummer, String postcode, String plaats, String straat) {
		sb = new StringBuilder();
		sb.append(setHuisnummer(huisnummer));
		sb.append(setPostcode(postcode));
		sb.append(setPlaats(plaats));
		sb.append(setStraat(straat));
	}
	
	public String checkConstructionErrors() {
		return sb.toString();
	}
	
	public String getStraat() {
		return straat;
	}
	public String setStraat(String straat) {
		String error = Formatter.checkMaxLength(straat, "straat", 60);
        if (error.equals("")) {
    		this.straat = straat;
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

	@Override
	public String toString() {
		return "Adress [huisnummer=" + huisnummer + ", postcode=" + postcode + ", plaats=" + plaats + ", straat="
				+ straat + ", sb=" + sb + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + huisnummer;
		result = prime * result + ((plaats == null) ? 0 : plaats.hashCode());
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + ((sb == null) ? 0 : sb.hashCode());
		result = prime * result + ((straat == null) ? 0 : straat.hashCode());
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
		Adress other = (Adress) obj;
		if (huisnummer != other.huisnummer)
			return false;
		if (plaats == null) {
			if (other.plaats != null)
				return false;
		} else if (!plaats.equals(other.plaats))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (sb == null) {
			if (other.sb != null)
				return false;
		} else if (!sb.equals(other.sb))
			return false;
		if (straat == null) {
			if (other.straat != null)
				return false;
		} else if (!straat.equals(other.straat))
			return false;
		return true;
	}
}