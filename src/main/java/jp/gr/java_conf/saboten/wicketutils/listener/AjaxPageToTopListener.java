package jp.gr.java_conf.saboten.wicketutils.listener;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxCallListener;


/**
 * ページトップへスクロール（というか一瞬で移動）させる<br/>
 *
 */
public class AjaxPageToTopListener extends AjaxCallListener {

	private static final long serialVersionUID = 1L;

//	@Override
//    public CharSequence preDecorateScript(CharSequence script) {
//        return "window.scroll(0,0);" + script;
//    }

	@Override
	public CharSequence getPrecondition(Component component) {
		return "window.scroll(0,0);";
	}
}
