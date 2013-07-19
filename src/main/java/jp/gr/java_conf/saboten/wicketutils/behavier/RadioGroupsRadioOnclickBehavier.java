package jp.gr.java_conf.saboten.wicketutils.behavier;

import org.apache.wicket.ajax.AjaxEventBehavior;


/**
 * RadioGroup はRadioChoiceと違ってinputタグを出力しないので、AjaxでのUpdate通知のセットの
 * 仕方が異なる
 * <pre>
 * form.add(new RadioGroup<FreqType>("freq", modelForSelect)
		.add(new Radio<FreqType>("radio1", Model.of(FreqType.START_END))
				.add(new RadioGroupsRadioOnclickBehavier() {
						private static final long serialVersionUID = 1L;
						@Override
						protected void onEvent(AjaxRequestTarget target) {
							target.addComponent(componentToUpdate);
						}
					}))
 *
 *
 * </pre>
 *
 * @author Hamanishi
 *
 */
public abstract class RadioGroupsRadioOnclickBehavier extends AjaxEventBehavior {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public RadioGroupsRadioOnclickBehavier() {
		super("onclick");
	}
}
