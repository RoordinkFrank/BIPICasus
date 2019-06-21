package nl.hu.schoolproject.BIPICasus.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

	public static LocalDateTime ConvertStringToLocalDateTime(String string) {
		//binnenkomende format "2019-06-21T16:37:46.331541800"
		String[] split = string.split("-|T|:|\\.");		
		LocalDateTime localDateTime = LocalDateTime.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]), Integer.valueOf(split[3]), Integer.valueOf(split[4]), Integer.valueOf(split[5]));
		return localDateTime;
	}
}
