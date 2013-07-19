package jp.gr.java_conf.saboten.wicketutils.validator;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;


/**
 * TODO wicket6系で、下記に意図した通りに動くのか未検証！
 *
 * そもそもAbstractFormValidator自体が大した事はしていない。
 * 基本的には、onValidate() の中で、
 * 各コンポーネントからcomponent.getConvertedInput()で値を取り出してチェックし、
 * ダメならcomponent.error()する。
 *
 * これは、単項目Validatorの中から他のコンポーネント参照してチェックするのとやり方としては同じだが
 * そのコンポーネントが別のValidationで蹴られてる場合には、FormValidatorは呼ばれないので、
 * その点の考慮が不要になる点は、コードが簡潔になる。
 *
 * 逆に、関連コンポーネント全部の参照が必要なので、面倒と言えば面倒
 *
 * メッセージは、コンポーネント毎に一つ（＝なのでメッセージ一つで複数をマーキングなどはできない）
 * あるいは、コンポーネントに紐付かないメッセージを吐く事が可能
 *
 * error() もFormValidator.error()も変わり無いが、
 * valueMapにコンポーネントの分だけ{label1},{label2},....とセットされる
 *
 */
public abstract class FormValidator extends AbstractFormValidator {


	private static final long serialVersionUID = 1L;


	private final FormComponent<?>[] components;

	public FormValidator(FormComponent<?>...components) {
		this.components = components;
	}

	public FormComponent<?>[] getDependentFormComponents() {
		return components;
	}

	public void error(FormComponent<?> fc) {
		if (fc == null)
			throw new RuntimeException("unexpected!"); // かならずどれかのFormComponentが必要！
		error(fc, resourceKey(), variablesMap(fc));
	}

	public void error(FormComponent<?> fc, final String resourceKey) {
		if (fc == null)
			throw new RuntimeException("unexpected!"); //
		error(fc, resourceKey, variablesMap(fc));
	}


	/*
	 * UnVisibleな項目が
	 * DependentFormComponentsの中に一つでもあると、このメソッドは呼ばれないので注意！
	 */
	public final void validate(Form<?> form) {

		/*
		 * コンポーネントがDisableの場合、短項目Validationもスルーする。
		 * よって、それを前提にしたFormValidatorも実施させるとおかしなことになるため、ここでチェック
		 * 前提条件が成立しない場合はスルーする
		 */
		for (FormComponent<?> c : components) {
			// 順に親を辿りながらチェック
			MarkupContainer d = findDisableParent(c);
			if (d != null)
				return;
		}

		validateWhenComponentsEnabled(form);
	}

	private MarkupContainer findDisableParent(MarkupContainer c) {
		if (c == null)
			return null;
		if (!c.isEnabled())
			return c;
		return findDisableParent(c.getParent());
	}


	/*
	 * Enable以前に、UnVisibleな項目がDependentFormComponentsの中に一つでもあると、
	 * このメソッド（正確には上位のvalidateメソッド）が呼ばれないので注意！
	 *
	 * disabledなら呼ばれる。
	 * 従って、Disabledを回避しようとUnVisibleにするとかえって動作しない仕様。
	 * しかし実際のところ、UnVisibleにされると、まだModel更新されてないので、代替値を取得する事もかなわずお手上げ。
	 * なのでこの場合は、Component.setVisibleではなく、cssのスタイルで非表示にする事になる。
	 */
	protected abstract void validateWhenComponentsEnabled(Form<?> form);


	protected Map<String, Object> variablesMap(FormComponent<?> component) {

		// label1,label2,...
		// input1,input2,...
		// name1,name2,...
		Map<String, Object> map = super.variablesMap();

		// 条件付き必須の場合など、既存のメッセージを使いたいので、添字無しのも追加する
		String i = null;
		for (Entry<String, Object> e : map.entrySet()) {
			if (e.getKey().startsWith("name") && e.getValue().equals(component.getId())) {
				i = e.getKey().substring(4);
				break;
			}
		}
		if (i != null) {
			map.put("label", map.get("label" + i));
			map.put("input", map.get("input" + i));
			map.put("name", map.get("name" + i));
		}
		return map;
	}
}
