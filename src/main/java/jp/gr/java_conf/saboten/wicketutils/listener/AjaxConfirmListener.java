package jp.gr.java_conf.saboten.wicketutils.listener;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxCallListener;

/**
 *
 * usage
 * <pre>
 * Link link = new AjaxLink() {
 *     {@link @Override}
 *     protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
 *         attributes.getAjaxCallListeners().add(new AjaxConfirmListener("Are you OK?"));
 *     }
 * }
 * </pre>
 *
 */
public class AjaxConfirmListener extends AjaxCallListener {

	private static final long serialVersionUID = 1L;

//	/**
//	 * RESOURCE_KEY_CONFIRM = "AjaxConfirmMsg"
//	 */
//	public static final String RESOURCE_KEY_CONFIRM = "AjaxConfirmMsg";


	private String confirmMessage;

//	public AjaxConfirmListener() {
//	}

	public AjaxConfirmListener(String confirmMessage) {
		this.confirmMessage = confirmMessage;
	}

	@Override
	public CharSequence getPrecondition(Component component) {
//		return "if(!confirm('" + getConfirmMessage(component) + "')) return false;";
		return "if(!confirm('" + confirmMessage + "')) return false;";
	}

//	/**
//	 * 確認メッセージを返却する。<br/>
//	 * デフォルトは、コンストラクタ引数で指定があればそれを、無ければリソースから取得する。キーは{@link AjaxConfirmListener#RESOURCE_KEY_CONFIRM}。<br/>
//	 */
//	protected String getConfirmMessage(Component component) {
//
//		if (confirmMessage != null)
//			return confirmMessage;
//
//		return Application.get().getResourceSettings().getLocalizer()
//				.getString(RESOURCE_KEY_CONFIRM, component);
//	}
}
