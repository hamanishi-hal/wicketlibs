package jp.gr.java_conf.saboten.wicketutils.behavier;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * Ajax系部品には使用できない<br/>
 * という想定だが試してない。
 * @see jp.gr.java_conf.saboten.wicketutils.listener.AjaxConfirmListener
 *
 */
public class JsConfirmBehavier extends AttributeAppender {

	private static final long serialVersionUID = 1L;

	public JsConfirmBehavier(final String message) {
		super("onclick", new AbstractReadOnlyModel<String>(){

			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return "if(!confirm('" + message + "')) return false;";
			}
		});
	}

}
