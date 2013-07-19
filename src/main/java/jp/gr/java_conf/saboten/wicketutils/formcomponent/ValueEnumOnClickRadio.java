package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import java.io.Serializable;

import jp.gr.java_conf.saboten.common.ValueEnum.IValueEnum;

import org.apache.wicket.model.IModel;

public abstract class ValueEnumOnClickRadio<E extends Enum<? extends IValueEnum<T>>, T extends Serializable>
extends ValueEnumRadio<E, T> {

	private static final long serialVersionUID = 1L;


	public ValueEnumOnClickRadio(String id, IModel<T> model, Class<E> enumClass, E defaultValue, E...ignoreEnums) {
		super(id, model, enumClass, defaultValue, ignoreEnums);
	}

	protected boolean wantOnSelectionChangedNotifications() {
		return true;
	}

	@SuppressWarnings("unchecked")
	protected void onSelectionChanged(Object newSelection) {
		ValueEnumOnClickRadio.this.onSelect((E) newSelection);
	}

	/**
	 * Modelも更新される実装なので、Modelから値を取得しても同じ
	 */
	protected abstract void onSelect(E selected);
}
