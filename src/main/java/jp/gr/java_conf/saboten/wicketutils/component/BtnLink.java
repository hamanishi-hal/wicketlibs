package jp.gr.java_conf.saboten.wicketutils.component;

import org.apache.wicket.model.IModel;

/**
 * 画像付きでボタンのように見せるアンカー.<br/>
 * Disabled時にはspanタグが追加されるので、CSS側でそのクラスに対する修飾を行う前提
 * <p>
 * 一般的な「文字列を使ったアンカー」には、このクラスではなく{@link Link}を使用すること
 *
 */
public abstract class BtnLink extends org.apache.wicket.markup.html.link.Link<Void> {

	private static final long serialVersionUID = 1L;

	public BtnLink(String id) {
		super(id);
		init();
	}

	public BtnLink(String id, IModel<Void> model) {
		super(id, model);
		init();
	}

	private void init() {
		setBeforeDisabledLink("<span class='disabled'>");
		setAfterDisabledLink("</span>");
	}

}
