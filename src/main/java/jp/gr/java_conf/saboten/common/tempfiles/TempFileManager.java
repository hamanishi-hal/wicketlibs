package jp.gr.java_conf.saboten.common.tempfiles;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class TempFileManager implements HttpSessionBindingListener, Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> files = new ArrayList<String>();

	public void add(String filePath) {
		files.add(filePath);
	}

	public void valueBound(HttpSessionBindingEvent arg0) {
	}

	public void valueUnbound(HttpSessionBindingEvent arg0) {
		for (String file : files) {
			File tempFile = new File(file);
			if (tempFile.exists()) {
				tempFile.delete();
            }
		}
	}
}
