package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;


public class BoolRadioGroup extends RadioGroup<Boolean> {

	private static final long serialVersionUID = 1L;

	public BoolRadioGroup(final String id, IModel<Boolean> model) {
		super(id, model);
	}

	public BoolRadioGroup(final String id, IModel<Boolean> model, String idOfTrue, String idOfFalse) {
		super(id, model);
		setRadio(idOfTrue, true);
		setRadio(idOfFalse, false);
	}

	public BoolRadioGroup setRadio(String radioId, boolean type) {
		this.add(new Radio<Boolean>(radioId, Model.of(type)));
		return this;
	}
}
