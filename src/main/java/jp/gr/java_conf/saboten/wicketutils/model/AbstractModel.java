package jp.gr.java_conf.saboten.wicketutils.model;

import org.apache.wicket.model.IModel;

public abstract class AbstractModel<T> implements IModel<T> {

	private static final long serialVersionUID = 1L;


	public void detach() {
	}

	public abstract T getObject();

	public abstract void setObject(T object);

}
