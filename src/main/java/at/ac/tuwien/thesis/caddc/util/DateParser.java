package at.ac.tuwien.thesis.caddc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 
 * @author Andreas Egger
 */
public class DateParser {

	
	private static final HashMap<String, String> validDateFormats = new HashMap<String, String>() {{
	    put("^\\d{1,2}-.{3}-\\d{4}$", "dd-MMM-yyyy");
	    put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
	    put("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}$", "dd.MM.yyyy");
	    put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
	    put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
	    put("^\\d{8}$", "yyyyMMdd");
	}};
	
	private static final HashMap<String, String> validDateTimeFormats = new HashMap<String, String>() {{
	    put("^\\d{1,2}-.{3}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MMM-yyyy HH:mm:ss");
	    put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
	    put("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd.MM.yyyy HH:mm:ss");
	    put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
	    put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
	    put("^\\d{8}\\s\\d{1,2}\\d{2}\\d{2}$", "yyyyMMdd HHmmss");
	    put("^\\d{8}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyyMMdd HH:mm:ss");
	    put("^\\d{1,2}-.{3}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MMM-yyyy HH:mm");
	    put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
	    put("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\s\\d{1,2}:\\d{2}$", "dd.MM.yyyy HH:mm");
	    put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
	    put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
	    put("^\\d{8}\\s\\d{1,2}\\d{2}\\d{2}$", "yyyyMMdd HHmmss");
	    put("^\\d{8}\\s\\d{2}\\d{2}$", "yyyyMMdd HHmm");
	}};
	
	
	/**
	 * Parse the given date string based on the regex definitions above
	 * @param dateString the date string to parse
	 * @return a date object corresponding to the given date string
	 */
	public static Date parseDate(String dateString) {
		String pattern = checkDatePattern(dateString);
		if(pattern == null) {
			System.err.println("DateParser: Date pattern not recognized");
			return null;
		}
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date result = sdf.parse(dateString);
			return result;
		} catch (ParseException e) {
			System.err.println("Could not parse date: "+e.getLocalizedMessage());
		}
		return null;
	}
	
	public static String checkDatePattern(String dateString) {
		for (String regexp : validDateTimeFormats.keySet()) {
	        if (dateString.toLowerCase().matches(regexp)) {
	            return validDateTimeFormats.get(regexp);
	        }
	    }
	    for (String regexp : validDateFormats.keySet()) {
	        if (dateString.toLowerCase().matches(regexp)) {
	            return validDateFormats.get(regexp);
	        }
	    }
	    return null; // Unknown format.
	}
}
