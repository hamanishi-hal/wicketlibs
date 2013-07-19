package jp.gr.java_conf.saboten.wicketutils.component;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * 日付を表示するLabelの親クラス.<br/>
 *
 * @see jp.gr.java_conf.saboten.wicketutils.component.DateLabel.SqlDateLabel
 * @see jp.gr.java_conf.saboten.wicketutils.component.DateLabel.SqlTimeLabel
 * @see jp.gr.java_conf.saboten.wicketutils.component.DateLabel.DateTimeLabel
 * @see jp.gr.java_conf.saboten.wicketutils.component.Label
 */
public abstract class DateLabel extends Label {

	private static final long serialVersionUID = 1L;

	public static class SqlDateLabel extends DateLabel {

		private static final long serialVersionUID = 1L;
		private static final String RESOURCE_KEY_IF_EMPTY = "DateLabelIfEmpty";
		private static final String RESOURCE_KEY_FORMAT = "DateLabelFormat";

		public SqlDateLabel(String id, IModel<? extends java.sql.Date> date) {
			super(id, date);
		}

		public SqlDateLabel(String id, java.sql.Date date) {
			super(id, date);
		}

		protected String getStringIfEmpty() {
			return getLocalizer().getString(RESOURCE_KEY_IF_EMPTY, this);
		}

		protected String getFormat() {
			return getLocalizer().getString(RESOURCE_KEY_FORMAT, this);
		}
	}

	public static class SqlTimeLabel extends DateLabel {

		private static final long serialVersionUID = 1L;
		private static final String RESOURCE_KEY_IF_EMPTY = "TimeLabelIfEmpty";
		private static final String RESOURCE_KEY_FORMAT = "TimeLabelFormat";

		public SqlTimeLabel(String id, IModel<? extends java.sql.Time> date) {
			super(id, date);
		}

		public SqlTimeLabel(String id, java.sql.Time date) {
			super(id, date);
		}

		protected String getStringIfEmpty() {
			return getLocalizer().getString(RESOURCE_KEY_IF_EMPTY, this);
		}

		protected String getFormat() {
			return getLocalizer().getString(RESOURCE_KEY_FORMAT, this);
		}
	}

	public static class DateTimeLabel extends DateLabel {

		private static final long serialVersionUID = 1L;
		private static final String RESOURCE_KEY_IF_EMPTY = "DateTimeLabelIfEmpty";
		private static final String RESOURCE_KEY_FORMAT = "DateTimeLabelFormat";

		public DateTimeLabel(String id, IModel<? extends java.sql.Time> date) {
			super(id, date);
		}

		public DateTimeLabel(String id, java.sql.Time date) {
			super(id, date);
		}

		protected String getStringIfEmpty() {
			return getLocalizer().getString(RESOURCE_KEY_IF_EMPTY, this);
		}

		protected String getFormat() {
			return getLocalizer().getString(RESOURCE_KEY_FORMAT, this);
		}
	}


	public DateLabel(String id, final IModel<? extends Date> date) {
		super(id, date);
	}

	public DateLabel(String id, final Date date) {
		super(id, Model.of(date));
	}

	protected String getStringIfNotEmpty() {
		Date date = (Date) getDefaultModelObject();
		return new SimpleDateFormat(getFormat()).format(date);
	}

	/**
	 * Modelの値がNull、もしくは文字列変換（{@link #getDefaultModelObjectAsString()}）した結果が空文字になる場合に、代替表示する文言を返却する。<br/>
	 * {@link #setUnVisibleIfEmpty(boolean) component.setUnVisibleIfEmpty(true);}されてると呼ばれない。<br/>
	 * デフォルトは、リソースから取得する。キーは{@link DateLabel#RESOURCE_KEY_IF_EMPTY}。<br/>
	 * @see jp.gr.java_conf.saboten.wicketutils.component.Label#getStringIfEmpty()
	 */
	protected abstract String getStringIfEmpty();

	/**
	 * 日付フォーマットを返却する。<br/>
	 * デフォルトは、リソースから取得する。キーは{@link DateLabel#RESOURCE_KEY_FORMAT}。<br/>
	 */
	protected abstract String getFormat();
}
