package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

public class EnumPulldown<T extends Enum<?>> extends DropDownChoice<T> {

	private static final long serialVersionUID = 1L;


	public EnumPulldown(final String id, IModel<T> model, Class<? extends T> enumClass, T...ignoreEnums) {

		super(id);
		setModel(model);

		List<T> enumClasses = new ArrayList<T>();
		List<T> ignores = Arrays.asList(ignoreEnums);
		for (T t : enumClass.getEnumConstants()) {
			if (!ignores.isEmpty() && !ignores.contains(t)) {
				enumClasses.add(t);
			}
		}
		setChoices(enumClasses);

		setChoiceRenderer(new IChoiceRenderer<T>() {
				private static final long serialVersionUID = 1L;
				public Object getDisplayValue(T value) {
					return value != null ? getString(resourceKey(value)) : null;
				}
				public String getIdValue(T value, int index) {
					return value.name();
				}
			});
	}

	protected String resourceKey(T value) {
		return value.getDeclaringClass().getSimpleName() + "." + value.name();
	}

//	private static <T extends Enum<T>> List<T> toArgs(Class<? extends T> enumClass, T...ignoreEnums) {
//		List<T> list = new ArrayList<T>();
//		for (T t : enumClass.getEnumConstants()) {
//			if (!ArrayUtils.contains(ignoreEnums, t)) {
//				list.add(t);
//			}
//		}
//		return list;
//	}
}