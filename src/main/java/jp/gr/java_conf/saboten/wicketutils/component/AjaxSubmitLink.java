package jp.gr.java_conf.saboten.wicketutils.component;

import jp.gr.java_conf.saboten.wicketutils.listener.AjaxFailureOnPageExpiredListener;
import jp.gr.java_conf.saboten.wicketutils.util.ComponentUtil;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.html.form.Form;

public abstract class AjaxSubmitLink extends org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink {

	private static final long serialVersionUID = 1L;


	boolean moveToTop;

	public AjaxSubmitLink(String id) {
		this(id, null, true);
	}

	public AjaxSubmitLink(String id, boolean moveToTop) {
		this(id, null, moveToTop);
	}

	public AjaxSubmitLink(String id, final Form<?> form) {
		this(id, form, true);
	}

	public AjaxSubmitLink(String id, final Form<?> form, boolean moveToTop) {
		super(id, form);
		this.moveToTop = moveToTop;
	}

	@Override
	public void onError(final AjaxRequestTarget target, Form<?> form) {

		/*
		 * Page上のIFeedbackを全部更新対象に追加する
		 * ModalWindowで子WindowのFeedbackのみ更新したい場合などは、オーバーライドして
		 */
		ComponentUtil.addAllFeedbacksTo(getPage(), target);
	}


	@Override
	protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
		// TODO これだけだと、画面下方のSubmitボタン→エラーで画面上方にメッセージ表示、の場合に見えないオチあり
		attributes.getAjaxCallListeners().add(new AjaxFailureOnPageExpiredListener());
	}

//	@Override
//	protected IAjaxCallDecorator getAjaxCallDecorator() {
//		if (moveToTop)
//			return new JsScrollDecorator(
//				new AjaxOnFailureDecorator(super.getAjaxCallDecorator(), getPage().getClass()));
//		else
//			return new AjaxOnFailureDecorator(super.getAjaxCallDecorator(), getPage().getClass());
//	}
}
