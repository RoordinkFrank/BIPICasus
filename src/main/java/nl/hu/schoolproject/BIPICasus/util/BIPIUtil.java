package nl.hu.schoolproject.BIPICasus.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class BIPIUtil {
	//Java8+, werkt niet in Mongo zo te zien.
	public static String getCurrentTime() {
		LocalDate date = LocalDate.now(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
	    return date.format(formatter);
	}
}
