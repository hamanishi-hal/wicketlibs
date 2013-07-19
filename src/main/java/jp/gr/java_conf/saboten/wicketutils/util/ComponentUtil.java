package jp.gr.java_conf.saboten.wicketutils.util;

import jp.gr.java_conf.saboten.wicketutils.component.border.ErrorFeedbackBorder;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComponentUtil {

	private static final Logger logger = LoggerFactory.getLogger(ComponentUtil.class);


	/**
	 * markupIdからコンポーネントを探す.<br/>
	 * @param page
	 * @param markupId
	 * @return
	 */
	public static Component findComponentByMarkupId(Page page, final String markupId) {

		logger.debug(markupId);

		return page.visitChildren(new IVisitor<Component, Component>() {
			@Override
			public void component(Component component, IVisit<Component> visit) {
				if (markupId.equals(component.getMarkupId())) {
					visit.stop(component);
				}
			}
		});
	}


	/**
	 * relativePathからコンポーネントを探す.<br/>
	 * @param page
	 * @param relativePath ex: form:xxcontainer:name
	 * @return
	 */
	public static Component findComponentByRelativePath(Page page, final String relativePath) {

		logger.debug(relativePath);

		return page.visitChildren(new IVisitor<Component, Component>() {
			@Override
			public void component(Component component, IVisit<Component> visit) {
				if (relativePath.equals(component.getPageRelativePath())) {
					visit.stop(component);
				}
			}
		});
	}


	/**
	 * ErrorFeedbackBorderをFormComponentにセットするためのユーティリティメソッド.<br/>
	 * 対象FormComponentをAddしたBorderをFormにAddする、という順番になる
	 * <p>
	 * Usage:
	 * <pre>
	 * form.add(ComponentUtil.appendErrBorder(new TextField("id", model));
	 * </pre>
	 */
	public static ErrorFeedbackBorder appendErrBorder(Component child) {
		String borderId = child.getId() + "Border";
		ErrorFeedbackBorder border = new ErrorFeedbackBorder(borderId);
		border.add(child);
		return border;
	}


	/**
	 * 子コンポーネントの全てをAjaxRequestTargetに追加する.<br/>
	 * @param container
	 * @param target
	 */
	public static void addAllFeedbacksTo(MarkupContainer container, final AjaxRequestTarget target) {
		container.visitChildren(IFeedback.class, new IVisitor<Component, Void>() {
			@Override
			public void component(Component component, IVisit<Void> visit) {
				target.add(component);
			}
		});
	}
}
