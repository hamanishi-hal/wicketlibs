package jp.gr.java_conf.saboten.wicketutils.validator;

import org.apache.wicket.validation.validator.PatternValidator;

public abstract class AbstractStringValidator extends PatternValidator {

	private static final long serialVersionUID = 1L;


	/**
	 * 半角英数字
	 */
	public static class HalfAlphaDigitValidator extends AbstractStringValidator {
		private static final long serialVersionUID = 1L;
		public HalfAlphaDigitValidator() {
			super("[0-9a-zA-Z]+");
		}
	}

	/**
	 * 半角英数記号
	 */
	public static class HalfAlphaDigitSymbolValidator extends AbstractStringValidator {
		private static final long serialVersionUID = 1L;
		public HalfAlphaDigitSymbolValidator() {
			super("^[a-zA-Z0-9-/:-@\\[-\\`\\{-\\~]+");
		}
	}

	/**
	 * 半角英数記号（スペース含む）
	 */
	public static class HalfAlphaDigitSymbolSpaceValidator extends AbstractStringValidator {
		private static final long serialVersionUID = 1L;
		public HalfAlphaDigitSymbolSpaceValidator() {
			super("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+");
		}
	}

	/**
	 * 半角数字
	 */
	public static class HalfDigitValidator extends AbstractStringValidator {
		private static final long serialVersionUID = 1L;
		public HalfDigitValidator() {
			super("[0-9]+");
		}
	}

	/**
	 * 半角数字＆ハイフン
	 */
	public static class HalfDigitHyphenValidator extends AbstractStringValidator {
		private static final long serialVersionUID = 1L;
		public HalfDigitHyphenValidator() {
			super("[0-9-]+");
		}
	}

//	/**
//	 * 半角カタカナ以外の半角文字全て
//	 */
//	public static class SingleByteCharValidator extends AbstractStringValidator {
//		private static final long serialVersionUID = 1L;
//		public SingleByteCharValidator() {
//			super("[\\x00-\\x7F]+");
//		}
//	}

	/**
	 * 半角カタカナのみ
	 */
	public static class HalfKanaValidator extends AbstractStringValidator {
		private static final long serialVersionUID = 1L;
		public HalfKanaValidator() {
			super("^[｡-ﾟ+]+$");
		}
	}

	/**
	 * 半角英数記号（スペース含む）＆半角カタカナ
	 */
	public static class AllHalfCharValidator extends AbstractStringValidator {
		private static final long serialVersionUID = 1L;
		public AllHalfCharValidator() {
			super("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~｡-ﾟ+]+$");
		}
	}

	/**
	 * 全角カタカナ（＆長音（ー））のみ
	 */
	public static class FullKanaValidator extends AbstractStringValidator {
		private static final long serialVersionUID = 1L;
		public FullKanaValidator() {
			super("^[ァ-ヶー]*$");
		}
	}

	/**
	 * ひらがなのみ
	 */
	public static class FullHiraganaValidator extends AbstractStringValidator {
		private static final long serialVersionUID = 1L;
		public FullHiraganaValidator() {
			super("^[ぁ-んー]*$");
		}
	}

	/**
	 * 全角すべて
	 */
	public static class AllFullCharValidator extends AbstractStringValidator {
		private static final long serialVersionUID = 1L;
		public AllFullCharValidator() {
			super("^[^a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~｡-ﾟ+]+$");
		}
	}

	public static class TelNumberValidator extends AbstractStringValidator {
		private static final long serialVersionUID = 1L;
		public TelNumberValidator() {
			super("\\d{2,4}-\\d{2,4}-\\d{4}");
		}
	}

	public static class ZipValidator extends AbstractStringValidator {
		private static final long serialVersionUID = 1L;
		public ZipValidator() {
			super("\\d{3}-\\d{4}");
		}
	}

	public AbstractStringValidator(String pattern) {
		super(pattern);
	}

//	protected String resourceKey() {
//		return getClass().getSimpleName();
//	}
}
