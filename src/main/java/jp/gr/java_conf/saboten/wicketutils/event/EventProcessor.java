package jp.gr.java_conf.saboten.wicketutils.event;

import org.apache.wicket.event.IEvent;

/**
 * onEvent()メソッド内でのイベントハンドリング用ユーティリティ<br/>
 * <p>
 * usage
 * <pre>
 * {@code @Override}
 * public void onEvent(IEvent<?> event) {
 *     new EventProcessor{@code <AjaxUpdateEventPayload>}(AjaxUpdateEventPayload.class) {
 *         {@code @Override}
 *         public void process(IEvent{@code <AjaxUpdateEventPayload>} event) {
 *             event.getPayload().getAjaxRequestTarget().add(Component.this);
 *         }
 *     }.exec(event);
 * }
 * </pre>
 *
 * @param <T> type of payload
 */
public abstract class EventProcessor<T> {

	private Class<T> t;

	public EventProcessor(Class<T> t) {
		this.t = t;
	}

	@SuppressWarnings("unchecked")
	public void exec(IEvent<?> event) {
		if (event.getPayload().getClass() == t) {
			IEvent<T> ev = (IEvent<T>) event;
			process(ev);
		}
	}

	public abstract void process(IEvent<T> event);
}
