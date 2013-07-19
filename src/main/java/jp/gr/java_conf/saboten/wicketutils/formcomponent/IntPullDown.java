package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

public class IntPullDown extends DropDownChoice<Integer> {

	private static final long serialVersionUID = 1L;

	public static class IntChoiceRenderer implements IChoiceRenderer<Integer> {
		private static final long serialVersionUID = 1L;
		private String suffix;
		public IntChoiceRenderer(String suffix) {
			this.suffix = suffix;
		}
		public Object getDisplayValue(Integer val) {
			return val + suffix;
		}
		public String getIdValue(Integer val, int index) {
			return val.toString();
		}
	}

	public IntPullDown(String id, IModel<Integer> targetModel, List<Integer> elements) {
		this(id, targetModel, elements, "");
	}

	public IntPullDown(String id, IModel<Integer> targetModel, List<Integer> elements, final String suffix) {
		this(id, targetModel, elements, new IntChoiceRenderer(suffix));
	}

	public IntPullDown(String id, IModel<Integer> targetModel, List<Integer> elements, IChoiceRenderer<Integer> renderer) {
		super(id, targetModel, elements, renderer);
	}
}
