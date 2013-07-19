package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;


public class EnumRadioGroup<T extends Enum<?>> extends RadioGroup<T> {

	private static final long serialVersionUID = 1L;

	public EnumRadioGroup(final String id, IModel<T> model) {
		super(id, model);
	}

	public EnumRadioGroup(final String id, IModel<T> model, Class<T> clazz, String... idOfEnumsWithOrder) {
		super(id, model);
		if (clazz.getEnumConstants().length != idOfEnumsWithOrder.length)
			throw new RuntimeException("");

		for (int i = 0; i < idOfEnumsWithOrder.length; i++) {
			addRadio(idOfEnumsWithOrder[i], clazz.getEnumConstants()[i]);
		}
	}

	public EnumRadioGroup<T> addRadio(String radioId, T type) {
		this.add(new Radio<T>(radioId, Model.of(type)));
		return this;
	}
}
