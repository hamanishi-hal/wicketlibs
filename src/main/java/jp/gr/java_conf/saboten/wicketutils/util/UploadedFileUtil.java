package jp.gr.java_conf.saboten.wicketutils.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.wicket.markup.html.form.upload.FileUpload;

public class UploadedFileUtil {

	public static File moveTo(FileUpload uploadedFile, String dir) {
		return moveTo(uploadedFile, dir, null);
	}

	public static File moveTo(FileUpload uploadedFile, String dir, String filename) {
		return moveTo(uploadedFile, new File(dir), filename);
	}

	public static File moveTo(FileUpload uploadedFile, File dir) {
		return moveTo(uploadedFile, dir, null);
	}

	public static File moveTo(FileUpload uploadedFile, File dir, String filename) {

		// ファイル名。指定が無い場合、UPされたファイル名をそのまま使用
		if (filename == null)
			filename = FilenameUtils.getName(uploadedFile.getClientFileName());

		final File copyTo = new File(dir, filename);
		try {
			uploadedFile.writeTo(copyTo);
			return copyTo;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// FileUpload はオンメモリの可能性もある
	public static File moveToTemp(FileUpload uploadedFile) {
		try {
			return uploadedFile.writeToTempFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
