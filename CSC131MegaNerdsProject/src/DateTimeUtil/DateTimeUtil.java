package DateTimeUtil;

import java.time.LocalDateTime;

/*
 * Class: DateTimeUtil
 * Provides helper methods related to Date and Time
 */

public class DateTimeUtil {
	
	//accepts two Strings and returns a LocalDateTime
	public static LocalDateTime dateTimeStringParse(String date, String time) {
		String temp = date;
		String y = time;
			
		String temp2 = "";
		String temp3 = "";
		String temp4 = "";
			
		String temp5 = "";
		String temp6 = "";
		String temp7 = "";
			
		String result = "";
		try {
			temp2 = temp.substring(0,4); //year
			temp3 = temp.substring(5,7); //month
			temp4 = temp.substring(8,temp.length()); //day
			
			temp5 = y.substring(0,2);//hour
			temp6 = y.substring(3,5);//minute
			temp7 = y.substring(6,8);//seconds

			result = temp2 + "-" + temp3 + "-" + temp4 + "T" + temp5 + ":" + temp6 + ":" + temp7;
		
			return LocalDateTime.parse(result);
		} catch (Exception e) {
			return null;
		}
	}
}
