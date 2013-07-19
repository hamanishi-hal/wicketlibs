package jp.gr.java_conf.saboten.wicketutils.util;

import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.Localizer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.MapModel;

public class MessageUtils {

	private static Localizer getLocalizer() {
		return Application.get().getResourceSettings().getLocalizer();
	}

	public static String getString(String key) {
		return getLocalizer().getString(key, null);
	}

	public static String getString(String key, Component c) {
		return getLocalizer().getString(key, c);
	}

	public static String getString(String key, Map<String, Object> param) {
		return getLocalizer().getString(key, null, new MapModel<String, Object>(param));
	}

	public static String getString(String key, Map<String, Object> param, Component c) {
		return getLocalizer().getString(key, c, new MapModel<String, Object>(param));
	}

	public static String getString(String key, IModel<?> param) {
		return getLocalizer().getString(key, null, param);
	}

	public static String getString(String key, IModel<?> param, Component c) {
		return getLocalizer().getString(key, c, param);
	}
}
