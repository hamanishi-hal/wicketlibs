package jp.gr.java_conf.saboten.wicketutils.behavier;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;

public class AjaxCheckboxInputUpdatingBehavior extends OnChangeAjaxBehavior {

	private static final long serialVersionUID = 1L;

	@Override
	protected void onUpdate(AjaxRequestTarget target) {
		// NOP？validateエラー時のメッセージの始末をしたいところ
	}

	protected boolean getUpdateModel() {
		// validateはされるがModelは更新されない
		return false;
	}
}
