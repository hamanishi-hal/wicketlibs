package jp.gr.java_conf.saboten.wicketutils.component;

import jp.gr.java_conf.saboten.wicketutils.util.ComponentUtil;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Session;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.Panel;

public class ErrorFeedbackIndicator extends Panel implements IFeedback {

	private static final long serialVersionUID = 1L;


	public static ErrorFeedbackIndicator append(String targetId) {
		return new ErrorFeedbackIndicator(targetId + "Indicator", targetId);
	}



	private String targetId;
	private MarkupContainer target;

	public ErrorFeedbackIndicator(String id, String targetId) {
		super(id);
		this.targetId = targetId;
	}

	public ErrorFeedbackIndicator(String id, MarkupContainer target) {
		super(id);
		this.target = target;
	}

	@Override
	public boolean isVisible() {
		return Session.get().getFeedbackMessages().messages(getMessagesFilter()).size() != 0;
	}

	protected IFeedbackMessageFilter getMessagesFilter() {

		if (target == null) {
			// エラー有無をチェックする相手（Nullならエラー）
			String targetRelativePath = getTargetRelativePath(targetId);
			Component c = ComponentUtil.findComponentByRelativePath(getPage(), targetRelativePath);
			target = (MarkupContainer)c;
		}

		// ↓1.当該コンポーネントがメッセージを持ってさえいれば、エラーでなくともひっかかるフィルタ
		final IFeedbackMessageFilter targetComponentFilter = new ContainerFeedbackMessageFilter(target);

		// ↓2.エラーメッセージだけをひっかけるフィルタ
		return new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR) {
			private static final long serialVersionUID = 1L;
			public boolean accept(FeedbackMessage message) {
				// ↓1と2両方使って判定する
				return super.accept(message) && targetComponentFilter.accept(message);
			}
		};
	}

	protected String getTargetRelativePath(String targetId) {
//		return StringUtils.replace(this.getPageRelativePath(), ":" + this.getId(), ":" + targetId);
		return this.getPageRelativePath().replaceAll(":" + this.getId(), ":" + targetId);
	}
}
