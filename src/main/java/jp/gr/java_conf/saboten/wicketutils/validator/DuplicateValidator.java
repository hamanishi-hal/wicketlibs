package jp.gr.java_conf.saboten.wicketutils.validator;

import org.apache.wicket.validation.IValidatable;

public abstract class DuplicateValidator extends AbstractValidator<String> {

	private static final long serialVersionUID = 1L;

	@Override
	public void validate(IValidatable<String> validatable) {
		String val = validatable.getValue();
		if (isDuplicated(val)) {
			error(validatable);
		}
	}

	@Override
	protected String resourceKey() {
		return "DuplicateValidator";
	}

	protected abstract boolean isDuplicated(String val);
}
