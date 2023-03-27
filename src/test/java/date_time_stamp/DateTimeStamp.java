package date_time_stamp;

import java.util.Date;

public class DateTimeStamp {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date.toString());
		String replacedDate = date.toString().replace(" ", "_");
		System.out.println(replacedDate);
		String finalReplacedDate = replacedDate.replace(":", "_");
		System.out.println(finalReplacedDate);
		

	}

}
