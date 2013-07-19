package jp.gr.java_conf.saboten.wicketutils.event;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class AjaxUpdateEventPayload {

	private final AjaxRequestTarget target;

	public AjaxUpdateEventPayload(AjaxRequestTarget target) {
		this.target = target;
	}

	public AjaxRequestTarget getAjaxRequestTarget() {
		return target;
	}
}
