package jp.gr.java_conf.saboten.wicketutils.validator;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.validation.INullAcceptingValidator;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;

public abstract class AbstractValidator<T> extends Behavior
implements INullAcceptingValidator<T> {

	private static final long serialVersionUID = 1L;

	protected String resourceKey() {
		return this.getClass().getSimpleName();
	}

	protected void error(IValidatable<T> validatable) {
		ValidationError error = new ValidationError().addKey(resourceKey());
		error = decorate(error, validatable);
		validatable.error(error);
	}

	protected ValidationError decorate(ValidationError error, IValidatable<T> validatable){
		return error;
	}
}
