package jp.gr.java_conf.saboten.wicketutils.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.model.IModel;

/**
 * ラベルを動的に変更できるアンカー.<br/>
 * ※通常は、Link＆Labelの2コンポーネントを使って行う。
 * <p>
 * <ul>
 * <li>enabled時のラベルはモデルから取得（モデル省略時は、テンプレートの文言がそのまま表示される）
 * <li>disabled時のラベルもモデルから取得（但し戻り値がNullの場合はリソースから取得）
 * </ul>
 * <p>
 * 「画像付きでボタンのように見せるアンカー」には、このクラスではなく{@link BtnLink}を使用すること
 *
 */
public abstract class Link extends org.apache.wicket.markup.html.link.Link<Void> {

	private static final long serialVersionUID = 1L;

	public static final String RESOURCE_KEY_IF_DISABLED = "LinkLabelIfDisabled";


	private IModel<String> label;

	public Link(String id) {
		super(id);
	}

	public Link(String id, IModel<String> label) {
		super(id);
		this.label = label;
	}

	public Link setCaptionModel(IModel<String> label) {
		this.label = label;
		return this;
	}

	public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
		if (isEnabledInHierarchy())
			if (label != null)
				replaceComponentTagBody(markupStream, openTag, label.getObject());
			else
				super.onComponentTagBody(markupStream, openTag);
		else
			if (label != null && label.getObject() != null)
				replaceComponentTagBody(markupStream, openTag, label.getObject());
			else
				replaceComponentTagBody(markupStream, openTag, getDefaultDisableString());
	}

	protected String getDefaultDisableString() {
		return getLocalizer().getString(RESOURCE_KEY_IF_DISABLED, this);
	}
}
