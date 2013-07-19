package jp.gr.java_conf.saboten.wicketutils.behavier;

import jp.gr.java_conf.saboten.wicketutils.message.ComponentErrorMessageFilter;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class ErrorStyleClassAppender extends AttributeAppender {

	private static final long serialVersionUID = 1L;


	public ErrorStyleClassAppender(final Component related, final String errClass) {
		super(
			"class",
			new AbstractReadOnlyModel<String>() {

				private static final long serialVersionUID = 1L;

				public String getObject() {
//            		if(Session.get().getFeedbackMessages().hasErrorMessageFor(related)){
            		if(Session.get().getFeedbackMessages().hasMessage(new ComponentErrorMessageFilter(related))){
            			return errClass;
            		}
            		return null;
            	};
        	}
		);
	}

	public static Component append(final Component related, final String errClass) {
		related.add(new ErrorStyleClassAppender(related, errClass));
		return related;
	}

}
