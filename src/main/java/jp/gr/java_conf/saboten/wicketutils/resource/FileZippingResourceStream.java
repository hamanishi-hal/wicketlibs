package jp.gr.java_conf.saboten.wicketutils.resource;


import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.resource.ZipResourceStream;

public class FileZippingResourceStream extends ZipResourceStream {

	private static final long serialVersionUID = 1L;

	/**
	 * 渡されたファイルをアーカイブしたResourceStream
	 * 内部で使用されるのはJava標準Zip圧縮なので、日本語ファイル名は文字化けする等あり。
	 * 渡されたファイルを、同じく渡された作業用Dirにコピーし、そのDirを圧縮する、という流れなので
	 * 作業用Dirは消されてもよいパス（っていうかゴミがあると嫌なので消します）を指定する事。
	 *
	 * <pre>
	 * 	try {
	 * 		File workDir = new File(path, "work"); // 作業用Dir
	 * 		IResourceStream stream = FileZippingResourceStream.create(logfile, workDir);
	 * 		behavier.init(target, stream, filename + ".zip");
	 * 		FileUtils.deleteQuietly(workDir); // 終わったら消しておく方がよいかと
	 * 	} catch (IOException e) {
	 * 		behavier.init(target, new StringResourceStream("fail to archive."), filename + ".txt");
	 * 	}
	 * </pre>
	 */
	public static FileZippingResourceStream create(File fileToArchive, File cleanWorkDir)
	throws IOException {

		// clear work-dir
		FileUtils.deleteDirectory(cleanWorkDir);
		cleanWorkDir.mkdir();
		// copy target
		FileUtils.copyFileToDirectory(fileToArchive, cleanWorkDir);
		return new FileZippingResourceStream(cleanWorkDir);
	}

	public FileZippingResourceStream(File dirToArchive) {
		super(dirToArchive);
	}
}
