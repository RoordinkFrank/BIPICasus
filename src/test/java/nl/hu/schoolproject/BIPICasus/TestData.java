package nl.hu.schoolproject.BIPICasus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.hu.schoolproject.BIPICasus.model.Adress;
import nl.hu.schoolproject.BIPICasus.model.BTWCode;
import nl.hu.schoolproject.BIPICasus.model.Bedrijf;
import nl.hu.schoolproject.BIPICasus.model.Factuur;
import nl.hu.schoolproject.BIPICasus.model.Klant;
import nl.hu.schoolproject.BIPICasus.model.Product;

public class TestData {
	public Map<String, Factuur> facturen = new HashMap<String, Factuur>();
	public List<Product> producten = new ArrayList<Product>();
	public Map<String, Bedrijf> bedrijven = new HashMap<String, Bedrijf>();
	public Map<String, Adress> adressen = new HashMap<String, Adress>();
	
	public TestData() {
		producten.add(new Product(0, "BifiTestProduct", 10, 12, BTWCode.LAAG, "Euro"));
		facturen.put("testInsertFactuur", new Factuur(LocalDateTime.of(1992, 9, 24, 4, 4, 4), 1, new Klant("1_5"), producten.get(0)));
		facturen.put("2de facuur", new Factuur(LocalDateTime.now(), 2, new Klant("2_5"), producten.get(0)));
		
		facturen.put("testRetrieveMontlyFacturen1", new Factuur(LocalDateTime.of(1992, 9, 28, 4, 8, 4), 3, new Klant("Frank"), producten.get(0)));
		facturen.put("testRetrieveMontlyFacturen2", new Factuur(LocalDateTime.of(1992, 9, 26, 4, 4, 4), 4, new Klant("Daan"), producten.get(0)));
		facturen.put("testRetrieveMontlyFacturen3", new Factuur(LocalDateTime.of(1991, 9, 26, 4, 4, 4), 5, new Klant("Joost"), producten.get(0)));
		
		adressen.put("normal", new Adress(122, "POST45", "utrecht", "ganzentraat"));
		adressen.put("PostcodeCijferTeVeel", new Adress(122, "POST455", "utrecht", "ganzentraat"));
		adressen.put("PostcodeLetterTeVeel", new Adress(122, "POSTT45", "utrecht", "ganzentraat"));
		bedrijven.put("werkendBedrijf", new Bedrijf("werkendBedrijf", adressen.get("normal"), "leBTWNummer" , "ING212423432", "32443FRT"));
		bedrijven.put("postcodeCijferTeVeel", new Bedrijf("werkendBedrijf", adressen.get("PostcodeCijferTeVeel"), "leBTWNummer", "ING212423432", "32443FRT"));
		bedrijven.put("postcodeLetterTeVeel", new Bedrijf("werkendBedrijf", adressen.get("PostcodeLetterTeVeel") , "leBTWNummer", "ING212423432", "32443FRT"));
		bedrijven.put("bicLengte10", new Bedrijf("werkendBedrijf", adressen.get("normal"), "leBTWNummer", "ING212423432", "32443FRTYY"));
		bedrijven.put("bicLengte11", new Bedrijf("werkendBedrijf", adressen.get("normal"), "leBTWNummer", "ING212423432", "32443FRTYYY"));
	}
}
