package jp.gr.java_conf.saboten.wicketutils.behavier;

import jp.gr.java_conf.saboten.wicketutils.listener.AjaxFailureOnPageExpiredListener;

import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.util.time.Duration;

public abstract class SleepableAjaxTimerBehavior extends AbstractAjaxTimerBehavior {

	private static final long serialVersionUID = 1L;


	public abstract static class IndicatingAjaxTimerBehavior extends SleepableAjaxTimerBehavior
	implements IAjaxIndicatorAware{

		private static final long serialVersionUID = 1L;

		public IndicatingAjaxTimerBehavior(Duration interval) {
			super(interval);
		}
	}




	private Duration reservedInterval;

	public SleepableAjaxTimerBehavior(Duration interval) {
		super(interval);
		reservedInterval = interval;
	}

	/**
	 * Durationを長期に再設定する事で擬似的にSleepさせる
	 * 但し、TimerスクリプトはHTMLのHeaderに書き込まれるので、Page全体を再描画させないと反映されない
	 * ※Ajaxの部分更新では、Headerが更新されないのでNG
	 */
	public void sleep() {
		// stopだと描画されなくなり、再スタートができないため
		super.setUpdateInterval(Duration.ONE_WEEK);
	}

	/**
	 * TimerスクリプトはHTMLのHeaderに書き込まれるので、Page全体を再描画させないと反映されない
	 * ※Ajaxの部分更新では、Headerが更新されないのでNG
	 */
	public void awake() {
		if (getUpdateInterval().equals(Duration.ONE_WEEK))
			super.setUpdateInterval(reservedInterval);
	}

	/**
	 * timer.sleep()で寝かせても、その時のPage全体を再描画で描画処理が一回走ってしまう（普通に組んでたら）。
	 * その一回が不要な場合、この戻り値で処理スキップするなどの用途に
	 */
	public boolean isSleeped() {
		return getUpdateInterval().equals(Duration.ONE_WEEK);
	}

	/**
	 * TimerスクリプトはHTMLのHeaderに書き込まれるので、Page全体を再描画させないと反映されない
	 * ※Ajaxの部分更新では、Headerが更新されないのでNG
	 */
	public void setInterval(Duration interval) {
		super.setUpdateInterval(interval);
	}

	/**
	 * TimerスクリプトはHTMLのHeaderに書き込まれるので、Page全体を再描画させないと反映されない
	 * ※Ajaxの部分更新では、Headerが更新されないのでNG
	 */
	public void setDefaultInterval() {
		super.setUpdateInterval(reservedInterval);
	}

	@Override
	protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
		attributes.getAjaxCallListeners().add(new AjaxFailureOnPageExpiredListener());
	}
//	@Override
//	protected IAjaxCallDecorator getAjaxCallDecorator() {
//		Class<? extends Page> pageClass = getComponent().getPage().getClass();
//		return new AjaxOnFailureDecorator(super.getAjaxCallDecorator(), pageClass);
//	}
}
