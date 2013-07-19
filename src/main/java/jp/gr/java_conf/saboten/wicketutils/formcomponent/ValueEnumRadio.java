package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import java.io.Serializable;

import jp.gr.java_conf.saboten.common.ValueEnum.IValueEnum;
import jp.gr.java_conf.saboten.wicketutils.model.ValueEnumConvertModel;

import org.apache.wicket.model.IModel;

public class ValueEnumRadio<E extends Enum<? extends IValueEnum<T>>, T extends Serializable>
extends EnumRadio<E> {


	private static final long serialVersionUID = 1L;



	@SuppressWarnings("unchecked")
	public ValueEnumRadio(String id, IModel<T> model, Class<E> enumClass) {
		this(id, model, enumClass, enumClass.getEnumConstants()[0]);
	}

	public ValueEnumRadio(String id, IModel<T> model, Class<E> enumClass, E defaultValue, E...ignoreEnums) {
		super(id,
			new ValueEnumConvertModel<E,T>(model, enumClass, defaultValue, false, defaultValue),
			enumClass, ignoreEnums);
	}

}
