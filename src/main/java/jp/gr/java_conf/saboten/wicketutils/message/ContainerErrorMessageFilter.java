package jp.gr.java_conf.saboten.wicketutils.message;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.util.lang.Objects;

public class ContainerErrorMessageFilter implements IFeedbackMessageFilter {

	private static final long serialVersionUID = 1L;

	private static final int ERROR_LEVEL = FeedbackMessage.ERROR;


	private final MarkupContainer container;

	public ContainerErrorMessageFilter(MarkupContainer component) {
		this.container = component;
	}

	@Override
	public boolean accept(FeedbackMessage message) {
		final Component reporter = message.getReporter();
		return (message.isLevel(ERROR_LEVEL)
			&& (container.contains(reporter, true) || Objects.equal(container, reporter)));
	}

}
