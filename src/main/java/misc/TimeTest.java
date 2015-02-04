package misc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeTest {

	public static void main(String[] args) throws Exception {

		//             1352700469351
		String time = "134752590000";
		SimpleDateFormat timeformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String dateStr = timeformat.format(new Date(Long.parseLong(time)));
		Date date = timeformat.parse(dateStr);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		int year = gc.get(Calendar.YEAR);
		int day = gc.get(Calendar.DAY_OF_YEAR);
		int hour = gc.get(Calendar.HOUR_OF_DAY);
		int minute = gc.get(Calendar.MINUTE);

		System.out.println(year);
		System.out.println(day);
		System.out.println(hour);
		System.out.println(minute);
		
		Calendar date1 = Calendar.getInstance();
		date1.setTimeInMillis(Long.parseLong(time));
		System.out.println(date1.get(Calendar.YEAR));
		
		
		System.out.println(new Date().getTime());
		
	}

}
