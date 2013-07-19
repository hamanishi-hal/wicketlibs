package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.ConversionException;

/**
 * TextFieldの拡張クラス.<br/>
 * 拡張機能として
 * <ul>
 * <li>全角スペースのトリム
 * <li>maxlength制御：生成されるHTMLタグにmaxlength属性を付加
 * <li>表示制御：生成されるHTMLタグにstyle属性(display:none;)を付加
 * <li>not enabled時に、HTMLタグにclass属性を出力(default="uneditable-input")
 */
public class TextField<T> extends org.apache.wicket.markup.html.form.TextField<T> {

	private static final long serialVersionUID = 1L;


	private IModel<Integer> maxlen;
	private boolean convertFullLengthSpaceToNull = true;
	private IModel<Boolean> displayNone;

	public TextField(String id, IModel<T> model) {
		super(id, model);
	}

	public TextField(String id, IModel<T> model, Class<T> type) {
		super(id, model, type);
	}

	public TextField<T> setMaxLen(int maxlen) {
		this.maxlen = Model.of(maxlen);
		return this;
	}

	public TextField<T> setMaxLen(IModel<Integer> maxlen) {
		this.maxlen = maxlen;
		return this;
	}

	public TextField<T> setConvertFullLengthSpaceToNull(boolean convertFullLengthSpaceToNull) {
		this.convertFullLengthSpaceToNull = convertFullLengthSpaceToNull;
		return this;
	}

	public TextField<T> setDisplayNone(IModel<Boolean> model) {
		this.displayNone = model;
		return this;
	}


	protected String trimSpace(String s) {
		int len = s.length();
        int st = 0;
        char[] val = s.toCharArray();

        while (st < len && (val[st] <= ' ' || val[st] == '　')) {
            st++;
        }
        while (st < len && (val[len - 1] <= ' ' || val[len - 1] == '　')) {
            len--;
        }
        if(st > 0 || len < s.length()) {
            return s.substring(st, len);
        }
        return s;
	}

	protected final String trimFull(String string) {
		String trimmed = string;
		if (trimmed != null && shouldTrimInput())
		{
			trimmed = trimSpace(trimmed);
		}
		return trimmed;
	}

	/*
	 * @see org.apache.wicket.markup.html.form.AbstractTextComponent#convertValue(String[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected T convertValue(String[] value) throws ConversionException	{
		//return (T)(value != null && value.length > 0 && value[0] != null ? trim(value[0]) : null);
		return (T)(value != null && value.length > 0 && value[0] != null ? trimFull(value[0]) : null);
	}

	/*
	 * @see org.apache.wicket.markup.html.form.FormComponent#getInput()
	 */
	public String getInput() {
		String[] input = getInputAsArray();
		if (input == null || input.length == 0) {
			return null;
		} else {
//			return trim(input[0]);
			return trimFull(input[0]);
		}
	}

	@Override
	protected void convertInput() {

		String[] value = getInputAsArray();
		if (value != null && value.length > 0) {
			if (convertFullLengthSpaceToNull && "　".equals(value[0])) {
				setConvertedInput(null);
				return;
			}
		}

		super.convertInput();
	}

	@Override
	protected void onComponentTag(final ComponentTag tag) {

		super.onComponentTag(tag);

		if (!isEnabledInHierarchy())
			tag.append("class", getDisabledClassName(), " ");

		if (displayNone != null
		&& displayNone.getObject() != null
		&& displayNone.getObject() == true)
			tag.append("style", "display:none;", "");

		if (maxlen != null && maxlen.getObject() != null)
			tag.put("maxlength", maxlen.getObject());
	}

	protected String getDisabledClassName() {
		return "uneditable-input";
	}
}
