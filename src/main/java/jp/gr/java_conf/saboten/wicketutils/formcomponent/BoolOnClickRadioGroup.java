package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import org.apache.wicket.model.IModel;


public class BoolOnClickRadioGroup extends BoolRadioGroup {

	private static final long serialVersionUID = 1L;

	public BoolOnClickRadioGroup(final String id, IModel<Boolean> model) {
		super(id, model);
	}

	public BoolOnClickRadioGroup(final String id, IModel<Boolean> model, String idOfTrue, String idOfFalse) {
		super(id, model, idOfTrue, idOfFalse);
	}

	@Override
	protected boolean wantOnSelectionChangedNotifications() {
		return true;
	}

	@Override
	protected void onSelectionChanged(final Object newSelection) {
		// Modelは、バリデーションをパスする場合は更新される。ので、このメソッドを必ずしも実装する必要は無い。
	}
}
