package jp.gr.java_conf.saboten.wicketutils.model;

import org.apache.wicket.model.IModel;

public abstract class IntConvertModel<T> implements IModel<T> {

	private static final long serialVersionUID = 1L;

	private IModel<Integer> model;

	public IntConvertModel(IModel<Integer> model) {
		this.model = model;
	}

	public void detach() {
	}

	public T getObject() {
		if (model == null || model.getObject() == null)
			return null;
		return convertFromInt(model.getObject().intValue()); // Integerではなくintで渡してる
		// 呼び出し側で、合致比較に使用する想定。
		// うっかりInteger==Integerで比較するとNG。一方がintなら、Integerも自動的にアウトボクシングされるのでOKになる
	}

	public void setObject(T object) {
		if (object == null)
			model.setObject(null);
		else
			model.setObject(convertToInt(object));
	}

	protected abstract int convertToInt(T obj);
	protected abstract T convertFromInt(int id);
}
