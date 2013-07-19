package jp.gr.java_conf.saboten.wicketutils.message;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.util.lang.Objects;

public class ComponentErrorMessageFilter implements IFeedbackMessageFilter {

	private static final long serialVersionUID = 1L;

	private static final int ERROR_LEVEL = FeedbackMessage.ERROR;


	private final Component component;

	public ComponentErrorMessageFilter(Component component) {
		this.component = component;
	}

	@Override
	public boolean accept(FeedbackMessage message) {
		return (message.isLevel(ERROR_LEVEL) && Objects.equal(component, message.getReporter()));
	}

}
