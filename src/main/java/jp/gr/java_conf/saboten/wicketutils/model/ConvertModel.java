package jp.gr.java_conf.saboten.wicketutils.model;

import org.apache.wicket.model.IModel;

public abstract class ConvertModel<T,D> implements IModel<T> {

	private static final long serialVersionUID = 1L;

	private IModel<D> model;

	public ConvertModel(IModel<D> model) {
		if (model == null)
			throw new RuntimeException();
		this.model = model;
	}

	public void detach() {
		model.detach();
	}

	public T getObject() {
		if (model.getObject() == null)
			return null;
		return convertFrom(model.getObject());
	}

	public void setObject(T object) {
		if (object == null)
			model.setObject(null);
		else
			model.setObject(convertTo(object));
	}

	protected abstract D convertTo(T obj);
	protected abstract T convertFrom(D obj);
}
