package jp.gr.java_conf.saboten.wicketutils.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * {@link jp.gr.java_conf.saboten.wicketutils.formcomponent.DateField DateField}と連動したConverter
 *
 */
public class SqlDateConverter extends org.apache.wicket.util.convert.converter.SqlDateConverter {

	private static final long serialVersionUID = 1L;


	private String format;

	public SqlDateConverter(String format) {
		this.format = format;
	}

	public DateFormat getDateFormat(Locale locale) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setLenient(false); // 非寛容＝繰り上がりを許可しない
		return formatter;
	}
}
