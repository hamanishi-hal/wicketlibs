package jp.gr.java_conf.saboten.wicketutils.component;

import java.text.DecimalFormat;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class NumberLabel extends Label {

	private static final long serialVersionUID = 1L;

	private static final String RESOURCE_KEY_IF_EMPTY = "NumberLabelIfEmpty";
	private static final String RESOURCE_KEY_FORMAT = "NumberLabelFormat";


	public NumberLabel(String id, Number val) {
		super(id, Model.of(val));
	}

	public NumberLabel(String id, IModel<? extends Number> model) {
		super(id, model);
	}


	protected String getStringIfNotEmpty() {
		Number value = (Number) getDefaultModelObject();
		return new DecimalFormat(getFormat()).format(value);
	}


	protected String getStringIfEmpty() {
		return getLocalizer().getString(RESOURCE_KEY_IF_EMPTY, this);
	}

	protected String getFormat() {
		return getLocalizer().getString(RESOURCE_KEY_FORMAT, this);
	}
}
