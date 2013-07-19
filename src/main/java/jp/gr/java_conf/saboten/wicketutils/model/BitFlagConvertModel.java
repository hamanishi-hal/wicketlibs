package jp.gr.java_conf.saboten.wicketutils.model;

import org.apache.wicket.model.IModel;

public class BitFlagConvertModel implements IModel<Boolean> {

	private static final long serialVersionUID = 1L;

	private IModel<Integer> model;
	private int mask;

	public BitFlagConvertModel(IModel<Integer> model, int mask) {
		this.model = model;
		this.mask = mask;
	}

	public void detach() {
	}

	public Boolean getObject() {
		if (model == null || model.getObject() == null)
			return null;
		return (model.getObject() & mask) == mask;
	}

	public void setObject(Boolean object) {
		if (object == null)
			object = false;
		Integer val = model.getObject();
		if (object == true)
			val = val | mask;
		else
			val = val ^ mask;

		model.setObject(val);
	}
}
