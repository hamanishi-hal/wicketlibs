package jp.gr.java_conf.saboten.wicketutils.validator;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;

public class UploadFileExtValidator extends AbstractValidator<FileUpload> {

	private static final long serialVersionUID = 1L;

	private String[] allowExts;

	/**
	 *
	 * @param allowExts ex: "csv", "pdf", ..
	 */
	public UploadFileExtValidator(String...allowExts) {

		if (allowExts.length == 0)
			throw new RuntimeException("許可する拡張子は最低一つ設定して下さい");

		this.allowExts = allowExts;
		for (String ext : allowExts) {
			ext = ext.toLowerCase();
		}
	}

	@Override
	public void validate(IValidatable<FileUpload> validatable) {

		FileUpload fileUpload = validatable.getValue();
		String filename = fileUpload.getClientFileName().toLowerCase();

		for (String ext : allowExts) {
			if (filename.endsWith(ext))
				return; // OK
		}

		// validation error
		error(validatable);
//		validatable.error(new ValidationError()
//								.addKey(RESOURCE_KEY)
//								.setVariable("exts", extsStr())); // input, name, label は自動で追加される
	}

	@Override
	protected String resourceKey() {
		return "UploadFileExtValidator";
	}

	@Override
	protected ValidationError decorate(ValidationError error, IValidatable<FileUpload> validatable){
		return error.setVariable("exts", extsStr());
	}

	private String extsStr() {
		StringBuilder sb = new StringBuilder();
		for (String ext : allowExts) {
			sb.append("'").append(ext).append("',");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
