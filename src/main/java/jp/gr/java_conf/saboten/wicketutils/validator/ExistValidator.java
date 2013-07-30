package jp.gr.java_conf.saboten.wicketutils.validator;

import org.apache.wicket.validation.IValidatable;

public abstract class ExistValidator extends AbstractValidator<String> {

	private static final long serialVersionUID = 1L;

	@Override
	public void validate(IValidatable<String> validatable) {
		String val = validatable.getValue();
		if (!exist(val)) {
			error(validatable);
		}
	}

	@Override
	protected String resourceKey() {
		return "ExistValidator";
	}

	protected abstract boolean exist(String val);
}
