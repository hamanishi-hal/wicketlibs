package jp.gr.java_conf.saboten.wicketutils.converter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class DoubleConverter extends org.apache.wicket.util.convert.converter.DoubleConverter {

	private static final long serialVersionUID = 1L;

	public static DoubleConverter INSTANCE = new DoubleConverter();

	@Override
	public NumberFormat getNumberFormat(Locale locale) {
		// defaultでは少数4位までしか表示されない
		return new DecimalFormat("#,###.#############");
	}
}
