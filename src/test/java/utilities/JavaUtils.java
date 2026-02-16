package utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static config.EnvVars.dateFormat;

public class JavaUtils {

	public String getTodayDate() {

		return LocalDate.now().format(DateTimeFormatter.ofPattern(dateFormat));
	}

	public String getDateFromNoOfDaysToday(int toAddDays) {

		return LocalDate.now().plusDays(toAddDays).format(DateTimeFormatter.ofPattern(dateFormat));

	}

}
