package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import java.io.Serializable;

import jp.gr.java_conf.saboten.common.ValueEnum.IValueEnum;
import jp.gr.java_conf.saboten.wicketutils.model.ValueEnumConvertModel;

import org.apache.wicket.model.IModel;

public class ValueEnumPulldown<E extends Enum<? extends IValueEnum<T>>, T extends Serializable>
extends EnumPulldown<E> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ValueEnumPulldown(String id, IModel<T> model, Class<E> enumClass, E... ignoreEnums) {
		super(id, new ValueEnumConvertModel<E,T>(model, enumClass), enumClass, ignoreEnums);
	}

//	@SuppressWarnings("unchecked")
//	public ValueEnumPulldown(String id, IModel<T> model, Class<E> enumClass) {
//		super(id, new ValueEnumConvertModel<E,T>(model, enumClass), enumClass);
//	}
}
