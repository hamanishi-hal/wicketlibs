package jp.gr.java_conf.saboten.wicketutils.converter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FloatConverter extends org.apache.wicket.util.convert.converter.FloatConverter {

	private static final long serialVersionUID = 1L;

	public static FloatConverter INSTANCE = new FloatConverter();

	@Override
	public NumberFormat getNumberFormat(Locale locale) {
		// defaultでは少数4位までしか表示されない
		return new DecimalFormat("#,###.#############");
	}
}
