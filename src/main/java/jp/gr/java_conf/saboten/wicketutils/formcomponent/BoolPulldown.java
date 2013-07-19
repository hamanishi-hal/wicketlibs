package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

public class BoolPulldown extends DropDownChoice<Boolean> {

	private static final long serialVersionUID = 1L;

	private static final List<Boolean> TRUE_FALSE = new ArrayList<Boolean>();
	private static final List<Boolean> FALSE_TRUE = new ArrayList<Boolean>();
	static {
		TRUE_FALSE.add(true);
		TRUE_FALSE.add(false);
		FALSE_TRUE.add(false);
		FALSE_TRUE.add(true);
	}

	public BoolPulldown(String id, IModel<Boolean> model,
			final String caseTrue, final String caseFalse) {
		this(id, model, caseTrue, caseFalse, true);
	}

	public BoolPulldown(String id, IModel<Boolean> model,
			final String caseTrue, final String caseFalse, boolean trueFirst) {

		super(
			id,
			model,
			trueFirst ? TRUE_FALSE : FALSE_TRUE,
			new IChoiceRenderer<Boolean>() {

				private static final long serialVersionUID = 1L;

				public Object getDisplayValue(Boolean object) {
					if (object == true)
						return caseTrue;
					else
						return caseFalse;
				}

				public String getIdValue(Boolean object, int index) {
					return object.toString();
				}
			});
	}
}
