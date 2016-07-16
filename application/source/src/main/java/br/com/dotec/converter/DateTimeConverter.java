package br.com.dotec.converter;
import java.util.ResourceBundle;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

@Convert(DateTime.class)
public class DateTimeConverter implements Converter<DateTime> {
	
	private static final DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
	
	public DateTime convert(String dateTime, Class<? extends DateTime> arg1,ResourceBundle arg2) {
		return fmt.parseDateTime(dateTime);
	}
}