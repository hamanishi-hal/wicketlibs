package jp.gr.java_conf.saboten.wicketutils.listener;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxCallListener;

public class AjaxFailureOnPageExpiredListener extends AjaxCallListener {

	private static final long serialVersionUID = 1L;

	@Override
	public CharSequence getFailureHandler(Component component) {
		// 戻り値に基づいて分岐すべきと思うが、どやって取ればよい？
		return "alert('Ajax access failure. Maybe page is expired.');";
	}
}
