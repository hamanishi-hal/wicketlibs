package jp.gr.java_conf.saboten.wicketutils.model;

import java.io.Serializable;

import jp.gr.java_conf.saboten.common.ValueEnum;
import jp.gr.java_conf.saboten.common.ValueEnum.IValueEnum;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class ValueEnumConvertModel<E extends Enum<? extends IValueEnum<T>>, T extends Serializable> implements IModel<E> {

	private static final long serialVersionUID = 1L;


	private IModel<T> model;
	private Class<E> clazz;
	private E valueWhenNull;
	private E valueWhenMissing;
	private boolean throwExceptionWhenMissing;



	public ValueEnumConvertModel(T value, Class<E> clazz) {
		this(Model.of(value), clazz, null, true, null);
	}

	public ValueEnumConvertModel(T value, Class<E> clazz, E valueWhenNull) {
		this(Model.of(value), clazz, valueWhenNull, true, null);
	}

	public ValueEnumConvertModel(T value, Class<E> clazz, boolean throwExceptionWhenMissing, E valueWhenMissing) {
		this(Model.of(value), clazz, null, throwExceptionWhenMissing, valueWhenMissing);
	}

	public ValueEnumConvertModel(T value, Class<E> clazz, E valueWhenNull, boolean throwExceptionWhenMissing, E valueWhenMissing) {
		this(Model.of(value), clazz, valueWhenNull, throwExceptionWhenMissing, valueWhenMissing);
	}

	public ValueEnumConvertModel(IModel<T> model, Class<E> clazz) {
		this(model, clazz, null, true, null);
	}

	public ValueEnumConvertModel(IModel<T> model, Class<E> clazz, E valueWhenNull) {
		this(model, clazz, valueWhenNull, true, null);
	}

	public ValueEnumConvertModel(IModel<T> model, Class<E> clazz, boolean throwExceptionWhenMissing, E valueWhenMissing) {
		this(model, clazz, null, throwExceptionWhenMissing, valueWhenMissing);
	}

	public ValueEnumConvertModel(IModel<T> model, Class<E> clazz, E valueWhenNull, boolean throwExceptionWhenMissing, E valueWhenMissing) {
		this.model = model;
		this.clazz = clazz;
		this.valueWhenNull = valueWhenNull;
		this.valueWhenMissing = valueWhenMissing;
		this.throwExceptionWhenMissing = throwExceptionWhenMissing;
	}

	public E getObject() {
		T value = model.getObject();
		if (value == null)
			return valueWhenNull;

		E ret = ValueEnum.get(clazz, value);
		if (ret != null)
			return ret;
		else if (!throwExceptionWhenMissing)
			return valueWhenMissing;
		throw new RuntimeException(
				String.format("ValueEnum is not found. class={%s}, value={%s}",
						clazz.getSimpleName(), value));
	}

	@SuppressWarnings("unchecked")
	public void setObject(E object) {
		if (object == null)
			model.setObject(null);
		else
			model.setObject(((IValueEnum<T>)object).getValue());
	}

	public void detach() {
	}
}
