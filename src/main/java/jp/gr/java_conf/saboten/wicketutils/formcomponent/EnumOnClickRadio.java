package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import org.apache.wicket.model.IModel;

public abstract class EnumOnClickRadio<T extends Enum<?>> extends EnumRadio<T> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public EnumOnClickRadio(String id, Class<? extends T> enumClass) {
		super(id, enumClass);
	}

	@SuppressWarnings("unchecked")
	public EnumOnClickRadio(String id, IModel<T> model, Class<? extends T> enumClass) {
		super(id, model, enumClass);
	}

	public EnumOnClickRadio(String id, IModel<T> model, Class<? extends T> enumClass, T...ignoreEnums) {
		super(id, model, enumClass, ignoreEnums);
	}

	protected boolean wantOnSelectionChangedNotifications() {
		return true;
	}

	@SuppressWarnings("unchecked")
	protected void onSelectionChanged(Object newSelection) {
		EnumOnClickRadio.this.onSelect((T) newSelection);
	}

	/**
	 * Modelも更新される実装なので、Modelから値を取得しても同じ
	 */
	protected abstract void onSelect(T selected);
}
