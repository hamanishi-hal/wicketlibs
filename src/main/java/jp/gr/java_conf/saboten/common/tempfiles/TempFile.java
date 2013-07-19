package jp.gr.java_conf.saboten.common.tempfiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TempFile {

	private static final Logger logger = LoggerFactory.getLogger(TempFile.class);


	private static final String SESSION_KEY = TempFile.class.getCanonicalName();

	private HttpSession session;
	private File tempDir;
	private File tempFile;
	private BufferedWriter writer;



	public TempFile(HttpSession session) {
		this.session = session;
	}

	public File getFile() {
		if (tempFile == null) {
			createTempDir();
			createTempFile();
			attacheToFileManager();
		}
		logger.debug(tempFile.getAbsolutePath());
		return tempFile;
	}

	public BufferedWriter getBufferedFileWriter(String encoding) {
		if (writer == null) {
			try {
				writer = new BufferedWriter(
							new OutputStreamWriter(
								new FileOutputStream(getFile()), encoding));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return writer;
	}

    protected void createTempDir() {
        String tempDirName = System.getProperty("java.io.tmpdir");
        if (tempDirName == null) {
            throw new RuntimeException("TEMPディレクトリが設定されてません。System.getProperty(java.io.tmpdir) == null");
        }
        tempDir = new File(tempDirName);
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }
    }

    protected void createTempFile() {
    	try {
    		if (tempDir == null)
    			createTempDir();
    		tempFile = File.createTempFile(session.getId(), ".tmp", tempDir);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void attacheToFileManager() {
		TempFileManager manager = (TempFileManager) session.getAttribute(SESSION_KEY);
		if (manager == null) {
			manager = new TempFileManager();
			session.setAttribute(SESSION_KEY, manager);
		}
		manager.add(tempFile.getPath());
	}
}
