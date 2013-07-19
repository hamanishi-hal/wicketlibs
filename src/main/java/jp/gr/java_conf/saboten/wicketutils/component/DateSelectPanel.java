package jp.gr.java_conf.saboten.wicketutils.component;

import java.util.Date;

import jp.gr.java_conf.saboten.wicketutils.behavier.AjaxTextFieldInputUpdatingBehavior;
import jp.gr.java_conf.saboten.wicketutils.formcomponent.DateTimeField;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


/**
 * サンプル実装
 * 左にチェックボックスがあって、チェックすると右側にある日付＆時刻選択フォームがEnableになる、という代物
 * IEで、チェックOnOffを繰り返すと、日時フォームが右に動いてゆくという怪現象があったため、
 * それに対処するためにTableタグで構成されている。
 *
 */
public class DateSelectPanel extends Panel {

	private static final long serialVersionUID = 1L;


	public DateSelectPanel(String id,
			final IModel<Boolean> checkBoxModel,
			IModel<Date> dateLabelModel) {

		super(id);

		//
		final DateTimeField dateField;
		add(dateField = new DateTimeField("dateselectpanel_dateField", dateLabelModel));
		dateField
			.addBehavier2DateField(new AjaxTextFieldInputUpdatingBehavior())
			.addBehavier2HoursField(new AjaxTextFieldInputUpdatingBehavior())
			.addBehavier2MinutesField(new AjaxTextFieldInputUpdatingBehavior())
			.setEnabled(checkBoxModel.getObject());

		//
		add(new AjaxCheckBox("dateselectpanel_check", checkBoxModel) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				dateField.setEnabled(checkBoxModel.getObject());
				target.add(dateField);
			}
		});
	}
}
