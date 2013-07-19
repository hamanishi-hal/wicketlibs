package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import jp.gr.java_conf.saboten.wicketutils.util.EnumUtil;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.IModel;


public class EnumRadio<T extends Enum<?>> extends RadioChoice<T> {

	private static final long serialVersionUID = 1L;

	private static final String PAD = " ";


	public EnumRadio(String id, Class<? extends T> enumClass, T...ignoreEnums) {
		super(id, EnumUtil.getEnumList(enumClass, ignoreEnums));
		init();
	}

	public EnumRadio(String id, IModel<T> model, Class<? extends T> enumClass, T...ignoreEnums) {
		super(id, model, EnumUtil.getEnumList(enumClass, ignoreEnums));
		init();
	}


	protected void init() {
		setChoiceRenderer(new EnumChoiceRenderer());
		// default is "<br />\n"
		this.setSuffix(" ");
	}

	protected String resourceKey(T value) {
		return value.getDeclaringClass().getSimpleName() + "." + value.name();
	}

	private class EnumChoiceRenderer implements IChoiceRenderer<T> {

		private static final long serialVersionUID = 1L;

		public Object getDisplayValue(T value) {
			return value != null ? PAD + getString(resourceKey(value)) : null;
		}

		public String getIdValue(T value, int index) {
			return value.name();
		}
	}
}
