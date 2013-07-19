package jp.gr.java_conf.saboten.wicketutils.behavier;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class AjaxTextFieldInputUpdatingBehavior extends org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior {

	private static final long serialVersionUID = 1L;

	public AjaxTextFieldInputUpdatingBehavior() {
		super("onblur");
	}

	@Override
	protected void onUpdate(AjaxRequestTarget target) {
		// NOP？validateエラー時のメッセージの始末をしたいところ
	}

	protected boolean getUpdateModel() {
		// validateはされるがModelは更新されない
		return false;
	}
}
