package jp.gr.java_conf.saboten.wicketutils.formcomponent;

import jp.gr.java_conf.saboten.wicketutils.converter.SqlDateConverter;

import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.AbstractTextComponent.ITextFormatProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;


/**
 * DateFieldの拡張クラス.<br/>
 * 拡張機能として
 * <ul>
 * <li>defaultで{@link DatePicker}を表示
 * <li>専用Converterとして{@link jp.gr.java_conf.saboten.wicketutils.converter.SqlDateConverter SqlDateConverter}の使用
 * <li>DatePicker、Converterとで一貫したFormatを、リソースから取得(キー="DateFieldFormat")
 */
public class DateField extends TextField<java.sql.Date> implements ITextFormatProvider {

	private static final long serialVersionUID = 1L;

	public static final String RESOURCE_KEY_DATE_FORMAT = "DateFieldFormat";


	public DateField(String id, IModel<java.sql.Date> model) {
		this(id, model, true);
	}

	public DateField(String id, IModel<java.sql.Date> model, boolean dispPicker) {

		super(id, model);

		setMaxLen(10);

		// ↓この型は直接は関係無いが、なにかしら指定しないとgetCOnverter()自体が呼ばれない場合がある
		//  PropertyModelを使ってる場合は、自動的にSetされる
		setType(java.sql.Date.class);
		add(new DatePicker());
	}


	/**
	 * {@link org.apache.wicket.extensions.yui.calendar.DatePicker DatePicker}から呼ばれる。
	 * 同じフォーマットを返すようにする
	 */
	public final String getTextFormat() {
		return getDateFormat();
	}

	protected String getDateFormat() {
		return getLocalizer().getString(RESOURCE_KEY_DATE_FORMAT, this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <C> IConverter<C> getConverter(Class<C> type) {
		return (IConverter<C>) new SqlDateConverter(getDateFormat());
	}
}
