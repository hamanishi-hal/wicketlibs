package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.IModel;

public class BoolRadio extends RadioChoice<Boolean> {

	private static final long serialVersionUID = 1L;

	private static final String PAD = " ";

	private static final List<Boolean> TRUE_FALSE = new ArrayList<Boolean>();
	private static final List<Boolean> FALSE_TRUE = new ArrayList<Boolean>();
	static {
		TRUE_FALSE.add(true);
		TRUE_FALSE.add(false);
		FALSE_TRUE.add(false);
		FALSE_TRUE.add(true);
	}

	public BoolRadio(String id, IModel<Boolean> model,
			final String caseTrue, final String caseFalse) {
		this(id, model, caseTrue, caseFalse, true);
	}

	public BoolRadio(String id, IModel<Boolean> model,
			final String caseTrue, final String caseFalse, Boolean defaultValue) {
		this(id, model, caseTrue, caseFalse, true);
	}

	public BoolRadio(String id, final IModel<Boolean> model,
			final String caseTrue, final String caseFalse, boolean trueFirst) {
		this(id, model, caseTrue, caseFalse, trueFirst, false);

	}

	public BoolRadio(String id, final IModel<Boolean> model,
			final String caseTrue, final String caseFalse,
			boolean trueFirst, final boolean defaultValue) {

		super(
			id,
			new IModel<Boolean>(){
				private static final long serialVersionUID = 1L;
				public void detach() {
					model.detach();
				}
				public Boolean getObject() {
					Boolean ret = model.getObject();
					if (ret == null)
						ret = defaultValue;
					return ret;
				}
				public void setObject(Boolean object) {
					model.setObject(object);
				}
			},
			trueFirst ? TRUE_FALSE : FALSE_TRUE,
			new IChoiceRenderer<Boolean>() {

				private static final long serialVersionUID = 1L;

				public Object getDisplayValue(Boolean object) {
					if (object == true)
						return PAD + caseTrue;
					else
						return PAD + caseFalse;
				}

				public String getIdValue(Boolean object, int index) {
					return object.toString();
				}
			});

		// default is "<br />\n"
		setSuffix(" ");

		setNullValid(false);
	}
}
