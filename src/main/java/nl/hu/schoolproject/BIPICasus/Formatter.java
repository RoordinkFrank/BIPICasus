package nl.hu.schoolproject.BIPICasus;

//Should technically be checked in the UI as well.
//Exceptions are EXPECTED and heavy, String will be giving back instead.
public class Formatter{
	public static String checkMaxLength(String line, String lineName, int maxLenght){
		if (maxLenght < line.length()) {
			return "Max length of "+lineName+" can not be over "+maxLenght;
		}
		return "";
	}
	
	public static String checkMaxLength(int line, String lineName, int maxLenght){
		if (maxLenght > line) {
			return "Max length of "+lineName+" can not be over "+maxLenght;
		}
		return "";
	}
	
	public static String checkPostcode(String postcode) {
		if (postcode.matches("[a-zA-Z]{4}[0-9]{2}")) {
			return "";
		}
		return "postcode is not in the A-Zx4 0-9X2 format";
	}
}
