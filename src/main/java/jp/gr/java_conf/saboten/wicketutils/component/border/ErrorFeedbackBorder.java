package jp.gr.java_conf.saboten.wicketutils.component.border;

import jp.gr.java_conf.saboten.wicketutils.message.ContainerErrorMessageFilter;

import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;

public class ErrorFeedbackBorder extends FormComponentFeedbackBorder {

	private static final long serialVersionUID = 1L;

	public ErrorFeedbackBorder(String id) {
		super(id);
	}

	protected IFeedbackMessageFilter getMessagesFilter() {

//		// ↓1.当該コンポーネントがメッセージを持ってさえいれば、エラーでなくともひっかかるフィルタ
//		final IFeedbackMessageFilter defaultFilter = super.getMessagesFilter();
//
//		// ↓2.エラーメッセージだけをひっかけるフィルタ
//		return new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR) {
//			private static final long serialVersionUID = 1L;
//			public boolean accept(FeedbackMessage message) {
//				// ↓1と2両方使って判定する
//				return super.accept(message) && defaultFilter.accept(message);
//			}
//		};
		return new ContainerErrorMessageFilter(this);
	}
}
