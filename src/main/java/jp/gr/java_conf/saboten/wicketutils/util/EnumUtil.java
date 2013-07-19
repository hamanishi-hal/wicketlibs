package jp.gr.java_conf.saboten.wicketutils.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Application;

public class EnumUtil {

	public static <T extends Enum<?>> String getResourceKey(T value) {
		if (value == null)
			return null;
		return value.getDeclaringClass().getSimpleName() + "." + value.name();
	}

	public static <T extends Enum<?>> String getResourceStr(T value) {
		String key = getResourceKey(value);
		if (key == null)
			return null;
		return Application.get().getResourceSettings().getLocalizer().getString(key, null);
	}


	public static <E extends Enum<?>> List<E> getEnumList(Class<? extends E> enumClass, E...ignoreEnums) {
		List<E> ignores = Arrays.asList(ignoreEnums);
		List<E> enumClasses = new ArrayList<E>();
		for (E t : enumClass.getEnumConstants()) {
			if (!ignores.isEmpty() && !ignores.contains(t)) {
				enumClasses.add(t);
			}
		}
		return enumClasses;
	}
}
