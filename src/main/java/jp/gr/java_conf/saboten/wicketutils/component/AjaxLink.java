package jp.gr.java_conf.saboten.wicketutils.component;

import jp.gr.java_conf.saboten.wicketutils.listener.AjaxFailureOnPageExpiredListener;

import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;

/**
 * Disableの場合に、なにもラベル出力しないアンカー
 * jp.co.vega_net.wicketutils.component.LinkのAjax版
 *
 *　画像付きのボタン風アンカーには、これじゃなくて
 * jp.co.vega_net.wicketutils.component.ajax.AjaxBtnLink
 * を使用すること
 */
public abstract class AjaxLink extends org.apache.wicket.ajax.markup.html.AjaxLink<Void> {

	private static final long serialVersionUID = 1L;

	private static final String EMPTY_OUTPUT = "&nbsp;";


	public AjaxLink(String id) {
		super(id);
	}


	public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
		if (isEnabledInHierarchy())
			super.onComponentTagBody(markupStream, openTag);
		else
			replaceComponentTagBody(markupStream, openTag, getEmptyString());
	}

	protected String getEmptyString() {
		return EMPTY_OUTPUT;
	}

	@Override
	protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
		attributes.getAjaxCallListeners().add(new AjaxFailureOnPageExpiredListener());
	}

//	@Override
//	protected IAjaxCallDecorator getAjaxCallDecorator() {
//		return new AjaxOnFailureDecorator(super.getAjaxCallDecorator(), getPage().getClass());
//	}
}
