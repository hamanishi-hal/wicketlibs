package jp.gr.java_conf.saboten.wicketutils.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.model.IModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Label拡張クラス.<br/>
 * Modelの値がNull、もしくは文字列変換（{@link #getDefaultModelObjectAsString()}）した結果が空文字になる場合に、
 * <ul>
 * <li>リソースファイルから文言を取得して代替表示する（あるいは何も表示しない）。
 * <li>コンポーネント自体を非表示にする。
 * </ul>
 * 等の挙動を制御する事が可能。
 */
public class Label extends org.apache.wicket.markup.html.basic.Label {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Label.class);

	/**
	 * RESOURCE_KEY_IF_EMPTY = "LabelIfEmpty"
	 */
	public static final String RESOURCE_KEY_IF_EMPTY = "LabelIfEmpty";
//	private static final String RESOURCE_KEY_PREFIX = "LabelPrefix";
//	private static final String RESOURCE_KEY_SUFFIX = "LabelSuffix";



	private boolean unVisibleIfEmpty = false;

	public Label(String id) {
		super(id);
	}

	public Label(String id, IModel<?> model) {
		super(id, model);
	}

	public Label(String id, String label) {
		super(id, label);
	}

	/**
	 * 表示すべき内容が無い場合に、コンポーネント自体を非表示にする。<br/>
	 */
	public Label setUnVisibleIfEmpty(boolean unVisibleIfEmpty) {
		this.unVisibleIfEmpty = unVisibleIfEmpty;
		return this;
	}

	@Override
	public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
		replaceComponentTagBody(markupStream, openTag, getOutputString());
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		if (unVisibleIfEmpty == true && getDefaultModelObject() == null)
			setVisibilityAllowed(false);
	}

	private String getOutputString() {
//		logger.debug("getPath() = " + getPath());
//		logger.debug("getPageRelativePath() = " + getPageRelativePath());
//		logger.debug("getClassRelativePath() = " + getClassRelativePath());

		String ret = getDefaultModelObjectAsString();
		if (ret == null || ret.length() == 0) {
			if (unVisibleIfEmpty)
				return "";
			else
				return getStringIfEmpty();
		} else {
			return getStringIfNotEmpty();
		}
	}

	/**
	 * 表示する文言を返却する。<br/>
	 * デフォルトは{@link #getDefaultModelObjectAsString()};の戻り値をそのまま返却。<br/>
	 * PrefixやSuffixを付加する等の用途に使用。値がNullの場合は呼ばれない。<br/>
	 *
	 */
	protected String getStringIfNotEmpty() {
		return getDefaultModelObjectAsString();
	}

	/**
	 * Modelの値がNull、もしくは文字列変換（{@link #getDefaultModelObjectAsString()}）した結果が空文字になる場合に、代替表示する文言を返却する。<br/>
	 * {@link #setUnVisibleIfEmpty(boolean) component.setUnVisibleIfEmpty(true);}されてると呼ばれない。<br/>
	 * デフォルトは、リソースから取得する。キーは{@link Label#RESOURCE_KEY_IF_EMPTY}。<br/>
	 */
	protected String getStringIfEmpty() {
		return getLocalizer().getString(RESOURCE_KEY_IF_EMPTY, this);
	}

}
