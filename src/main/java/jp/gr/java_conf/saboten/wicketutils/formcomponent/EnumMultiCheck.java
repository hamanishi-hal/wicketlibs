package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import java.util.Collection;

import jp.gr.java_conf.saboten.wicketutils.util.EnumUtil;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

public class EnumMultiCheck <T extends Enum<?>> extends CheckBoxMultipleChoice<T> {

	private static final long serialVersionUID = 1L;

	private static final String PAD = " ";


	public EnumMultiCheck(String id, IModel<? extends Collection<T>> model, Class<? extends T> enumClass, T...ignoreEnums) {

		super(id, model, EnumUtil.getEnumList(enumClass, ignoreEnums));

		setChoiceRenderer(new IChoiceRenderer<T>() {
			private static final long serialVersionUID = 1L;
			public Object getDisplayValue(T value) {
				return value != null ? PAD + getString(resourceKey(value)) : null;
			}
			public String getIdValue(T value, int index) {
				return value.name();
			}
		});

		// default is "<br />\n"
		this.setSuffix("&nbsp;");

	}


	protected String resourceKey(T value) {
		return value.getDeclaringClass().getSimpleName() + "." + value.name();
	}
}
