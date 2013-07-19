package jp.gr.java_conf.saboten.wicketutils.component;

import jp.gr.java_conf.saboten.wicketutils.listener.AjaxFailureOnPageExpiredListener;

import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;



/**
 * Aタグ＋画像でボタンのように使うLink用の部品
 *
 */
public abstract class AjaxBtnLink extends org.apache.wicket.ajax.markup.html.AjaxLink<Void> {

	private static final long serialVersionUID = 1L;


	public AjaxBtnLink(String id) {
		super(id);
		setBeforeDisabledLink("<span class='disabled'>");
		setAfterDisabledLink("</span>");
	}

	@Override
	protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
		attributes.getAjaxCallListeners().add(new AjaxFailureOnPageExpiredListener());
	}
}
