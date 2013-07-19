package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.gr.java_conf.saboten.common.SqlDateUtil;
import jp.gr.java_conf.saboten.wicketutils.model.MapModel;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeField extends FormComponentPanel<Date> {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(DateTimeField.class);


	public enum RequireType {DATE_HOUR_MIN, DATE_HOUR, DATE, NONE}

	private static List<Integer> hours;
	private static List<Integer> minutes;
	static {
		hours = new ArrayList<Integer>();
		for (int i = 0; i < 24; i++) {
			hours.add(i);
		}
		minutes = new ArrayList<Integer>();
		for (int i = 0; i < 60; i++) {
			minutes.add(i);
		}
	}

	private class Renderer implements IChoiceRenderer<Integer> {
		private static final long serialVersionUID = 1L;
		public Object getDisplayValue(Integer val) {
			return String.format("%02d%n", val);
		}
		public String getIdValue(Integer val, int index) {
			return String.valueOf(val);
		}
	};


	@SuppressWarnings("unused")
	private java.sql.Date date;
	@SuppressWarnings("unused")
	private Integer hour;
	@SuppressWarnings("unused")
	private Integer minute;

	private FormComponent<java.sql.Date> dateField;
	private DropDownChoice<Integer> hoursField;
	private DropDownChoice<Integer> minutesField;


	public DateTimeField(String id, IModel<Date> model) {
		this(id, model, true, Model.of(RequireType.DATE_HOUR_MIN));
	}

	public DateTimeField(String id, final IModel<Date> model, boolean dispTime, final IModel<RequireType> required) {
		this(id, model, true, required, hours);
	}

	public DateTimeField(String id, final IModel<Date> model, boolean dispTime, final IModel<RequireType> required, List<Integer> hours) {
		this(id, model, true, required, hours, minutes);
	}

	public DateTimeField(String id, final IModel<Date> model, boolean dispTime, final IModel<RequireType> required, List<Integer> hours, List<Integer> minutes) {

		super(id, model);
		setType(Date.class);

		// 日付
		add(dateField =
			new DateField("DateTimeField_date", new PropertyModel<java.sql.Date>(this, "date")){

				private static final long serialVersionUID = 1L;
				@Override
				public boolean isRequired() {
					return required.getObject() != RequireType.NONE;
				}
			}.setLabel(new AbstractReadOnlyModel<String>() {
				private static final long serialVersionUID = 1L;
				@Override
				public String getObject() {
					// 何もしないと、DateFieldの名前だけがラベルとして表示される。
					// 頭にこのDateTimeFieldの名前を追加したいのでこのようにする。
					return getLocalizer().getString(dateField.getId(), dateField,
							new MapModel().add("fieldname", getFieldLabel()));
				}
			}));


		// container
		WebMarkupContainer container = new WebMarkupContainer("DateTimeField_timeContainer");
		add(container);
		if (dispTime == false)
			container.setVisibilityAllowed(false);

		// 時
		container.add(hoursField =
			new DropDownChoice<Integer>("DateTimeField_hour",
					new PropertyModel<Integer>(this, "hour"), hours, new Renderer()) {

				private static final long serialVersionUID = 1L;
				@Override
				public boolean isRequired() {
					return required.getObject() == RequireType.DATE_HOUR_MIN
						|| required.getObject() == RequireType.DATE_HOUR;
				}
			});
		hoursField.setNullValid(true);
		hoursField.setLabel(new AbstractReadOnlyModel<String>() {
				private static final long serialVersionUID = 1L;
				@Override
				public String getObject() {
					return getLocalizer().getString(hoursField.getId(), hoursField,
							new MapModel().add("fieldname", getFieldLabel()));
				}
			});

		// 分
		container.add(minutesField =
			new DropDownChoice<Integer>("DateTimeField_minute",
					new PropertyModel<Integer>(this, "minute"), minutes, new Renderer()) {

				private static final long serialVersionUID = 1L;
				@Override
				public boolean isRequired() {
					return required.getObject() == RequireType.DATE_HOUR_MIN;
				}
			});
		minutesField.setLabel(new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = 1L;
			@Override
			public String getObject() {
				return getLocalizer().getString(minutesField.getId(), minutesField,
						new MapModel().add("fieldname", getFieldLabel()));
			}
		});
		minutesField.setNullValid(true);
	}


	@Override
	protected void onBeforeRender()	{
		Date datetime = getModelObject();
		if (datetime != null) {
			date = SqlDateUtil.getDate(datetime);
			Calendar c = Calendar.getInstance();
			c.setTime(datetime);
			hour = c.get(Calendar.HOUR_OF_DAY);
			minute = c.get(Calendar.MINUTE);
		} else {
			date = null;
			hour = null;
			minute = null;
		}

		super.onBeforeRender();
	}

	@Override
	protected void convertInput() {
		Date date = dateField.getConvertedInput();
//		Integer hour = hoursField.isVisible() ? hoursField.getConvertedInput() : 0;
		Integer hour = hoursField.getConvertedInput();
		if (!hoursField.isVisible()) hour = null;
		Integer minute = minutesField.getConvertedInput();
		if (!minutesField.isVisible()) minute = null;

		/*
		 * case
		 *  date, time(hour + minute)
		 *  date
		 *  nothing
		 */

		if (date == null && hour == null && minute == null) {
			setConvertedInput(null);
			return;
		}

		if (date != null && hour != null && minute != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.HOUR_OF_DAY, hour);
			c.set(Calendar.MINUTE, minute);
			c.set(Calendar.SECOND, 0);
			setConvertedInput(c.getTime());
			return;
		}

		if (date != null && hour != null && minute == null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.HOUR_OF_DAY, hour);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			setConvertedInput(c.getTime());
			return;
		}

		if (date != null && hour == null && minute == null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			setConvertedInput(c.getTime());
			return;
		}

		// 相関精査エラー：相関精査はここでこんなふうにやるので良いみたい
		logger.debug("id's name is " + getLocalizer().getString(getId(), this));
		String msg = getLocalizer().getString("missingInputError", this,
				new MapModel().add("fieldname", getLocalizer().getString(getId(), this)));
		error(msg);
		invalid();
	}

	private String getFieldLabel() {
		return getLocalizer().getString(getId(), this);
	}

	public DateTimeField addBehavier2DateField(final Behavior... behaviors) {
		dateField.add(behaviors);
		return this;
	}

	public DateTimeField addBehavier2HoursField(final Behavior... behaviors) {
		hoursField.add(behaviors);
		return this;
	}

	public DateTimeField addBehavier2MinutesField(final Behavior... behaviors) {
		minutesField.add(behaviors);
		return this;
	}

	public DateTimeField setVisible2HourMinute(boolean visible) {
		hoursField.setVisible(visible);
		minutesField.setVisible(visible);
		return this;
	}

	public DateTimeField setVisible2Minute(boolean visible) {
		minutesField.setVisible(visible);
		return this;
	}

}
