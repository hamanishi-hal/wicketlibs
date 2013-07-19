package jp.gr.java_conf.saboten.wicketutils.behavier;

import jp.gr.java_conf.saboten.wicketutils.util.JsUtil;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;


/**
 *
 * 画面表示後（OnDomReady）、指定されたコンポーネント（ボタン等）をクリックするJavascriptを起動させる。<br/>
 *
 * これにより、画面表示の後に別のAjax処理を実行させる＝遅延実行を実現する
 *
 */
public class LazyClickScript extends Behavior {

	private static final long serialVersionUID = 1L;

	private Component target;

	public LazyClickScript(final Component target) {
		this.target = target;
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		String script = "$('" + JsUtil.getIDs(target) + "').click();";
		response.render(OnDomReadyHeaderItem.forScript(script));
	}
}
